<%@ page import="com.model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%
User auth = (User) request.getSession().getAttribute("auth");
if(auth!=null){
	response.sendRedirect("home.jsp");
}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}

%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <%@include file="includes/head.jsp"%>
  

  <style>
    body {
  font-family: Arial, sans-serif;
  background-color: #f4f4f4;
  background-image: url('products/tomato.jpg');
  background-size: cover;
  background-repeat: no-repeat; 
  background-attachment: fixed;
  background-position: center; 
}

    .login-container {
      max-width: 400px;
      margin: 50px auto;
      background: #fff;
      padding: 30px;
      border-radius: 5px;
      box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.1);
    }

    .form-group {
      margin-bottom: 20px;
    }

    label {
      display: block;
      margin-bottom: 5px;
      font-weight: bold;
    }

    input[type="email"],
    input[type="password"] {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-sizing: border-box;
    }

    button[type="submit"] {
      background-color: #007bff;
      color: #fff;
      border: none;
      padding: 10px 20px;
      border-radius: 5px;
      cursor: pointer;
    }

    button[type="submit"]:hover {
      background-color: #0056b3;
    }

    .signup-link {
      text-align: center;
      margin-top: 20px;
    }

    .signup-link a {
      color: #28a745;
      text-decoration: none;
    }

    .signup-link a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
  <div class="login-container">
    <form id="loginForm" action="Login" method="POST">
      <h2>Login</h2>
      <div class="form-group">
        <label for="loginEmail">Email:</label>
        <input type="email" id="loginEmail" name="email" required>
      </div>
      <div class="form-group">
        <label for="loginPassword">Password:</label>
        <input type="password" id="loginPassword" name="password" required>
      </div>
      <div class="form-group">
        <button type="submit">Login</button>
      </div>
      <div class="signup-link">
        Don't have an account? <a href="signup.jsp">Sign up</a>
      </div>
    </form>
  </div>
</body>
</html>
