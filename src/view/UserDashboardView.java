package view;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.util.Date;
import java.sql.Timestamp;
import controller.BookingController;
import database.DatabaseConnection;
import model.Train;
import model.Ticket; // Import the Ticket class
import java.util.List;
import model.TicketCommand;
import model.BookTicketCommand;
import model.FareConfig;
import model.Payment;
import model.PaymentManager;
import model.TicketInvoker;
public class UserDashboardView {

    private JFrame frame;
    private JButton bookTicketButton;
    private JButton cancelTicketButton;
    private JButton viewTrainsButton;
    private JButton pantryButton;
    private JLabel welcomeLabel;
    private JButton additionalServicesButton;

    private BookingController controller;

    // Default constructor (not useful alone, but can still show the UI)
    public UserDashboardView() {
        initUI(); // call the UI setup
    }
    public JFrame getFrame() {
        return frame;
    }

    // Main constructor with controller
    public UserDashboardView(BookingController controller) {
        this.controller = controller;
        initUI(); // reuse the UI setup
        addActionListeners(); // hook up actions
    }

    // Shared UI setup
    private void initUI() {
        frame = new JFrame("User Dashboard");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // Components
        welcomeLabel = new JLabel("Welcome User!", JLabel.CENTER);
        bookTicketButton = new JButton("Book Ticket");
        cancelTicketButton = new JButton("Cancel Ticket");
        viewTrainsButton = new JButton("View Train Details");
        pantryButton = new JButton("Check Pantry");
        additionalServicesButton=new JButton("AdditionalServices");


        // Add to frame
        frame.add(Box.createVerticalStrut(10));
        frame.add(welcomeLabel);
        frame.add(Box.createVerticalStrut(10));
        frame.add(bookTicketButton);
        frame.add(cancelTicketButton);
        frame.add(viewTrainsButton);
        frame.add(pantryButton);
        frame.add(additionalServicesButton);  // Make sure to add this line!

        frame.add(Box.createVerticalStrut(10));

        // Frame settings
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        System.out.println("UserDashboardView initialized.");
        System.out.println("User Dashboard is now visible.");
    }

