package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DBConnection;
import com.dao.OrderDao;
import com.model.Cart;
import com.model.Order;
import com.model.User;

import java.sql.SQLException;

@WebServlet("/cart-check-out")
public class CheckOutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.now();

            ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
            User auth = (User) request.getSession().getAttribute("auth");

            if (cart_list != null && auth != null) {
                try (OrderDao oDao = new OrderDao(DBConnection.getConnection())) {
                    for (Cart c : cart_list) {
                        Order order = new Order();
                        order.setId(c.getId());
                        order.setUid(auth.getId());
                        order.setQuantity(c.getQuantity());
                        order.setDate(date);

                        boolean result = oDao.insertOrder(order);
                        if (!result) {
                        	
                            request.setAttribute("errorMessage", "Order failed for product: " + c.getId());
                            RequestDispatcher dispatcher = request.getRequestDispatcher("allError.jsp");
                            dispatcher.forward(request, response);
                            return;
                        }
                    }
                    // Clear the cart after successful checkout
                    cart_list.clear();
                    response.sendRedirect("orders.jsp");
                } catch (Exception e) {
                    e.printStackTrace();
                    response.sendRedirect("error.jsp");
                }
            } else {
                if (auth == null) {
                    response.sendRedirect("login.jsp");
                } else {
                    response.sendRedirect("cart.jsp");
                }
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
