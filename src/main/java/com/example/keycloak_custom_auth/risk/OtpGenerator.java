package com.example.keycloak_custom_auth.risk;

import java.security.SecureRandom;

public class OtpGenerator {

    private static final SecureRandom random = new SecureRandom();
    public static String generateCode() {
        int code = 100000 + random.nextInt(900000); 
        return String.valueOf(code);
    }

}
