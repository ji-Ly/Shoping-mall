package com.hly.context;

import java.sql.Connection;
import java.sql.DriverManager;

public class Context {
	
	public static Connection getConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Connection connection = DriverManager.getConnection("jdbc:sqlserver://localhost:1433; databaseName =Wish; encrypt = false", "sa", "12345");
			return connection;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		if(getConnection() != null) {
			System.out.println("Ok");
		}else {
			System.out.println("fail");
		}
	}

}
