package com.example.keycloak_custom_auth.risk;

import org.keycloak.Config;
import org.keycloak.authentication.Authenticator;
import org.keycloak.authentication.AuthenticatorFactory;
import org.keycloak.models.AuthenticationExecutionModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;
import org.keycloak.provider.ProviderConfigProperty;

import java.util.ArrayList;
import java.util.List;

public class EmailOtpAuthenticatorFactory implements AuthenticatorFactory {

    public static final String PROVIDER_ID = "email-otp-authenticator";

@Override
    public Authenticator create(KeycloakSession session) {
        return new EmailOtpAuthenticator();
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }

    @Override
    public String getDisplayType() {
        return "Email OTP Authenticator";
    }

    @Override
    public String getHelpText() {
        return "Authenticator that sends a one-time password (OTP) to the user's email.";
    }

    @Override
    public String getReferenceCategory() {
        return "otp";
    }

    @Override
    public boolean isConfigurable() {
        return true;
    }

    @Override
    public AuthenticationExecutionModel.Requirement[] getRequirementChoices() {
        // Mark this execution as REQUIRED in the authentication flow.
        return new AuthenticationExecutionModel.Requirement[]{AuthenticationExecutionModel.Requirement.REQUIRED};
    }

    @Override
    public boolean isUserSetupAllowed() {
        return false;
    }

    

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        List<ProviderConfigProperty> configProperties = new ArrayList<>();

        ProviderConfigProperty expiryMinutes = new ProviderConfigProperty();
        expiryMinutes.setName(ConfigKeys.OTP_EXPIRY_MINUTES);
        expiryMinutes.setLabel("OTP Expiry (minutes)");
        expiryMinutes.setType(ProviderConfigProperty.STRING_TYPE);
        expiryMinutes.setHelpText("Number of minutes the OTP is valid for.");
        expiryMinutes.setDefaultValue("5");
        configProperties.add(expiryMinutes);

        ProviderConfigProperty emailSubject = new ProviderConfigProperty();
        emailSubject.setName(ConfigKeys.OTP_EMAIL_SUBJECT);
        emailSubject.setLabel("OTP Email Subject");
        emailSubject.setType(ProviderConfigProperty.STRING_TYPE);
        emailSubject.setHelpText("Subject of the OTP email.");
        emailSubject.setDefaultValue("Your OTP Code");
        configProperties.add(emailSubject);

        return configProperties;
    }

    @Override
    public void init(Config.Scope scope) {
    }

    @Override
    public void postInit(KeycloakSessionFactory keycloakSessionFactory) {
    }

    @Override
    public void close() {
    }

    
}
