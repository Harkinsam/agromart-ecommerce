
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Failed</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .error-container {
            background-color: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }
        .error-container h1 {
            color: #d9534f;
        }
        .error-container p {
            color: #5a5a5a;
        }
        .error-container a {
            color: #0275d8;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Login Failed</h1>
        <p>${errorMessage}</p>
        <p><a href="login.jsp">Go back to login</a></p>
    </div>
</body>
</html>
