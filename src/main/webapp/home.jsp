<%@page import="com.connection.DBConnection"%>
<%@page import="com.dao.ProductDao"%>
<%@ page import="com.model.Product" %>
<%@page import="java.util.*"%>
<%@ page import="com.model.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%

User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ProductDao pd = new ProductDao(DBConnection.getConnection());
List<Product> products = pd.getAllProducts();

ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
List<Cart> cartProduct = null;
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}


%>

<!DOCTYPE html>
<html>
<head>
<title>Agriculture</title>
<%@include file="includes/head.jsp"%>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>


	<!-- header section end -->
	<!-- layout_border start -->
	<div class="container-fluid">
		<div class="layout_border">
			<!-- banner section start -->
			<div class="banner_section layout_padding">
				<div class="container-fluid">
					<div id="main_slider" class="carousel slide" data-ride="carousel">
						<div class="carousel-inner">
							<div class="carousel-item active">
								<div class="row">
									<div class="col-sm-6">
										<div class="banner_taital_main">
											<h1 class="banner_taital">Fresh Vegetable Shop</h1>
											<p class="banner_text">Many variations of passages of
												Lorem Ipsum available, but the majority have suffered</p>
											<div class="btn_main">
												<div class="started_text">
													<a href="#">Buy Now</a>
												</div>
												<div class="started_text active">
													<a href="#">Contact Us</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="banner_img">
											<img src="images/banner-img.png">
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<div class="row">
									<div class="col-sm-6">
										<div class="banner_taital_main">
											<h1 class="banner_taital">Fresh Vegetable Shop</h1>
											<p class="banner_text">>Many variations of passages of
												vegetables and fruits are  available, but the majority have suffered</p>
											<div class="btn_main">
												<div class="started_text">
													<a href="#">Buy Now</a>
												</div>
												<div class="started_text active">
													<a href="#">Contact Us</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="banner_img">
											<img src="images/banner-img.png">
										</div>
									</div>
								</div>
							</div>
							<div class="carousel-item">
								<div class="row">
									<div class="col-sm-6">
										<div class="banner_taital_main">
											<h1 class="banner_taital">Fresh Vegetable Shop</h1>
											<p class="banner_text">Many variations of passages of
												vegetables and fruits are  available, but the majority have suffered</p>
											<div class="btn_main">
												<div class="started_text active">
													<a href="#">Contact Us</a>
												</div>
											</div>
										</div>
									</div>
									<div class="col-sm-6">
										<div class="banner_img">
											<img src="images/banner-img.png">
										</div>
									</div>
								</div>
							</div>
						</div>
						<a class="carousel-control-prev" href="#main_slider" role="button"
							data-slide="prev"> <img src="images/arrow-left.png">
						</a> <a class="carousel-control-next" href="#main_slider"
							role="button" data-slide="next"> <img
							src="images/arrow-right.png">
						</a>
					</div>
				</div>
			</div>
			<!-- banner section end -->

			<div class="container">
		<div class="card-header my-3">All Products</div>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="<%=p.getImage()%>" alt="Product Image">>
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
						<h6 class="price">Price: $<%=p.getPrice() %></h6>
						<h6 class="category">Category: <%=p.getCategory() %></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a class="btn btn-dark" href="add-to-cart?id=<%=p.getId()%>">Add to Cart</a> <a
								class="btn btn-primary" href="order-now?quantity=1&id=<%=p.getId()%>">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("There is no proucts");
			}
			%>

		</div>
	</div>


			<!-- about section start -->
			<div class="about_section layout_padding">
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<h1 class="about_taital">About Us</h1>
							<p class="about_text">Passages of Lorem Ipsum available, but
								the majority have suffered alteration</p>
						</div>
					</div>
					<div class="about_section_2"></div>
					<div class="row">
						<div class="col-md-6">
							<div class="about_img">
								<img src="images/about-img.png">
							</div>
						</div>
						<div class="col-md-6">
							<div class="fresh_taital">Fresh any</div>
							<p class="ipsum_text">Variations of passages of Lorem Ipsum
								available, but the majority have suffered alteration in some
								form, by injected humour, or randomisedvariations of passages of
								Lorem Ipsum available, but the majority have suffered alteration
								in some form, by injected humour, or randomised</p>
							<div class="read_bt">
								<a href="#">Read More</a>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- about section end -->
			<!-- vagetables section start -->
			
			<!-- contact section start -->
			<div class="contact_section layout_padding">
				<div class="container">
					<div class="row">
						<div class="col-sm-12">
							<h1 class="contact_taital">Get In Touch</h1>
						</div>
					</div>
				</div>
				<div class="container">
					<div class="contact_section_2">
						<div class="row">
							<div class="col-md-6">
								<div class="mail_section_1">
									<input type="text" class="mail_text" placeholder="Name"
										name="Name"> <input type="text" class="mail_text"
										placeholder="Phone Number" name="Phone Number"> <input
										type="text" class="mail_text" placeholder="Email" name="Email">
									<textarea class="massage-bt" placeholder="Massage" rows="5"
										id="comment" name="Massage"></textarea>
									<div class="send_bt">
										<a href="#">SEND</a>
									</div>
								</div>
							</div>
							<div class="col-md-6">
								<div class="map_main">
									<div class="map-responsive">
										<iframe
											src="https://www.google.com/maps/embed/v1/place?key=AIzaSyA0s1a7phLN0iaD6-UE7m4qP-z21pH0eSc&amp;q=Eiffel+Tower+Paris+France"
											width="600" height="340" frameborder="0"
											style="border: 0; width: 100%;" allowfullscreen=""></iframe>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- contact section end -->
			
			<!-- layout_border end -->
		</div>
	</div>
	<!-- footer section start -->
	<div class="footer_section layout_padding">
		<div class="container">
			<div class="row">
				<div class="col-lg-4 col-sm-6">
					<h3 class="footer_text">Useful links</h3>
					<div class="footer_menu">
						<ul>
							<li class="active"><a href="index.html"><span
									class="angle_icon active"><i class="fa fa-arrow-right"
										aria-hidden="true"></i></span> Home</a></li>
							<li><a href="about.html"><span class="angle_icon"><i
										class="fa fa-arrow-right" aria-hidden="true"></i></span> About</a></li>
							<li><a href="services.html"><span class="angle_icon"><i
										class="fa fa-arrow-right" aria-hidden="true"></i></span> Services</a></li>
							
							<li><a href="contact.html"><span class="angle_icon"><i
										class="fa fa-arrow-right" aria-hidden="true"></i></span> Contact Us</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-sm-6">
					<h3 class="footer_text">Address</h3>
					<div class="location_text">
						<ul>
							<li><a href="#"> <span class="padding_left_10"><i
										class="fa fa-map-marker" aria-hidden="true"></i></span>It is a long
									established fact that a<br> reader will be distracted
							</a></li>
							<li><a href="#"> <span class="padding_left_10"><i
										class="fa fa-phone" aria-hidden="true"></i></span>(+71) 1234567890<br>(+71)
									1234567890
							</a></li>
							<li><a href="#"> <span class="padding_left_10"><i
										class="fa fa-envelope" aria-hidden="true"></i></span>demo@gmail.com
							</a></li>
						</ul>
					</div>
				</div>
				<div class="col-lg-4 col-sm-6">
					<div class="footer_main">
						<h3 class="footer_text">Find Us</h3>
						<p class="dummy_text">more-or-less normal distribution</p>
						<div class="social_icon">
							<ul>
								<li><a href="#"><i class="fa fa-facebook"
										aria-hidden="true"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"
										aria-hidden="true"></i></a></li>
								<li><a href="#"><i class="fa fa-instagram"
										aria-hidden="true"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- footer section end -->
	<!-- copyright section start -->
	<div class="copyright_section">
		<div class="container">
			<p class="copyright_text">
				2023 All Rights Reserved. Design by <a href="https://html.design">Free
					html Templates</a>
			</p>
		</div>
	</div>
	<!-- copyright section end -->
	<!-- Javascript files-->
	<script src="js/jquery.min.js"></script>
	<script src="js/popper.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script src="js/jquery-3.0.0.min.js"></script>
	<script src="js/plugin.js"></script>
</body>
</html>

