package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.connection.DBConnection;
import com.dao.OrderDao;
import com.model.Order;

@WebServlet("/procurement-list")
public class ProcurementListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (OrderDao orderDao = new OrderDao(DBConnection.getConnection())) {
            List<Order> procurementList = orderDao.createProcurementList();
            request.setAttribute("procurementList", procurementList);
            
            request.getRequestDispatcher("procurement-list.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while processing the procurement list.");
            request.getRequestDispatcher("allError.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}

