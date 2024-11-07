package dao.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	 public static Connection getMySQLConnection(){
	     // Note: Change the connection parameters accordingly.
	     String hostName = "localhost";
	     String dbName = "proslavarodjendana";
	     String userName = "root";
	     String password = "root";
	     return getMySQLConnection(hostName, dbName, userName, password);
	 }
	  
	 public static Connection getMySQLConnection(String hostName, String dbName,
	         String userName, String password){
	    
	     try {
			Class.forName("com.mysql.jdbc.Driver");//sta ovo radi
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
	     // URL Connection for MySQL:
	     // Example: 
	     // jdbc:mysql://localhost:3306/simplehr
	     String connectionURL = "jdbc:mysql://" + hostName + ":3306/" + dbName;
	  
	     Connection conn=null;
		try {
			conn = DriverManager.getConnection(connectionURL, userName,
			         password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return conn;
	 }
}
