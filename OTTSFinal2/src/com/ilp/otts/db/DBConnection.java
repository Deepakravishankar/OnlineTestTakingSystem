package com.ilp.otts.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	public static Connection getConnection(){
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//office
			//Connection con=DriverManager.getConnection("jdbc:oracle:thin:@172.24.137.30:1521:ora10g","e584491","emraan");
			//home
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@Emraan-PC:1521:orcl","scott","emraan");
			return con;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("SQL Exception Found Error");
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Class Not Found Exception Found Error");
			e.printStackTrace();
		}
		return null;
		
		

	}
}
