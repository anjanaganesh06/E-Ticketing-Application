// package model;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;

// public class User {
//     private String username;
//     private String password;
//     private String email;

//     // Database connection details
//     private static final String URL = "jdbc:mysql://localhost:3306/eticketing";  // Make sure your DB name is correct
//     private static final String USER = "root";  // Your MySQL username
//     private static final String PASSWORD = "Grape2004";  // Your MySQL password

//     // Constructor to set username, password, and email
//     public User(String username, String password, String email) {
//         this.username = username;
//         this.password = password;
//         this.email = email;
//     }

//     // Empty constructor (if needed)
//     public User() {}

//     // Getter and setter methods for username, password, and email
//     public String getUsername() {
//         return username;
//     }

//     public void setUsername(String username) {
//         this.username = username;
//     }

//     public String getPassword() {
//         return password;
//     }

//     public void setPassword(String password) {
//         this.password = password;
//     }

//     public String getEmail() {
//         return email;
//     }

//     public void setEmail(String email) {
//         this.email = email;
//     }

//     // Method to register the user in the database
//     public boolean register() {
//         try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
//             System.out.println("Connected to the database successfully!");

//             // SQL query to insert the user details into the "users" table
//             String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
//             PreparedStatement stmt = conn.prepareStatement(query);
//             stmt.setString(1, this.username);  // Set username
//             stmt.setString(2, this.password);  // Set password (you should hash the password in a real application)
//             stmt.setString(3, this.email);     // Set email

//             // Execute the query
//             int rowsAffected = stmt.executeUpdate();

//             // If the insertion is successful, return true
//             return rowsAffected > 0;

//         } catch (Exception e) {
//             e.printStackTrace();  // Print any exceptions that occur
//             return false;
//         }
//     }
//     public boolean login(String username, String password) {
//         String query = "SELECT * FROM users WHERE username = ? AND password = ?";
     
//         try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
//              PreparedStatement stmt = conn.prepareStatement(query)) {
     
//             stmt.setString(1, username);
//             stmt.setString(2, password);
     
//             var resultSet = stmt.executeQuery();
     
//             // Debug: Check if query returns any result
//             if (!resultSet.next()) {
//                 System.out.println("No user found with the given username and password.");
//                 return false;
//             }
     
//             return true; // User found
     
//         } catch (Exception e) {
//             e.printStackTrace();
//             return false;
//         }
//     }
    
    
// }
package model;

import java.sql.*;

public class User {
    private String username;
    private String password;
    private String email;

    private AuthenticationStrategy authenticationStrategy = new PlainTextAuthenticationStrategy(); // default

    // DB config
    private static final String URL = "jdbc:mysql://localhost:3306/eticketing";
    private static final String USER = "root";
    private static final String PASSWORD = "Grape2004";

    public User() {}

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters/setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // Allow setting strategy from outside
    public void setAuthenticationStrategy(AuthenticationStrategy strategy) {
        this.authenticationStrategy = strategy;
    }

    public boolean register() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, this.username);
            stmt.setString(2, this.password); // store as plaintext here
            stmt.setString(3, this.email);

            return stmt.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean login(String inputUsername, String inputPassword) {
        String query = "SELECT * FROM users WHERE username = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, inputUsername);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return authenticationStrategy.authenticate(inputPassword, storedPassword);
            }

            return false;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

