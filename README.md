# Email OTP Authenticator for Keycloak

This project provides a **custom Keycloak authenticator** that implements **email-based One-Time Password (OTP)** verification. It allows you to enhance the authentication flow by sending a temporary OTP to the user's email address after successful password authentication.

## Features

- Generates a **6-digit OTP** for each login attempt.
- OTP is **valid for 5 minutes** by default (configurable in the factory).
- Sends OTP to the user's **registered email**.
- Provides a **FreeMarker-based form** for OTP input.
- Validates OTP and allows login if the code is correct.
- Supports integration as a **step in any authentication flow**.

## How it works

1. User enters their **username and password** in the normal login form.
2. The Email OTP Authenticator triggers after successful password validation.
3. A **random 6-digit OTP** is generated and stored in the user’s authentication session.
4. The OTP is sent to the user’s **email**.
5. The user sees a **form** to enter the OTP.
6. Authenticator validates the entered OTP:
   - If correct and not expired → **authentication succeeds**.
   - If incorrect or expired → **authentication fails**.

## Components

- **EmailOtpAuthenticator.java**  
  Implements the logic for OTP generation, email sending, and verification.

- **EmailOtpAuthenticatorFactory.java**  
  Registers the authenticator in Keycloak and provides configuration options.

- **OtpGenerator.java**  
  Generates secure 6-digit OTP codes.

- **email-otp-form.ftl**  
  FreeMarker template for OTP input form.

## Installation

1. Build the project using Maven:

   ```bash
   ./mvnw clean package
   cp target/keycloak-custom-auth-0.0.1-SNAPSHOT.jar ~/keycloak-26.1.4/providers/
   cd ~/keycloak-26.1.4
   bin/kc.sh build
   bin/kc.sh start-dev
