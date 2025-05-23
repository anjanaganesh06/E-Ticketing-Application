package controller;

import model.FareConfig;
import model.Passenger;
import model.Payment;
import model.PaymentManager;
import model.ReservationSystem;
import model.Ticket;
import model.Train;
import view.UserDashboardView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import java.sql.Timestamp;
import java.sql.Statement;

import database.DatabaseConnection;

public class BookingController {

    private ReservationSystem reservationSystem;
    private UserDashboardView view;

    public BookingController(ReservationSystem reservationSystem, UserDashboardView view) {
        this.reservationSystem = reservationSystem;
        this.view = view;
    }

//      // Modify the method to accept parameters for booking
//      public void bookTicket(String name, int age, String gender, String berthPreference, 
//      String govtIdType, String govtIdNumber, String email, int trainId, Date travelDate) {

// // Create a new passenger using the data provided
// Passenger passenger = new Passenger(name, age, gender, berthPreference, govtIdType, govtIdNumber, email);

// if (passenger != null) {
// // Get the selected train (you may modify this logic to allow the user to select a train from available options)
// Train selectedTrain = reservationSystem.getTrainById(trainId);
// Date bookingDate = new Date(); // Set the booking date as current time

// // Create the ticket object with the provided details
// Ticket ticket = new Ticket(selectedTrain, passenger, bookingDate, travelDate); 

// // Book the ticket using ReservationSystem
// boolean isBooked = reservationSystem.bookTicket(ticket);

// if (isBooked) {
// view.showMessage("Booking successful! Ticket details: " + ticket.getTicketDetails());
// } else {
// view.showMessage("Booking failed. Try again.");
// }
// } 
// }
// public void bookTicket(String name, int age, String gender, String berthPreference, 
//                         String govtIdType, String govtIdNumber, String email, int trainId, Date travelDate) {

//     // Create a new passenger using the data provided
//     Passenger passenger = new Passenger(name, age, gender, berthPreference, govtIdType, govtIdNumber, email);

//     if (passenger != null) {
//         // Persist the passenger to the database (add this step)
//         boolean isPassengerSaved = savePassengerToDatabase(passenger);
        
//         if (!isPassengerSaved) {
//             view.showMessage("Failed to save passenger. Try again.");
//             return;
//         }

//         // Get the selected train
//         Train selectedTrain = reservationSystem.getTrainByIdUsingBuilder(trainId);
//         Date bookingDate = new Date(); // Set the booking date as current time

//         // Create the ticket object with the provided details
//         Ticket ticket = new Ticket(selectedTrain, passenger, bookingDate, travelDate);

//         // Book the ticket using ReservationSystem
//         boolean isBooked = reservationSystem.bookTicket(ticket);

//         if (isBooked) {
//             view.showMessage("Booking successful! Ticket details: " + ticket.getTicketDetails());
//         } else {
//             view.showMessage("Booking failed. Try again.");
//         }
//     }
// }
// public void bookTicket(String name, int age, String gender, String berthPreference, 
//                        String govtIdType, String govtIdNumber, String email, 
//                        int trainId, Date travelDate, String paymentMode) {

//     // Create the passenger object
//     Passenger passenger = new Passenger(name, age, gender, berthPreference, govtIdType, govtIdNumber, email);

//     if (passenger != null) {
//         // Save the passenger to the database
//         boolean isPassengerSaved = savePassengerToDatabase(passenger);

//         if (!isPassengerSaved) {
//             view.showMessage("Failed to save passenger. Try again.");
//             return;
//         }

//         // Get the selected train
//         Train selectedTrain = reservationSystem.getTrainByIdUsingBuilder(trainId);
//         Date bookingDate = new Date();
//         Ticket ticket = new Ticket(selectedTrain, passenger, bookingDate, travelDate);

//         try (Connection connection = DatabaseConnection.getConnection()) {
//             connection.setAutoCommit(false);

//             // 1. Insert ticket into the database
//             String ticketQuery = "INSERT INTO tickets (train_id, passenger_id, booking_date, travel_date) VALUES (?, ?, ?, ?)";
//             try (PreparedStatement ticketStmt = connection.prepareStatement(ticketQuery, Statement.RETURN_GENERATED_KEYS)) {
//                 ticketStmt.setInt(1, trainId);
//                 ticketStmt.setInt(2, passenger.getId());
//                 ticketStmt.setTimestamp(3, new Timestamp(bookingDate.getTime()));
//                 ticketStmt.setTimestamp(4, new Timestamp(travelDate.getTime()));

//                 int rows = ticketStmt.executeUpdate();

//                 if (rows > 0) {
//                     // Get the generated ticket ID
//                     ResultSet rs = ticketStmt.getGeneratedKeys();
//                     if (rs.next()) {
//                         int ticketId = rs.getInt(1);

//                         // 2. Insert payment with user-defined payment mode
//                         double fare = FareConfig.getFare(trainId);
//                         Payment payment = new Payment(paymentMode, new Timestamp(System.currentTimeMillis()), fare);

//                         // Use the PaymentManager to insert the payment
//                         boolean paymentDone = PaymentManager.getInstance().insertPayment(connection, payment);

//                         if (paymentDone) {
//                             connection.commit();
//                             view.showMessage("Booking successful! Ticket ID: " + ticketId);
//                             return;
//                         }
//                     }
//                 }
//             }

//             // Rollback if there was an error
//             connection.rollback();
//             view.showMessage("Booking or payment failed. Rolled back.");
//         } catch (SQLException e) {
//             e.printStackTrace();
//             view.showMessage("Database error during booking.");
//         }
//     }
// // }

// public void bookTicket(String name, int age, String gender, String berthPreference, 
//                        String govtIdType, String govtIdNumber, String email, 
//                        int trainId, Date travelDate, String paymentMode) {

//     // Create the passenger object
//     Passenger passenger = new Passenger(name, age, gender, berthPreference, govtIdType, govtIdNumber, email);

//     if (passenger != null) {
//         // Save the passenger to the database
//         boolean isPassengerSaved = savePassengerToDatabase(passenger);

//         if (!isPassengerSaved) {
//             view.showMessage("Failed to save passenger. Try again.");
//             return;
//         }

//         // Get the selected train
//         Train selectedTrain = reservationSystem.getTrainByIdUsingBuilder(trainId);
//         Date bookingDate = new Date();
//         Ticket ticket = new Ticket(selectedTrain, passenger, bookingDate, travelDate);

//         try (Connection connection = DatabaseConnection.getConnection()) {
//             connection.setAutoCommit(false);

//             // 1. Insert ticket into the database
//             String ticketQuery = "INSERT INTO tickets (train_id, passenger_id, booking_date, travel_date) VALUES (?, ?, ?, ?)";
//             try (PreparedStatement ticketStmt = connection.prepareStatement(ticketQuery, Statement.RETURN_GENERATED_KEYS)) {
//                 ticketStmt.setInt(1, trainId);
//                 ticketStmt.setInt(2, passenger.getId());
//                 ticketStmt.setTimestamp(3, new Timestamp(bookingDate.getTime()));
//                 ticketStmt.setTimestamp(4, new Timestamp(travelDate.getTime()));

//                 int rows = ticketStmt.executeUpdate();

//                 if (rows > 0) {
//                     // Get the generated ticket ID
//                     ResultSet rs = ticketStmt.getGeneratedKeys();
//                     if (rs.next()) {
//                         int ticketId = rs.getInt(1);  // Extract the generated ticket_id

//                         // 2. Insert payment with user-defined payment mode and ticket_id
//                         double fare = FareConfig.getFare(trainId);
//                         Payment payment = new Payment(paymentMode, new Timestamp(System.currentTimeMillis()), fare, ticketId);

//                         // Use the PaymentManager to insert the payment with the existing connection
//                         boolean paymentDone = PaymentManager.getInstance().insertPayment(connection, payment);

//                         if (paymentDone) {
//                             connection.commit();
//                             view.showMessage("Booking successful! Ticket ID: " + ticketId);
//                             return;
//                         }
//                     }
//                 }
//             }

//             // Rollback if there was an error
//             connection.rollback();
//             view.showMessage("Booking or payment failed. Rolled back.");
//         } catch (SQLException e) {
//             e.printStackTrace();
//             view.showMessage("Database error during booking.");
//         }
//     }
// }


public void bookTicket(String name, int age, String gender, String berthPreference, 
                       String govtIdType, String govtIdNumber, String email, 
                       int trainId, Date travelDate, String paymentMode) {

    Passenger passenger = new Passenger(name, age, gender, berthPreference, govtIdType, govtIdNumber, email);

    if (passenger != null) {
        boolean isPassengerSaved = savePassengerToDatabase(passenger);

        if (!isPassengerSaved) {
            view.showMessage("Failed to save passenger. Try again.");
            return;
        }

        Train selectedTrain = reservationSystem.getTrainByIdUsingBuilder(trainId);
        Date bookingDate = new Date();
        Ticket ticket = new Ticket(selectedTrain, passenger, bookingDate, travelDate);

        try (Connection connection = DatabaseConnection.getConnection()) {
            connection.setAutoCommit(false);

            // Insert the ticket into the tickets table
            String ticketQuery = "INSERT INTO tickets (train_id, passenger_id, booking_date, travel_date) VALUES (?, ?, ?, ?)";
            try (PreparedStatement ticketStmt = connection.prepareStatement(ticketQuery, Statement.RETURN_GENERATED_KEYS)) {
                ticketStmt.setInt(1, trainId);
                ticketStmt.setInt(2, passenger.getId());
                ticketStmt.setTimestamp(3, new Timestamp(bookingDate.getTime()));
                ticketStmt.setTimestamp(4, new Timestamp(travelDate.getTime()));

                int rows = ticketStmt.executeUpdate();

                if (rows > 0) {
                    ResultSet rs = ticketStmt.getGeneratedKeys();
                    if (rs.next()) {
                        int ticketId = rs.getInt(1);  // Ticket ID (just for message/debug now)

                        System.out.println("Generated Ticket ID: " + ticketId);

                        // Insert payment (without ticket_id now)
                        double fare = FareConfig.getFare(trainId);
                        Payment payment = new Payment(paymentMode, new Timestamp(System.currentTimeMillis()), fare);

                        boolean paymentDone = PaymentManager.getInstance().insertPayment(connection, payment);

                        if (paymentDone) {
                            connection.commit();
                            view.showMessage("Booking successful! Ticket ID: " + ticketId);
                            return;
                        }
                    }
                }
            }

            // Rollback in case of failure
            connection.rollback();
            view.showMessage("Booking or payment failed. Rolled back.");
        } catch (SQLException e) {
            e.printStackTrace();
            view.showMessage("Database error during booking.");
        }
    }
}
public void bookAdditionalServices(int ticketId, List<String> services) {
    String insertQuery = "INSERT INTO additional_services (ticket_id, service_type, service_name, price) VALUES (?, ?, ?, ?)";
    int totalPrice = 0;

    // Validate ticket
    Ticket ticket = reservationSystem.getTicketById(ticketId);
    if (ticket == null) {
        JOptionPane.showMessageDialog(null, "No ticket found with the given Ticket ID.");
        return;
    }

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(insertQuery)) {

        for (String service : services) {
            if (service.startsWith("Total:")) continue; // Skip total row

            // Assuming format: "Wi-Fi Pack - ₹50" or "Veg Meal - ₹120"
            String[] parts = service.split(" - ₹");
            if (parts.length == 2) {
                String name = parts[0].trim();
                int price = Integer.parseInt(parts[1].trim());
                totalPrice += price;

                // Set the ticket ID
                stmt.setInt(1, ticketId);

                // Set service type based on the service name
                String serviceType = getServiceType(name);  // Add a method to get the service type
                stmt.setString(2, serviceType);  // Set service type

                // Set service name and price
                stmt.setString(3, name);
                stmt.setInt(4, price);

                // Add to batch
                stmt.addBatch();
            }
        }

        // Execute the batch insert
        int[] result = stmt.executeBatch();

        // Show result dialog
        if (result.length > 0) {
            String summary = "Ticket ID: " + ticketId + "\n" +
                             "Items:\n" + String.join("\n", services);
            JOptionPane.showMessageDialog(null, "Services booked successfully!\n\n" + summary);
        } else {
            JOptionPane.showMessageDialog(null, "Service booking failed. Please try again.");
        }

    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error occurred while booking services: " + e.getMessage());
    }
}
private String getServiceType(String serviceName) {
    // Determine the service type based on the service name
    if (serviceName.contains("Wi-Fi")) {
        return "Wi-Fi";
    } else if (serviceName.contains("Meal")) {
        return "Pantry";
    } else if (serviceName.contains("Porter")) {
        return "Porter";
    } else {
        return "Other";  // Default if no known type is matched
    }
}


