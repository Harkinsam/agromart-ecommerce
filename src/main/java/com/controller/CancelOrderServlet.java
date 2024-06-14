package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DBConnection;
import com.dao.OrderDao;

@WebServlet("/cancel-order")
public class CancelOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            if (id != null) {
                try (OrderDao orderDao = new OrderDao(DBConnection.getConnection())) {
                    orderDao.cancelOrder(Integer.parseInt(id));
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", "An error occurred while canceling the order. Please try again later.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                    return;
                }
            }
            response.sendRedirect("order.jsp");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }

}