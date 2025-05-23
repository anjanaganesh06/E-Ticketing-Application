// package model;

// import java.sql.*;
// import database.DatabaseConnection;

// public class Passenger {
//     private int id;
//     private String name;
//     private int age;
//     private String gender;
//     private String berthPreference;
//     private String govtIdType;
//     private String govtIdNumber;
//     private String email;

//     // Constructor for newly created (inserted) passengers
//     public Passenger(String name, int age, String gender, String berthPreference,
//     String govtIdType, String govtIdNumber, String email) {
// this.name = name;
// this.age = age;
// this.gender = gender;
// this.berthPreference = berthPreference;
// this.govtIdType = govtIdType;
// this.govtIdNumber = govtIdNumber;
// this.email = email;
// }

//     // Constructor with minimal details for database retrieval
//     public Passenger(int id, String name, String email) {
//         this.id = id;
//         this.name = name;
//         this.email = email;
//         // You can set default values for the other fields or handle them accordingly
//         this.age = -1; // Default value to indicate data is missing
//         this.gender = "Not specified";
//         this.berthPreference = "Not specified";
//         this.govtIdType = "Not specified";
//         this.govtIdNumber = "Not specified";
//     }

//     // Constructor with all details
//     public Passenger(int id, String name, int age, String gender, String berthPreference,
//                      String govtIdType, String govtIdNumber, String email) {
//         this.id = id;
//         this.name = name;
//         this.age = age;
//         this.gender = gender;
//         this.berthPreference = berthPreference;
//         this.govtIdType = govtIdType;
//         this.govtIdNumber = govtIdNumber;
//         this.email = email;
//     }

//     // Getters for all the attributes
//     public int getId() { return id; }
//     public String getName() { return name; }
//     public int getAge() { return age; }
//     public String getGender() { return gender; }
//     public String getBerthPreference() { return berthPreference; }
//     public String getGovtIdType() { return govtIdType; }
//     public String getGovtIdNumber() { return govtIdNumber; }
//     public String getEmail() { return email; }

//     // Add passenger to DB
//     public static Passenger addPassenger(String name, int age, String gender, String berthPref,
//                                          String idType, String idNumber, String email) {

//         String query = "INSERT INTO passengers (name, age, gender, berth_preference, government_id_type, government_id_number, email) " +
//                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

//         try (Connection connection = DatabaseConnection.getConnection();
//              PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

//             statement.setString(1, name);
//             statement.setInt(2, age);
//             statement.setString(3, gender);
//             statement.setString(4, berthPref);
//             statement.setString(5, idType);
//             statement.setString(6, idNumber);
//             statement.setString(7, email);

//             int rows = statement.executeUpdate();

//             if (rows > 0) {
//                 try (ResultSet rs = statement.getGeneratedKeys()) {
//                     if (rs.next()) {
//                         int id = rs.getInt(1);
//                         return new Passenger(id, name, age, gender, berthPref, idType, idNumber, email);
//                     }
//                 }
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }

//         return null; // on failure
//     }

//     // Get passenger by ID from DB
//     public static Passenger getPassengerById(int id) {
//         try (Connection connection = DatabaseConnection.getConnection()) {
//             String query = "SELECT * FROM passengers WHERE id = ?";
//             try (PreparedStatement stmt = connection.prepareStatement(query)) {
//                 stmt.setInt(1, id);
//                 try (ResultSet rs = stmt.executeQuery()) {
//                     if (rs.next()) {
//                         String name = rs.getString("name");
//                         int age = rs.getInt("age");
//                         String gender = rs.getString("gender");
//                         String berthPreference = rs.getString("berth_preference");
//                         String govtIdType = rs.getString("government_id_type");
//                         String govtIdNumber = rs.getString("government_id_number");
//                         String email = rs.getString("email");
                        
//                         // Return fully constructed Passenger object
//                         return new Passenger(id, name, age, gender, berthPreference, govtIdType, govtIdNumber, email);
//                     }
//                 }
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return null;  // Return null if no passenger found
//     }
// }

package model;

import database.DatabaseConnection;
import java.sql.*;

public class Passenger {
    private int id;
    private String name;
    private int age;
    private String gender;
    private String berthPreference;
    private String govtIdType;
    private String govtIdNumber;
    private String email;

    // Constructor for newly created (inserted) passengers
    public Passenger(String name, int age, String gender, String berthPreference,
    String govtIdType, String govtIdNumber, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
        this.govtIdType = govtIdType;
        this.govtIdNumber = govtIdNumber;
        this.email = email;
    }

    // Constructor with minimal details for database retrieval
    public Passenger(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.age = -1; // Default value to indicate data is missing
        this.gender = "Not specified";
        this.berthPreference = "Not specified";
        this.govtIdType = "Not specified";
        this.govtIdNumber = "Not specified";
    }

    // Constructor with all details
    public Passenger(int id, String name, int age, String gender, String berthPreference,
                     String govtIdType, String govtIdNumber, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
        this.govtIdType = govtIdType;
        this.govtIdNumber = govtIdNumber;
        this.email = email;
    }

    // Getters for all the attributes
    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getBerthPreference() { return berthPreference; }
    public String getGovtIdType() { return govtIdType; }
    public String getGovtIdNumber() { return govtIdNumber; }
    public String getEmail() { return email; }

    // Add passenger to DB
    public static Passenger addPassenger(String name, int age, String gender, String berthPref,
                                         String idType, String idNumber, String email) {

        String query = "INSERT INTO passengers (name, age, gender, berth_preference, government_id_type, government_id_number, email) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, name);
            statement.setInt(2, age);
            statement.setString(3, gender);
            statement.setString(4, berthPref);
            statement.setString(5, idType);
            statement.setString(6, idNumber);
            statement.setString(7, email);

            int rows = statement.executeUpdate();

            if (rows > 0) {
                try (ResultSet rs = statement.getGeneratedKeys()) {
                    if (rs.next()) {
                        int id = rs.getInt(1);
                        return PassengerFactory.createPassengerForInsert(name, age, gender, berthPref, idType, idNumber, email);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // on failure
    }

    // Get passenger by ID from DB
    public static Passenger getPassengerById(int id) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM passengers WHERE id = ?";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        // Use factory to create the Passenger object from the ResultSet
                        return PassengerFactory.createPassengerFromResultSet(rs);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if no passenger found
    }
}
