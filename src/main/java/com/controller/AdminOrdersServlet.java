package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DBConnection;
import com.dao.OrderDao;
import com.model.Order;

@WebServlet("/admin-orders")
public class AdminOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			try (OrderDao orderDao = new OrderDao(DBConnection.getConnection())) {
				List<Order> orderLists = orderDao.getAllOrders();
				request.setAttribute("orderLists", orderLists);
				request.getRequestDispatcher("adminOrders.jsp").forward(request, response);
			}
		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("errorMessage", "Error accessing the database: " + e.getMessage());
			request.getRequestDispatcher("allError.jsp").forward(request, response);
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
			request.getRequestDispatcher("allError.jsp").forward(request, response);
		}
	}
}
