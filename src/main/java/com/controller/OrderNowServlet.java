package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

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

@WebServlet("/order-now")
public class OrderNowServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate date = LocalDate.now();

            User auth = (User) request.getSession().getAttribute("auth");

            if (auth != null) {
                String productId = request.getParameter("id");
                int productQuantity = Integer.parseInt(request.getParameter("quantity"));
                String deliveryDateString = request.getParameter("deliveryDate");
                if (productQuantity <= 0) {
                    productQuantity = 1;
                }
                Order orderModel = new Order();
                orderModel.setId(Integer.parseInt(productId));
                orderModel.setUid(auth.getId());
                orderModel.setQuantity(productQuantity);
                orderModel.setDate(date);
                orderModel.setDeliveryDate(LocalDate.parse(deliveryDateString, formatter));

                try (OrderDao orderDao = new OrderDao(DBConnection.getConnection())) {
                    boolean result = orderDao.insertOrder(orderModel);
                    if (result) {
                        ArrayList<Cart> cart_list = (ArrayList<Cart>) request.getSession().getAttribute("cart-list");
                        if (cart_list != null) {
                            for (Cart c : cart_list) {
                                if (c.getId() == Integer.parseInt(productId)) {
                                    cart_list.remove(cart_list.indexOf(c));
                                    break;
                                }
                            }
                            request.getSession().setAttribute("cart-list", cart_list); // Update session attribute
                        }
                        response.sendRedirect("order.jsp");
                    } else {
                        // Order insertion failed
                        request.setAttribute("errorMessage", "Failed to place the order. Please try again later.");
                        request.getRequestDispatcher("error-page.jsp").forward(request, response);
                    }
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", "An error occurred while processing the order. Please try again later.");
                    request.getRequestDispatcher("allError.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("login.jsp");
            }

        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
