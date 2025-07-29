package com.aurionpro.test;

import java.sql.Connection;
import java.sql.SQLException;

import com.aurionpro.database.Database;

public class Database_test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection connection = Database.getConnection();
		System.out.println(connection);
		Connection connection1 = Database.getConnection();
		System.out.println(connection1);
		try {
			connection1.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
