package edu.mum.database;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConnection {
	
	private Connection conn = null;
	private static final String driver = "com.mysql.jdbc.Driver";

	public static void main(String[] args) {
		try {
			Class.forName(driver);
			
		} catch (ClassNotFoundException e) {
			System.out.println("Driver not found !!!");
			e.printStackTrace();
			
		}
		DataBaseConnection dbconn = new DataBaseConnection();
		dbconn.connectionUsingString();
		dbconn.connectionUsingProperties();
		dbconn.disConnnect();
	}
	
	public Connection connectionUsingProperties(){
		Properties prop = new Properties();
		try {
			prop.load(new FileInputStream("config.properties"));
		} catch (FileNotFoundException e) {			
			System.out.println("properties file not found");
			e.printStackTrace();
			return null;
		} catch (IOException e) {		
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(prop.getProperty("url"),prop);
		} catch (SQLException e) {
			System.out.println("Connection(prop) failed");
			e.printStackTrace();
			return null;
		}
		if(conn!=null){
			System.out.println("connectin (prop) established");
		}
		return conn;
	}
	
	public Connection connectionUsingString(){
		
		String url = "jdbc:mysql://localhost:3306/learning";
		String user = "root";
		String password = "";
 
		try{
			conn = DriverManager.getConnection(url, user, password);
		}
		catch(SQLException e){
			System.out.println("Connection (string) Failed");
		}
		if(conn!=null){
			System.out.println("Connection (string) Established");
		}
		return conn;
	}
	
	public void disConnnect(){
		if(conn!=null){
			try {
				conn.close();
				System.out.println("Data base disconnect");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}


