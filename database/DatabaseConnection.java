package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // JDBC URL, username, and password of MySQL server
    private static final String URL = "jdbc:mysql://localhost:3306/eticketing?useSSL=false&serverTimezone=UTC"; // Fixed the DB URL
    private static final String USER = "root"; // Change to your DB username
    private static final String PASSWORD = "Grape2004"; // Change to your DB password

    // JDBC variables for opening, closing, and managing the connection
    private static Connection connection;

    // Method to get the database connection
    public static Connection getConnection() {
        try {
            // Check if the connection already exists
            if (connection == null || connection.isClosed()) {
                // Load MySQL JDBC driver (optional, depending on your setup)
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Create a new connection
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return connection;
    }

    // Method to close the connection
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
