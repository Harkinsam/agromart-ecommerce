package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection con;
    private PreparedStatement pstmt;

    public void init() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/girrafe", "root", "samxino1");
            pstmt = con.prepareStatement("INSERT INTO users (fullname, email, password) VALUES (?, ?, ?)");// Compilation happens once unlike statement
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            if (emailExists(email)) {
                request.setAttribute("errorMessage", "Email already exists.");
                request.getRequestDispatcher("allError.jsp").forward(request, response);
            } else {
                pstmt.setString(1, fullName);
                pstmt.setString(2, email);
                pstmt.setString(3, password);
                pstmt.executeUpdate();
                response.sendRedirect("registrationSuccess.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred during registration. Please try again.");
            request.getRequestDispatcher("allError.jsp").forward(request, response);
        }
    }

    private boolean emailExists(String email) throws SQLException {
        String query = "SELECT COUNT(*) FROM users WHERE email = ?";
        try (PreparedStatement checkStmt = con.prepareStatement(query)) {
            checkStmt.setString(1, email);
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) > 0) {
                return true;
            }
        }
        return false;
    }

    public void destroy() {
        try {
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


