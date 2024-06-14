<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.model.Order"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin Procurement</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<title>Procurement List</title>
<style>
table {
	width: 100%;
	border-collapse: collapse;
	margin-bottom: 20px;
}

th, td {
	border: 1px solid #dddddd;
	padding: 8px;
	text-align: left;
}

th {
	background-color: #f2f2f2;
}

tr:nth-child(even) {
	background-color: #f9f9f9;
}

tr:hover {
	background-color: #e2e2e2;
}
</style>
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container">
            <a class="navbar-brand" href="home.jsp">E-Commerce Cart</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item"><a class="nav-link" href="admin.jsp">Home</a></li>
                    <li class="nav-item"><a class="nav-link" href="admin-orders">View Orders</a></li>
                    <li class="nav-item"><a class="nav-link" href="procurement-list">Procurement Days</a></li>
                    <li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="container mt-5">
        <h1 class="mb-4">Procurement</h1>
        <table class="table table-bordered table-hover">
            <thead class="thead-light">
                <tr>
                    <th>Product ID</th>
                    <th>Product Name</th>
                    <th>Quantity</th>
                    <th>Order Date</th>
                    <th>Delivery Date</th>
                    <th>Procurement Day</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Order> orderList = (List<Order>) request.getAttribute("procurementList");
                    if (orderList != null && !orderList.isEmpty()) {
                        for (Order order : orderList) {
                %>
                <tr>
                    <td><%= order.getId() %></td>
                    <td><%= order.getProductName() %></td>
                    <td><%= order.getQuantity() %></td>
                    <td><%= order.getDate() %></td>
                    <td><%= order.getDeliveryDate() %></td>
                    <td><%= order.getProcurementDay() %></td>
                </tr>
                <% 
                        }
                    } else {
                %>
                <tr>
                    <td colspan="7" class="text-center">No orders found.</td>
                </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
      <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