    // Hook up button actions only if controller is present
    private void addActionListeners() {
        if (controller == null) return;

//         bookTicketButton.addActionListener(e -> {
//             try {
//                 String name = JOptionPane.showInputDialog(frame, "Enter your name:");
//                 int age = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter your age:"));
//                 String gender = JOptionPane.showInputDialog(frame, "Enter your gender:");
//                 String berthPreference = JOptionPane.showInputDialog(frame, "Enter your berth preference:");
//                 String govtIdType = JOptionPane.showInputDialog(frame, "Enter government ID type:");
//                 String govtIdNumber = JOptionPane.showInputDialog(frame, "Enter government ID number:");
//                 String email = JOptionPane.showInputDialog(frame, "Enter your email:");
//                 // Fetch trains from reservationSystem
// java.util.List<model.Train> trains = controller.getReservationSystem().getAllTrains();

// if (trains.isEmpty()) {
//     showMessage("No trains available for booking.");
//     return;
// }

// // Create an array of train names for dropdown
// String[] trainOptions = trains.stream()
//     .map(t -> "ID: " + t.getTrainId() + " - " + t.getName() + " (" + t.getSource() + " to " + t.getDestination() + ")")
//     .toArray(String[]::new);

// // Show dropdown
// String selectedTrainStr = (String) JOptionPane.showInputDialog(
//         frame,
//         "Select a train:",
//         "Train Selection",
//         JOptionPane.QUESTION_MESSAGE,
//         null,
//         trainOptions,
//         trainOptions[0]
// );

// // If user cancels
// if (selectedTrainStr == null) return;

// // Extract selected train ID
// int trainId = Integer.parseInt(selectedTrainStr.split(" ")[1]);

//                 Date travelDate = new Date(); // stubbed with current date

//                 controller.bookTicket(name, age, gender, berthPreference, govtIdType, govtIdNumber, email, trainId, travelDate);
//             } catch (Exception ex) {
//                 showMessage("Error: " + ex.getMessage());
//             }
//         });
// bookTicketButton.addActionListener(e -> {
//     try {
//         // Get passenger details
//         String name = JOptionPane.showInputDialog(frame, "Enter your name:");
//         int age = Integer.parseInt(JOptionPane.showInputDialog(frame, "Enter your age:"));
//         String gender = JOptionPane.showInputDialog(frame, "Enter your gender:");
//         String berthPreference = JOptionPane.showInputDialog(frame, "Enter your berth preference:");
//         String govtIdType = JOptionPane.showInputDialog(frame, "Enter government ID type:");
//         String govtIdNumber = JOptionPane.showInputDialog(frame, "Enter government ID number:");
//         String email = JOptionPane.showInputDialog(frame, "Enter your email:");

//         // Fetch trains from reservationSystem
//         java.util.List<model.Train> trains = controller.getReservationSystem().getAllTrains();

//         if (trains.isEmpty()) {
//             showMessage("No trains available for booking.");
//             return;
//         }

//         // Create an array of train names for dropdown
//         String[] trainOptions = trains.stream()
//             .map(t -> "ID: " + t.getTrainId() + " - " + t.getName() + " (" + t.getSource() + " to " + t.getDestination() + ")")
//             .toArray(String[]::new);

//         // Show dropdown for train selection
//         String selectedTrainStr = (String) JOptionPane.showInputDialog(
//             frame,
//             "Select a train:",
//             "Train Selection",
//             JOptionPane.QUESTION_MESSAGE,
//             null,
//             trainOptions,
//             trainOptions[0]
//         );

//         // If user cancels
//         if (selectedTrainStr == null) return;

//         // Extract selected train ID
//         int trainId = Integer.parseInt(selectedTrainStr.split(" ")[1]);

//         // Prompt for payment mode
//         String paymentMode = JOptionPane.showInputDialog(frame, "Enter payment mode (e.g., UPI, Credit Card):");

//         // Validate payment mode input
//         if (paymentMode == null || paymentMode.trim().isEmpty()) {
//             showMessage("Invalid payment mode. Please enter a valid payment mode.");
//             return;
//         }

//         // Set travel date (can be modified as per user input if necessary)
//         Date travelDate = new Date(); // stubbed with current date

//         // Call the method to book the ticket, passing the payment mode
//         controller.bookTicket(name, age, gender, berthPreference, govtIdType, govtIdNumber, email, trainId, travelDate, paymentMode);
//     } catch (Exception ex) {
//         showMessage("Error: " + ex.getMessage());
//     }
// });
// bookTicketButton.addActionListener(e -> {
//     try {
//         // Gather passenger details with validation
//         String name = JOptionPane.showInputDialog(frame, "Enter your name:");
//         if (name == null || name.trim().isEmpty()) return;

//         String ageStr = JOptionPane.showInputDialog(frame, "Enter your age:");
//         if (ageStr == null || ageStr.trim().isEmpty()) return;
//         int age = Integer.parseInt(ageStr);

//         String gender = JOptionPane.showInputDialog(frame, "Enter your gender:");
//         if (gender == null || gender.trim().isEmpty()) return;

//         String berthPreference = JOptionPane.showInputDialog(frame, "Enter your berth preference:");
//         if (berthPreference == null || berthPreference.trim().isEmpty()) return;

//         String govtIdType = JOptionPane.showInputDialog(frame, "Enter government ID type:");
//         if (govtIdType == null || govtIdType.trim().isEmpty()) return;

//         String govtIdNumber = JOptionPane.showInputDialog(frame, "Enter government ID number:");
//         if (govtIdNumber == null || govtIdNumber.trim().isEmpty()) return;

//         String email = JOptionPane.showInputDialog(frame, "Enter your email:");
//         if (email == null || email.trim().isEmpty()) return;

//         // Fetch available trains
//         java.util.List<model.Train> trains = controller.getReservationSystem().getAllTrains();
//         if (trains.isEmpty()) {
//             showMessage("No trains available for booking.");
//             return;
//         }

//         // Convert train list to string options
//         String[] trainOptions = trains.stream()
//                 .map(t -> "ID: " + t.getTrainId() + " - " + t.getName() + " (" + t.getSource() + " to " + t.getDestination() + ")")
//                 .toArray(String[]::new);

//         // Let user select a train
//         String selectedTrainStr = (String) JOptionPane.showInputDialog(
//                 frame,
//                 "Select a train:",
//                 "Train Selection",
//                 JOptionPane.QUESTION_MESSAGE,
//                 null,
//                 trainOptions,
//                 trainOptions[0]
//         );

//         if (selectedTrainStr == null) return;

//         // Extract train ID from selected option
//         int trainId = Integer.parseInt(selectedTrainStr.split(" ")[1]);
        

//         // Get payment mode
//         String paymentMode = JOptionPane.showInputDialog(frame, "Enter payment mode (e.g., UPI, Credit Card):");
//         if (paymentMode == null || paymentMode.trim().isEmpty()) {
//             showMessage("Invalid payment mode. Please enter a valid payment mode.");
//             return;
//         }

//         // Stubbed travel date with current date (can add date picker later)
//         Date travelDate = new Date();

//         // Create and execute the booking command
//         TicketCommand command = new BookTicketCommand(
//                 controller, name, age, gender, berthPreference,
//                 govtIdType, govtIdNumber, email,
//                 trainId, travelDate, paymentMode
//         );

//         TicketInvoker invoker = new TicketInvoker();
//         invoker.setCommand(command);
//         invoker.run();  // calls command.execute()

//     } catch (NumberFormatException nfe) {
//         showMessage("Invalid input. Age must be a number.");
//     } catch (Exception ex) {
//         ex.printStackTrace();
//         showMessage("Error: " + ex.getMessage());
//     }
// });
bookTicketButton.addActionListener(e -> {
    try {
        // Gather passenger details with validation
        String name = JOptionPane.showInputDialog(frame, "Enter your name:");
        if (name == null || name.trim().isEmpty()) return;

        String ageStr = JOptionPane.showInputDialog(frame, "Enter your age:");
        if (ageStr == null || ageStr.trim().isEmpty()) return;
        int age = Integer.parseInt(ageStr);

        String gender = JOptionPane.showInputDialog(frame, "Enter your gender:");
        if (gender == null || gender.trim().isEmpty()) return;

        String berthPreference = JOptionPane.showInputDialog(frame, "Enter your berth preference:");
        if (berthPreference == null || berthPreference.trim().isEmpty()) return;

        String govtIdType = JOptionPane.showInputDialog(frame, "Enter government ID type:");
        if (govtIdType == null || govtIdType.trim().isEmpty()) return;

        String govtIdNumber = JOptionPane.showInputDialog(frame, "Enter government ID number:");
        if (govtIdNumber == null || govtIdNumber.trim().isEmpty()) return;

        String email = JOptionPane.showInputDialog(frame, "Enter your email:");
        if (email == null || email.trim().isEmpty()) return;

        // Fetch available trains
        java.util.List<model.Train> trains = controller.getReservationSystem().getAllTrains();
        if (trains.isEmpty()) {
            showMessage("No trains available for booking.");
            return;
        }

        // Convert train list to string options
        String[] trainOptions = trains.stream()
                .map(t -> "ID: " + t.getTrainId() + " - " + t.getName() + " (" + t.getSource() + " to " + t.getDestination() + ")")
                .toArray(String[]::new);

        // Let user select a train
        String selectedTrainStr = (String) JOptionPane.showInputDialog(
                frame,
                "Select a train:",
                "Train Selection",
                JOptionPane.QUESTION_MESSAGE,
                null,
                trainOptions,
                trainOptions[0]
        );

        if (selectedTrainStr == null) return;

        // Extract train ID from selected option
        int trainId = Integer.parseInt(selectedTrainStr.split(" ")[1]);

        // Get payment mode
        String paymentMode = JOptionPane.showInputDialog(frame, "Enter payment mode (e.g., UPI, Credit Card):");
        if (paymentMode == null || paymentMode.trim().isEmpty()) {
            showMessage("Invalid payment mode. Please enter a valid payment mode.");
            return;
        }

        // Fetch fare using FareConfig
        double fareAmount = FareConfig.getFare(trainId);

        // Create payment object
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Payment payment = new Payment(paymentMode, timestamp, fareAmount);

        // Insert payment into DB
        Connection conn = DatabaseConnection.getConnection(); // Assuming you have this
        boolean paymentSuccess = PaymentManager.getInstance().insertPayment(conn, payment);

        if (!paymentSuccess) {
            showMessage("Payment failed. Please try again.");
            return;
        }

        // Stubbed travel date with current date (can add date picker later)
        Date travelDate = new Date();

        // Proceed to book ticket after successful payment
        TicketCommand command = new BookTicketCommand(
                controller, name, age, gender, berthPreference,
                govtIdType, govtIdNumber, email,
                trainId, travelDate, paymentMode
        );

        TicketInvoker invoker = new TicketInvoker();
        invoker.setCommand(command);
        invoker.run();  // calls command.execute()

    } catch (NumberFormatException nfe) {
        showMessage("Invalid input. Age must be a number.");
    } catch (Exception ex) {
        ex.printStackTrace();
        showMessage("Error: " + ex.getMessage());
    }
});

        
        cancelTicketButton.addActionListener(e -> {
            // Prompt user for ticket ID (could also be done with a JTextField or other UI elements)
            String ticketIdStr = JOptionPane.showInputDialog("Enter Ticket ID to cancel:");
            
            if (ticketIdStr == null || ticketIdStr.trim().isEmpty()) {
                showMessage("Ticket ID is required.");
                return;
            }
        
            try {
                int ticketId = Integer.parseInt(ticketIdStr.trim()); // Parse the ticket ID to integer
        
                // Call the cancelTicket method in the controller and pass the ticket ID
                boolean success = controller.cancelTicket(ticketId);
        
                // Show a message based on the result
                if (success) {
                    showMessage("Ticket and corresponding passenger canceled successfully.");
                } else {
                    showMessage("Failed to cancel the ticket. Please try again.");
                }
        
            } catch (NumberFormatException ex) {
                showMessage("Invalid Ticket ID. Please enter a valid number.");
            }
        });
        
        viewTrainsButton.addActionListener(e -> controller.viewTrains());
        pantryButton.addActionListener(e -> {
            System.out.println("Pantry button clicked!");  // Debugging line

            String[] items = {
                "Veg Meal - ₹120",
                "Non-Veg Meal - ₹150",
                "Sandwich - ₹60",
                "Tea - ₹15",
                "Coffee - ₹20"
            };
        
            JList<String> itemList = new JList<>(items);
            itemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            JScrollPane scrollPane = new JScrollPane(itemList);
            scrollPane.setPreferredSize(new Dimension(250, 120));
        
            int result = JOptionPane.showConfirmDialog(
                frame,
                scrollPane,
                "Select Pantry Items",
                JOptionPane.OK_CANCEL_OPTION
            );
        
            if (result == JOptionPane.OK_OPTION) {
                List<String> selectedItems = itemList.getSelectedValuesList();
                if (selectedItems.isEmpty()) {
                    showMessage("No items selected.");
                    return;
                }
        
                String input = prompt("Enter your Ticket ID:");
                if (input == null || input.trim().isEmpty()) {
                    showMessage("Ticket ID is required to book pantry items.");
                    return;
                }
        
                try {
                    int ticketId = Integer.parseInt(input.trim());
                    controller.bookPantryItems(ticketId, selectedItems);
                } catch (NumberFormatException ex) {
                    showMessage("Invalid Ticket ID format.");
                }
            }
        });
        additionalServicesButton.addActionListener(e -> {
            System.out.println("Additional Services button clicked!");  // Debugging line
        
            String[] services = {
                "Luggage Carrier - ₹100",
                "Wheelchair for Senior Citizen - ₹0",
                "Assistance at Platform - ₹50",
                "Priority Boarding - ₹75"
            };
        
            JList<String> serviceList = new JList<>(services);
            serviceList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            JScrollPane scrollPane = new JScrollPane(serviceList);
            scrollPane.setPreferredSize(new Dimension(250, 120));
        
            int result = JOptionPane.showConfirmDialog(
                frame,
                scrollPane,
                "Select Additional Services",
                JOptionPane.OK_CANCEL_OPTION
            );
        
            if (result == JOptionPane.OK_OPTION) {
                List<String> selectedServices = serviceList.getSelectedValuesList();
                if (selectedServices.isEmpty()) {
                    showMessage("No services selected.");
                    return;
                }
        
                String input = prompt("Enter your Ticket ID:");
                if (input == null || input.trim().isEmpty()) {
                    showMessage("Ticket ID is required to book additional services.");
                    return;
                }
        
                try {
                    int ticketId = Integer.parseInt(input.trim());
                    controller.bookAdditionalServices(ticketId, selectedServices);
                } catch (NumberFormatException ex) {
                    showMessage("Invalid Ticket ID format.");
                }
            }
        });
        
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
    public String prompt(String message) {
        return JOptionPane.showInputDialog(null, message);
    }
    
    
}
