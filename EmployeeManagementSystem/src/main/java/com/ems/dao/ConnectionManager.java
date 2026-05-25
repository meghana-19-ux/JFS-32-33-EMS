package com.ems.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectionManager {
	public static Connection getConnection() {
		String driver="com.mysql.cj.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/ems";
		String username="root";
		String password="root";
		Connection connection=null;
		try {
			Class.forName(driver);
			connection=DriverManager.getConnection(url,username,password);
			connection.setAutoCommit(false);
		}
		catch(ClassNotFoundException e) {
			System.out.println(e);
		}catch(SQLException e) {
			System.out.println(e);
		}
		return connection;
	}
}
