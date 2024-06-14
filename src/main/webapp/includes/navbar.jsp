
 <nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="container">
		<a class="navbar-brand" href="home.jsp">E-Commerce Cart</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="home.jsp">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="Delivery.jsp">Cart <span class="badge badge-danger px-1">${cart_list.size()}</span> </a></li>
				<%
				if (request.getAttribute("auth") != null) {
				%>
				<li class="nav-item"><a class="nav-link" href="order.jsp">Orders</a></li>
				<li class="nav-item"><a class="nav-link" href="LogoutServlet">Logout</a></li>
				<%
				} else {
				%>
				<li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
				<%
				}
				%>
			</ul>
		</div>
	</div>
</nav>