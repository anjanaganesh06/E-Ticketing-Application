package model;

import database.DatabaseConnection;
import java.sql.*;

public class PassengerFactory {

    // Factory method to create a Passenger object for adding a new passenger
    public static Passenger createPassengerForInsert(String name, int age, String gender, String berthPref,
                                                     String idType, String idNumber, String email) {
        return new Passenger(name, age, gender, berthPref, idType, idNumber, email);
    }

    // Factory method to create a Passenger object for database retrieval (with minimal details)
    public static Passenger createPassengerForRetrieve(int id, String name, String email) {
        return new Passenger(id, name, email);
    }

    // Factory method to create a fully populated Passenger object from the database
    public static Passenger createPassengerFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        String gender = rs.getString("gender");
        String berthPreference = rs.getString("berth_preference");
        String govtIdType = rs.getString("government_id_type");
        String govtIdNumber = rs.getString("government_id_number");
        String email = rs.getString("email");

        return new Passenger(id, name, age, gender, berthPreference, govtIdType, govtIdNumber, email);
    }
}
