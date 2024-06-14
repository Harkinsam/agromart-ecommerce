<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.Order" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Admin Orders</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
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
        <h1 class="mb-4">Orders</h1>
        <table class="table table-bordered table-hover">
            <thead class="thead-light">
                <tr>
                    <th>Order ID</th>
                    <th>Customer</th>
                    <th>Product Name</th>
                    <th>Category</th>
                    <th>Quantity</th>
                    <th>Order Date</th>
                    <th>Delivery Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Order> orderList = (List<Order>) request.getAttribute("orderLists");
                    if (orderList != null && !orderList.isEmpty()) {
                        for (Order order : orderList) {
                %>
                <tr>
                    <td><%= order.getId() %></td>
                    <td><%= order.getUserName() %></td>
                    <td><%= order.getProductName() %></td>
                    <td><%= order.getCategory() %></td>
                    <td><%= order.getQuantity() %></td>
                    <td><%= order.getDate() %></td>
                    <td><%= order.getDeliveryDate() %></td>
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


