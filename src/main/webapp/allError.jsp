<!-- error-page.jsp -->
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Error Page</title>
<style>
    body {
        background-color: #f8f9fa;
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        min-height: 100vh;
    }

    .error-container {
        text-align: center;
    }

    .error-title {
        font-size: 36px;
        color: #dc3545;
        margin-bottom: 20px;
    }

    .error-message {
        font-size: 18px;
        color: #6c757d;
    }

    .error-image {
        width: 150px;
        margin-bottom: 20px;
    }

    .error-button {
        padding: 10px 20px;
        background-color: #007bff;
        color: #fff;
        border: none;
        border-radius: 5px;
        text-transform: uppercase;
        font-weight: bold;
        text-decoration: none;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .error-button:hover {
        background-color: #0056b3;
    }
</style>
</head>
<body>
    <div class="error-container">
        <h1 class="error-title">Oops! Something went wrong.</h1>
        <p class="error-message">${errorMessage}</p>
        <a href="home.jsp" class="error-button">Go Back to Home</a>
    </div>
</body>
</html>
