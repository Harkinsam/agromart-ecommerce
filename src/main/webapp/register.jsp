
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Registration</title>
</head>
<body>

    <h1>User Registration Form</h1>
    <form method="post" action="registerServlet">
        <label for="firstname">First Name:</label>
        <input type="text" id="firstname" name="firstname" required><br>

        <label for="lastname">Last Name:</label>
        <input type="text" id="lastname" name="lastname" required><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br>

        <label for="phone">Phone:</label>
        <input type="tel" id="phone" name="phone" required><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>

        <button type="submit">Register</button>
    </form>
</body>
</html>
