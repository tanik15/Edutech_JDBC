package com.aurionpro.test;

import java.sql.Connection;

import com.aurionpro.database.DBUtil;

public class DBtest {
	 public static void main(String[] args) {
	        Connection conn = DBUtil.getConnection();
	        if (conn != null) {
	            System.out.println("Database connection successful!");
	        } else {
	            System.out.println(" Database connection failed!");
	        }
	    }
}
