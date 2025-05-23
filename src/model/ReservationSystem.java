// package model;

// import database.DatabaseConnection;

// import java.sql.*;
// import java.util.ArrayList;
// import java.util.List;

// public class ReservationSystem {

//     // Book ticket (add to DB)
//     public boolean bookTicket(Ticket ticket) {
//         try (Connection connection = DatabaseConnection.getConnection()) {
//             String query = "INSERT INTO tickets (train_id, passenger_id, booking_date, travel_date) VALUES (?, ?, ?, ?)";
//             try (PreparedStatement stmt = connection.prepareStatement(query)) {
//                 stmt.setInt(1, ticket.getTrain().getId());
//                 stmt.setInt(2, ticket.getPassenger().getId());
//                 stmt.setTimestamp(3, new Timestamp(ticket.getBookingDate().getTime()));
//                 stmt.setTimestamp(4, new Timestamp(ticket.getTravelDate().getTime()));

//                 int rowsInserted = stmt.executeUpdate();
//                 return rowsInserted > 0;
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return false;
//     }

//     // Cancel ticket (remove from DB)
//     public boolean cancelTicket(Ticket ticket) {
//         try (Connection connection = DatabaseConnection.getConnection()) {
//             String query = "DELETE FROM tickets WHERE id = ?";
//             try (PreparedStatement stmt = connection.prepareStatement(query)) {
//                 stmt.setInt(1, ticket.getId());
//                 int rowsDeleted = stmt.executeUpdate();
//                 return rowsDeleted > 0;
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return false;
//     }

//     // Get all tickets
//     public List<Ticket> getAllTickets() {
//         List<Ticket> tickets = new ArrayList<>();
//         try (Connection connection = DatabaseConnection.getConnection()) {
//             String query = "SELECT * FROM tickets";
//             try (Statement stmt = connection.createStatement();
//                  ResultSet rs = stmt.executeQuery(query)) {

//                 while (rs.next()) {
//                     int id = rs.getInt("id");
//                     int trainId = rs.getInt("train_id");
//                     int passengerId = rs.getInt("passenger_id");
//                     Date bookingDate = new Date(rs.getTimestamp("booking_date").getTime());
// Date travelDate = new Date(rs.getTimestamp("travel_date").getTime());

                   

//                     // Assuming you have methods to get Train and Passenger by ID
//                     Train train = Train.getTrainById(trainId);
//                     Passenger passenger = Passenger.getPassengerById(passengerId);

//                     Ticket ticket = new Ticket(id, train, passenger, bookingDate, travelDate);
//                     tickets.add(ticket);
//                 }
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//         return tickets;
//     }

//     // Get all trains (simplified here, should also connect to DB ideally)
//     public List<Train> getAllTrains() {
//         return Train.getAllTrains(); // Assume this method fetches train list from DB
//     }
//     public Train getTrainById(int trainId) {
//         for (Train train : trains) {
//             if (train.getId() == trainId) {
//                 return train;  // Return the train if its ID matches
//             }
//         }
//         return null;  // Return null if no train is found with the given ID
//     }
// }
package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import database.DatabaseConnection;
import model.Train;
import model.Ticket;
import model.Passenger;
import java.util.Date;
import java.util.Arrays;
public class ReservationSystem {

    private List<Train> trains;

    // Constructor to load trains from the database
    public ReservationSystem() {
        this.trains = new ArrayList<>();
        loadTrainsFromDatabase();
    }

    // Method to load trains from the database
    private void loadTrainsFromDatabase() {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM trains";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String source = rs.getString("source");
                    String destination = rs.getString("destination");
                    String schedule = rs.getString("schedule");

                    // Convert the schedule string into a list (split by commas)
                    List<String> scheduleList = List.of(schedule.split(","));

                    // Create a Train object and add it to the trains list
                    Train train = new Train(id, name, source, destination, scheduleList);
                    trains.add(train);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to get all trains
    public List<Train> getAllTrains() {
        return trains; // Return the list of trains
    }

    // Method to get a train by its ID
    public Train getTrainById(int trainId) {
        for (Train train : trains) {
            if (train.getTrainId() == trainId) {
                return train; // Return the train if its ID matches
            }
        }
        return null; // Return null if no train is found with the given ID
    }
    public Train getTrainByIdUsingBuilder(int trainId) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
    
        try {
            conn = DatabaseConnection.getConnection();
            String sql = "SELECT * FROM trains WHERE id = ?";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, trainId);
            rs = stmt.executeQuery();
    
            if (rs.next()) {
                // Assuming 'schedule' is stored as a comma-separated string in the DB
                String scheduleStr = rs.getString("schedule");
                List<String> scheduleList = Arrays.asList(scheduleStr.split(","));
    
                // Using the Builder pattern to create the Train object
                Train train = new Train.Builder()
                    .setId(rs.getInt("id"))
                    .setName(rs.getString("name"))
                    .setSource(rs.getString("source"))
                    .setDestination(rs.getString("destination"))
                    .setSchedule(scheduleList)
                    .build();
    
                return train;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Ensure resources are closed to prevent memory leaks
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close(); // Don't forget to close the connection
            } catch (SQLException e) {
                e.printStackTrace(); // Handle closing errors if needed
            }
        }
    
