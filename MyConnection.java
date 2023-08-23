package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
  	public static Connection connection; 
    public static Connection getConnection() {
    	try{
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectjava1?useSSL=false","root","root");
    	}
    	catch(Exception  e) {
    		e.printStackTrace();
    		
    	}
    	System.out.println("Connection esatblished");
    	return connection;
   
    }
    public static void closeConnection() {
    	if(connection != null) {
    		try {
    			connection.close();
    		}
    		catch(Exception ex) {
    			ex.printStackTrace();
    		}
    	}
    }
    /*public static void main(String[] args) {
    	MyConnection.getConnection();*/
    
}
