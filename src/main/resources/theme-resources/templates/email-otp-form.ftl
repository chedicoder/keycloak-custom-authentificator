<#-- Simple FreeMarker form -->
<html>
<head><title>Email OTP Verification</title></head>
<body>
<h2>Enter the code sent to your email</h2>
<form method="post">
    <input type="text" name="otp" placeholder="Enter OTP" required/>
    <button type="submit">Verify</button>
</form>
<#if error??>
    <p style="color:red">${error}</p>
</#if>
</body>
</html>
