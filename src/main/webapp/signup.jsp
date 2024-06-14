 
 <%@ page import="com.model.*"%>
  <%
User auth = (User) request.getSession().getAttribute("auth");
if(auth!=null){
	response.sendRedirect("home.jsp");
}
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Sign Up</title>
  <%@include file="includes/head.jsp"%>
 
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
    }

    .form-container {
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
    input[type="password"],
    input[type="text"] {
      width: 100%;
      padding: 10px;
      border: 1px solid #ccc;
      border-radius: 5px;
      box-sizing: border-box;
    }

    button[type="submit"] {
      background-color: #28a745;
      color: #fff;
      border: none;
      padding: 10px 20px;
      border-radius: 5px;
      cursor: pointer;
    }

    button[type="submit"]:hover {
      background-color: #218838;
    }

    .login-link {
      text-align: center;
      margin-top: 20px;
    }

    .login-link a {
      color: #007bff;
      text-decoration: none;
    }

    .login-link a:hover {
      text-decoration: underline;
    }
  </style>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
  <div class="form-container">
    <form id="signupForm" action="registerServlet" method="POST">
      <h2>Sign Up</h2>
      <div class="form-group">
        <label for="fullName">Full Name:</label>
        <input type="text" id="fullName" name="fullname" required>
      </div>
      <div class="form-group">
        <label for="signupEmail">Email:</label>
        <input type="email" id="signupEmail" name="email" required>
      </div>
      <div class="form-group">
        <label for="signupPassword">Password:</label>
        <input type="password" id="signupPassword" name="password" required>
      </div>
      <div class="form-group">
        <button type="submit">Sign Up</button>
      </div>
      <div class="login-link">
        Already have an account? <a href="login.jsp">Login</a>
      </div>
    </form>
  </div>
</body>
</html>

