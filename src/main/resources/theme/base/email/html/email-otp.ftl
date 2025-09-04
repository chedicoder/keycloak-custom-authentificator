<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Your OTP Code</title>
  <style>
    body { font-family: Arial, sans-serif; line-height: 1.6; }
    .otp { font-size: 1.5em; color: #2E86C1; font-weight: bold; }
    .footer { margin-top: 20px; font-size: 0.9em; color: #666; }
  </style>
</head>
<body>
  <h1>Email OTP Verification</h1>
  <p>Hello ${user.username!""},</p>
  <p>Your One-Time Password (OTP) is:</p>
  <p class="otp">${otp}</p>
  <p>This OTP will expire in 5 minutes. Do not share it with anyone.</p>
  <div class="footer">
    Regards,<br/>
    The Keycloak Team
  </div>
</body>
</html>
