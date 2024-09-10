package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	private static final String DATABASE_NAME = "rental_db";
	private static final String PROPERTIES = "?characterEncoding=utf-8";
	private static final String URL = "jdbc:mysql://localhost:3306/" + DATABASE_NAME + PROPERTIES;
    private static final String USER = "root";
    private static final String PASSWORD = "0000";
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load MySQL JDBC driver", e);
        }
    }
    
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Database connection failed.");
        }
    }
}
