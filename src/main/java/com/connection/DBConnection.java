package com.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static Connection connection = null;
    private static final String DB_URL = "jdbc:mysql://localhost/girrafe";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "samxino1";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
    	if(connection==null) {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    		System.out.println("connected");
    	}
    	return connection;	
    }

}

