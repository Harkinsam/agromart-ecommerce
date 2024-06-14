package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertProductServlet")
public class InsertProductServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String category = request.getParameter("category");
        double price = Double.parseDouble(request.getParameter("price"));
        String imagePath = request.getParameter("image");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/girrafe", "root", "samxino1");
            String sql = "INSERT INTO goods (name, category, price, image) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, category);
            statement.setDouble(3, price);
            statement.setString(4, imagePath);

            int rowsInserted = statement.executeUpdate(); // Get the number of rows affected
            statement.close();
            conn.close();

            if (rowsInserted > 0) {
                response.sendRedirect("success.jsp");
            } else {
                request.setAttribute("errorMessage", "An error occurred while inserting the product. Please try again later.");
                request.getRequestDispatcher("allError.jsp").forward(request, response);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while inserting the product. Please try again later.");
            request.getRequestDispatcher("allError.jsp").forward(request, response);
        }
    }
}

