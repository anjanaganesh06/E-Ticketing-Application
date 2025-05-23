// package model;



// import java.sql.Connection;
// import java.sql.PreparedStatement;
// import java.sql.ResultSet;
// import java.sql.SQLException;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.List;
// import java.sql.Statement;

// import database.DatabaseConnection;

// public class Train {
//     private int id;
//     private String name;
//     private String source;
//     private String destination;
//     private List<String> schedule; // Could be ["Mon 10:00", "Tue 14:00", etc.]

//     // Constructor
//     public Train(int id, String name, String source, String destination, List<String> schedule) {
//         this.id = id;
//         this.name = name;
//         this.source = source;
//         this.destination = destination;
//         this.schedule = schedule;
//     }

//     // Getters
//     public int getId() {
//         return id;
//     }

//     public String getName() {
//         return name;
//     }

//     public String getSource() {
//         return source;
//     }

//     public String getDestination() {
//         return destination;
//     }

//     public List<String> getSchedule() {
//         return schedule;
//     }
//     public int getTrainId() {
//         return id;
//     }

//     // Setters
//     public void setName(String name) {
//         this.name = name;
//     }

//     public void setSource(String source) {
//         this.source = source;
//     }

//     public void setDestination(String destination) {
//         this.destination = destination;
//     }

//     public void setSchedule(List<String> schedule) {
//         this.schedule = schedule;
//     }

//     // Display formatted train info
//     public String getTrainDetails() {
//         return "Train ID: " + id +
//                "\nName: " + name +
//                "\nRoute: " + source + " -> " + destination +
//                "\nSchedule: " + String.join(", ", schedule);
//     }

//     // Equality check (useful when selecting/canceling trains)
//     @Override
//     public boolean equals(Object obj) {
//         if (this == obj) return true;
//         if (!(obj instanceof Train)) return false;
//         Train other = (Train) obj;
//         return this.id == other.id;
//     }

//     @Override
//     public int hashCode() {
//         return id;
//     }
//       public static Train getTrainById(int trainId) {
//         try (Connection connection = DatabaseConnection.getConnection()) {
//             String query = "SELECT * FROM trains WHERE id = ?";
//             try (PreparedStatement stmt = connection.prepareStatement(query)) {
//                 stmt.setInt(1, trainId);
//                 try (ResultSet rs = stmt.executeQuery()) {
//                     if (rs.next()) {
//                         return new Train(
//                                 rs.getInt("id"),
//                                 rs.getString("name"),
//                                 rs.getString("source"),
//                                 rs.getString("destination"),
//                                 Arrays.asList(rs.getString("schedule").split(","))
//                         );
//                     }
//                 }
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return null;
//     }

//     // Get all trains
//     public static List<Train> getAllTrains() {
//         List<Train> trains = new ArrayList<>();
//         try (Connection connection = DatabaseConnection.getConnection()) {
//             String query = "SELECT * FROM trains";
//             try (Statement stmt = connection.createStatement();
//                  ResultSet rs = stmt.executeQuery(query)) {

//                 while (rs.next()) {
//                     Train train = new Train(
//                             rs.getInt("id"),
//                             rs.getString("name"),
//                             rs.getString("source"),
//                             rs.getString("destination"),
//                             Arrays.asList(rs.getString("schedule").split(","))
//                     );
//                     trains.add(train);
//                 }
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return trains;
//     }
// }

// package model;
package model;
import java.util.List;
import java.util.List;
import java.util.Objects;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Statement;

import database.DatabaseConnection;

public class Train {
    private final int id;
    private final String name;
    private final String source;
    private final String destination;
    private final List<String> schedule;

    public Train(int id, String name, String source, String destination, List<String> schedule) {
        this.id = id;
        this.name = name;
        this.source = source;
        this.destination = destination;
        this.schedule = schedule;
    }

    // Getters only (no setters)
    public int getTrainId() { return id; }
    public String getName() { return name; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public List<String> getSchedule() { return schedule; }

    public String getTrainDetails() {
        return "Train ID: " + id +
               "\nName: " + name +
               "\nRoute: " + source + " -> " + destination +
               "\nSchedule: " + String.join(", ", schedule);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Train)) return false;
        Train other = (Train) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public static class Builder {
        private int id;
        private String name;
        private String source;
        private String destination;
        private List<String> schedule;

        public Builder setId(int id) { this.id = id; return this; }
        public Builder setName(String name) { this.name = name; return this; }
        public Builder setSource(String source) { this.source = source; return this; }
        public Builder setDestination(String destination) { this.destination = destination; return this; }
        public Builder setSchedule(List<String> schedule) { this.schedule = schedule; return this; }

        public Train build() {
            return new Train(id, name, source, destination, schedule);
        }
    }
}