private boolean savePassengerToDatabase(Passenger passenger) {
    String query = "INSERT INTO passengers (name, age, gender, berth_preference, government_id_type, government_id_number, email) VALUES (?, ?, ?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseConnection.getConnection(); // Assuming DatabaseConnection handles connection
         PreparedStatement stmt = conn.prepareStatement(query)) {

        // Set the passenger details to the PreparedStatement
        stmt.setString(1, passenger.getName());
        stmt.setInt(2, passenger.getAge());
        stmt.setString(3, passenger.getGender());
        stmt.setString(4, passenger.getBerthPreference());
        stmt.setString(5, passenger.getGovtIdType());
        stmt.setString(6, passenger.getGovtIdNumber());
        stmt.setString(7, passenger.getEmail());

        // Execute the update and check if successful
        int rowsAffected = stmt.executeUpdate();
        return rowsAffected > 0; // Return true if passenger is saved successfully
    } catch (SQLException e) {
        e.printStackTrace(); // Log the exception (you can also add more meaningful logging)
        return false; // Return false in case of failure
    }
}


public Train getTrainById(int trainId) {
    Train train = null;
    String query = "SELECT * FROM trains WHERE id = ?";

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setInt(1, trainId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String source = rs.getString("source");
            String destination = rs.getString("destination");

            // Assuming your schedule is stored as a comma-separated string in the DB
            String scheduleStr = rs.getString("schedule");
            List<String> schedule = Arrays.asList(scheduleStr.split(","));

            train = new Train(id, name, source, destination, schedule);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return train;
}






    public void viewTrains() {
        StringBuilder trainDetails = new StringBuilder();
        for (Train train : reservationSystem.getAllTrains()) {
            trainDetails.append(train.getTrainDetails()).append("\n");
        }
        view.showMessage("Available Trains: \n" + trainDetails.toString());
    }

    public void makePayment() {
        // Simulate payment
        view.showMessage("Payment process initiated. You will be redirected to the payment gateway.");
    }
    public void bookPantryItems(int ticketId, List<String> items) {
        String insertQuery = "INSERT INTO pantry_orders (ticket_id, item_name, price) VALUES (?, ?, ?)";
        int totalPrice = 0;
    
        // Get the ticket by ID to validate it exists
        Ticket ticket = reservationSystem.getTicketById(ticketId);
    
        if (ticket == null) {
            JOptionPane.showMessageDialog(null, "No ticket found with the given Ticket ID.");
            return;
        }
    
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertQuery)) {
    
            for (String item : items) {
                if (item.startsWith("Total:")) continue; // Skip total row
    
                // Assuming item format: "Veg Meal - ₹120"
                String[] parts = item.split(" - ₹");
                if (parts.length == 2) {
                    String name = parts[0].trim();
                    int price = Integer.parseInt(parts[1].trim());
                    totalPrice += price;
    
                    stmt.setInt(1, ticketId); // ✅ Use passed ticketId directly
                    stmt.setString(2, name);
                    stmt.setInt(3, price);
                    stmt.addBatch();
                }
            }
    
            int[] result = stmt.executeBatch();
    
            if (result.length > 0) {
                String summary = "Ticket ID: " + ticketId + "\nItems:\n" + String.join("\n", items);
                JOptionPane.showMessageDialog(null, "Pantry items booked successfully!\n\n" + summary);
            } else {
                JOptionPane.showMessageDialog(null, "Pantry booking failed. Please try again.");
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error occurred while booking pantry items: " + e.getMessage());
        }
    }
    
    
    // Inside BookingController.java
public ReservationSystem getReservationSystem() {
    return this.reservationSystem;
}
public boolean cancelTicket(int ticketId) {
    try (Connection connection = DatabaseConnection.getConnection()) {
        // First delete from passengers table using the ticket's passenger_id
        String deletePassengerQuery = "DELETE FROM passengers WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(deletePassengerQuery)) {
            stmt.setInt(1, ticketId);
            stmt.executeUpdate();  // This deletes the passenger

            // Now delete the ticket
            String deleteTicketQuery = "DELETE FROM tickets WHERE id = ?";
            try (PreparedStatement stmt2 = connection.prepareStatement(deleteTicketQuery)) {
                stmt2.setInt(1, ticketId);
                int rowsDeleted = stmt2.executeUpdate();
                return rowsDeleted > 0; // Returns true if ticket was deleted successfully
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false; // In case of an error
    }
}





}
