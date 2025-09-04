package com.example.keycloak_custom_auth.risk;

import org.keycloak.authentication.*;
import org.keycloak.email.EmailException;
import org.keycloak.models.*;
import org.keycloak.sessions.AuthenticationSessionModel;
import jakarta.ws.rs.core.Response;

import java.util.*;

public class EmailOtpAuthenticator implements Authenticator {

    private static final String OTP_NOTE = "EMAIL_OTP";
    private static final String OTP_EXPIRY_NOTE = "EMAIL_OTP_EXPIRY";

    @Override
    public void authenticate(AuthenticationFlowContext context) {
        String otp = OtpGenerator.generateCode();
        long expiry = System.currentTimeMillis() + 5 * 60 * 1000; // 5 minutes

        AuthenticationSessionModel session = context.getAuthenticationSession();
        session.setAuthNote(OTP_NOTE, otp);
        session.setAuthNote(OTP_EXPIRY_NOTE, String.valueOf(expiry));

        UserModel user = session.getAuthenticatedUser();
        String email = user.getEmail();

        if (email == null || email.trim().isEmpty()) {
            context.failureChallenge(AuthenticationFlowError.INVALID_USER,
                    context.form().setError("Missing email for OTP").createErrorPage(Response.Status.BAD_REQUEST));
            return;
        }

        try {
            Map<String, Object> vars = new HashMap<>();
            vars.put("otp", otp);
            context.getSession().getProvider(org.keycloak.email.EmailTemplateProvider.class)
                .setRealm(context.getRealm())
                .setUser(user)
                .send("Your OTP Code", "otp-email.ftl", vars);


        } catch (EmailException e) {
            context.failureChallenge(AuthenticationFlowError.INTERNAL_ERROR,
                    context.form().setError("Failed to send OTP email").createErrorPage(Response.Status.INTERNAL_SERVER_ERROR));
            return;
        }

        Response challenge = context.form().createForm("email-otp-form.ftl");
        context.challenge(challenge);
    }

    @Override
    public void action(AuthenticationFlowContext context) {
        String enteredOtp = context.getHttpRequest().getDecodedFormParameters().getFirst("otp");
        String expectedOtp = context.getAuthenticationSession().getAuthNote(OTP_NOTE);
        long expiry = Long.parseLong(context.getAuthenticationSession().getAuthNote(OTP_EXPIRY_NOTE));

        if (System.currentTimeMillis() > expiry) {
            context.failureChallenge(AuthenticationFlowError.EXPIRED_CODE,
                    context.form().setError("OTP expired").createForm("email-otp-form.ftl"));
            return;
        }

        if (enteredOtp != null && enteredOtp.equals(expectedOtp)) {
            context.success();
        } else {
            context.failureChallenge(AuthenticationFlowError.INVALID_CREDENTIALS,
                    context.form().setError("Invalid OTP").createForm("email-otp-form.ftl"));
        }
    }

    @Override public boolean requiresUser() { return true; }
    @Override public boolean configuredFor(KeycloakSession session, RealmModel realm, UserModel user) { return true; }
    @Override public void setRequiredActions(KeycloakSession session, RealmModel realm, UserModel user) { }
    @Override public void close() { }
}
