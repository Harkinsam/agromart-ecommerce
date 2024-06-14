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
import com.dao.UserDao;
import com.model.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp"); // for security purpose if the url of the servlet is entered
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			try {
				UserDao userDao = new UserDao(DBConnection.getConnection());
				User user = userDao.userLogin(email, password);
				if(user != null) {
					request.getSession().setAttribute("auth", user);
					
					if ("admin@example.com".equals(email) && "admin123".equals(password)) {
						response.sendRedirect("admin.jsp");
					}else {
						response.sendRedirect("home.jsp");
					}
					
				}else {
					 request.setAttribute("errorMessage", "Invalid email or password.");
			            request.getRequestDispatcher("allError.jsp").forward(request, response);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
				 request.setAttribute("errorMessage", "An error occurred during login. Please try again.");
			        request.getRequestDispatcher("allError.jsp").forward(request, response);
			}
		}
	}

}
