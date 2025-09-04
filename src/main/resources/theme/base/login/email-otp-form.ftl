<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Verify OTP</title>
    <style>
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f6f8;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .otp-container {
            background: #ffffff;
            padding: 40px 50px;
            border-radius: 12px;
            box-shadow: 0 8px 20px rgba(0,0,0,0.1);
            max-width: 400px;
            width: 100%;
            text-align: center;
        }

        h2 {
            color: #2E86C1;
            margin-bottom: 20px;
        }

        input[type="text"] {
            width: 100%;
            padding: 12px 15px;
            margin: 15px 0;
            border-radius: 8px;
            border: 1px solid #ccc;
            font-size: 16px;
        }

        button {
            background-color: #2E86C1;
            color: white;
            border: none;
            padding: 12px 25px;
            font-size: 16px;
            border-radius: 8px;
            cursor: pointer;
            transition: background 0.3s;
        }

        button:hover {
            background-color: #1B4F72;
        }

        .error {
            color: red;
            margin-top: 10px;
            font-size: 14px;
        }

        p.info {
            font-size: 14px;
            color: #555;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <div class="otp-container">
        <h2>OTP Verification</h2>
        <p class="info">Enter the one-time password sent to your email</p>
        <form action="${url.loginAction}" method="post">
            <input type="text" name="otp" placeholder="Enter OTP" required/>
            <button type="submit">Verify</button>
        </form>
        <#if errorMessage??>
            <p class="error">${errorMessage}</p>
        </#if>
    </div>
</body>
</html>
