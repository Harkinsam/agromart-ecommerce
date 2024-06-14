<!DOCTYPE html>
<html>
<head>
    <title>Product Details</title>
</head>
<body>
    <% if (request.getAttribute("product") != null) { %>
        <h1><%= ((com.model.Product) request.getAttribute("product")).getName() %></h1>
        <p>Category: <%= ((com.model.Product) request.getAttribute("product")).getCategory() %></p>
        <p>Price: <%= ((com.model.Product) request.getAttribute("product")).getPrice() %></p>
        <!-- Display product image -->
        <img src="<%= request.getContextPath() %>/<%= ((com.model.Product) request.getAttribute("product")).getImage() %>" alt="Product Image">
    <% } else { %>
        <p>Product not found.</p>
    <% } %>
</body>
</html>
