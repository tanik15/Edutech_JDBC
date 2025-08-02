package com.aurionpro.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	 private static final String URL = "jdbc:mysql://localhost:3306/edutech";
	    private static final String USER = "root";
	    private static final String PASSWORD = "Samruddhi@2003";

	    
	    
	    public static Connection getConnection() {
	        Connection connection = null;
	        try {
	            connection = DriverManager.getConnection(URL, USER, PASSWORD);
	        } catch (SQLException e) {
	            System.out.println("Database connection failed!");
	            e.printStackTrace();
	        }
	        return connection;
	    }
	}