        return null; // Return null if no train found
    }
    

    // Method to book a ticket
    public boolean bookTicket(Ticket ticket) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO tickets (train_id, passenger_id, booking_date, travel_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                // Insert ticket details into the database
                stmt.setInt(1, ticket.getTrain().getTrainId()); // train_id
                stmt.setInt(2, ticket.getPassenger().getId()); // passenger_id
                stmt.setTimestamp(3, new Timestamp(ticket.getBookingDate().getTime())); // booking_date
                stmt.setTimestamp(4, new Timestamp(ticket.getTravelDate().getTime())); // travel_date

                // Execute the query
                int rowsInserted = stmt.executeUpdate();
                return rowsInserted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // If ticket insertion fails
    }

    // Method to cancel a ticket and delete the associated passenger
public boolean cancelTicket(Ticket ticket) {
    try (Connection connection = DatabaseConnection.getConnection()) {
        connection.setAutoCommit(false); // Start transaction

        // Step 1: Delete the ticket
        String deleteTicketQuery = "DELETE FROM tickets WHERE id = ?";
        try (PreparedStatement deleteTicketStmt = connection.prepareStatement(deleteTicketQuery)) {
            deleteTicketStmt.setInt(1, ticket.getId());
            int rowsDeleted = deleteTicketStmt.executeUpdate();

            if (rowsDeleted > 0) {
                // Step 2: Delete the corresponding passenger
                String deletePassengerQuery = "DELETE FROM passengers WHERE id = ?";
                try (PreparedStatement deletePassengerStmt = connection.prepareStatement(deletePassengerQuery)) {
                    deletePassengerStmt.setInt(1, ticket.getPassenger().getId());
                    deletePassengerStmt.executeUpdate();
                }

                connection.commit(); // All good, commit both deletions
                return true;
            } else {
                connection.rollback(); // No ticket deleted, rollback
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}


    // Get all tickets
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection()) {
            String query = "SELECT * FROM tickets";
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    int id = rs.getInt("id");
                    int trainId = rs.getInt("train_id");
                    int passengerId = rs.getInt("passenger_id");
                    Date bookingDate = new Date(rs.getTimestamp("booking_date").getTime());
                    Date travelDate = new Date(rs.getTimestamp("travel_date").getTime());

                    // Assuming you have methods to get Train and Passenger by ID
                    Train train = getTrainById(trainId);
                    Passenger passenger = Passenger.getPassengerById(passengerId);

                    Ticket ticket = new Ticket(id, train, passenger, bookingDate, travelDate);
                    tickets.add(ticket);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }
    public Ticket getTicketById(int ticketId) {
        String query = "SELECT * FROM tickets WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, ticketId);
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                int id = rs.getInt("id");
                int trainId = rs.getInt("train_id");
                int passengerId = rs.getInt("passenger_id");
                Date bookingDate = rs.getDate("booking_date");
                Date travelDate = rs.getDate("travel_date");
    
                //  Now safe to call DB methods:
                Train train = getTrainById(trainId);
                Passenger passenger = getPassengerById(passengerId);
    
                return new Ticket(id, train, passenger, bookingDate, travelDate);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return null;  // Return null if ticket not found
    }
    

    public Passenger getPassengerById(int passengerId) {
        String query = "SELECT * FROM passengers WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
    
            stmt.setInt(1, passengerId);  // Set the passengerId parameter
            ResultSet rs = stmt.executeQuery();
    
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String gender = rs.getString("gender");
                String berthPreference = rs.getString("berth_preference");
                String govtIdType = rs.getString("government_id_type");
                String govtIdNumber = rs.getString("government_id_number");
                String email = rs.getString("email");
    
                // Assuming Passenger has a constructor like this:
                return new Passenger(id, name, age, gender, berthPreference, govtIdType, govtIdNumber, email);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if no passenger is found
    }
    
    
}
