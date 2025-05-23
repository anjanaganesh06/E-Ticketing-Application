// // import controller.BookingController;
// // import controller.UserController;
// // import model.ReservationSystem;
// // import model.User;
// // import view.*;

// // public class Main {
// //     public static void main(String[] args) {
// //         showLandingView();
// //     }

// //     private static void showLandingView() {
// //         LandingView landingView = new LandingView();

// //         landingView.setRegisterAction(e -> {
// //             landingView.dispose(); // Close the landing view
// //             showRegisterView();
// //         });

// //         landingView.setLoginAction(e -> {
// //             landingView.dispose(); // Close the landing view
// //             showLoginView();
// //         });

// //         landingView.setVisible(true);
// //     }

// //     private static void showRegisterView() {
// //         User model = new User();
// //         RegisterView registerView = new RegisterView();
// //         UserController userController = new UserController(registerView, model);
// //         registerView.setController(userController);

// //         registerView.setOnRegisterComplete(() -> {
// //             System.out.println("Registration complete. Proceeding to Login.");
// //             registerView.dispose();
// //             showLoginView();
// //         });

// //         registerView.setVisible(true);
// //     }

// //     private static void showLoginView() {
// //         LoginView loginView = new LoginView();

// //         loginView.setLoginAction(e -> {
// //             String username = loginView.getUsername();
// //             String password = loginView.getPassword();
// //             boolean isValidLogin = validateLogin(username, password);
            
// // // You must create ReservationSystem and controller before
// // ReservationSystem reservationSystem = new ReservationSystem(); // Assuming you have this class
// // UserDashboardView userDashboardView = new UserDashboardView(); // TEMP view to pass to controller
// // BookingController bookingController = new BookingController(reservationSystem, userDashboardView);

// // // Now create the full view
// // UserDashboardView fullDashboard = new UserDashboardView(bookingController);

// //             if (isValidLogin) {
// //                 if (username.equals("admin")) {
// //                     System.out.println("Login successful! Redirecting to Admin Dashboard...");
// //                     new AdminDashboardView().setVisible(true);
// //                 } else {
// //                     System.out.println("Login successful! Redirecting to User Dashboard...");
// //                     new UserDashboardView().setVisible(true);
// //                 }
// //                 loginView.dispose();
// //             } else {
// //                 System.out.println("Invalid username or password.");
// //                 loginView.showMessage("Invalid username or password. Please try again.");
// //             }
// //         });

// //         loginView.setVisible(true);
// //     }

// //     private static boolean validateLogin(String username, String password) {
// //         return (username.equals("admin") && password.equals("admin123")) ||
// //                (username.equals("user") && password.equals("user123"));
// //     }
// // }

// // import controller.UserController;
// // import controller.BookingController;
// // import model.ReservationSystem;
// // import model.User;
// // import view.RegisterView;
// // import view.LoginView;
// // import view.UserDashboardView;
// // import view.AdminDashboardView;

// // import javax.swing.*;

// // public class Main {
// //     public static void main(String[] args) {
// //         // Show initial option dialog
// //         String[] options = {"Login", "Register"};
// //         int choice = JOptionPane.showOptionDialog(
// //                 null,
// //                 "Welcome to the E-Ticketing System. What would you like to do?",
// //                 "E-Ticketing",
// //                 JOptionPane.DEFAULT_OPTION,
// //                 JOptionPane.QUESTION_MESSAGE,
// //                 null,
// //                 options,
// //                 options[0]
// //         );

// //         if (choice == 0) {
// //             // User chose Login
// //             showLoginView();
// //         } else if (choice == 1) {
// //             // User chose Register
// //             showRegisterView();
// //         } else {
// //             // Closed or Cancelled
// //             System.out.println("User closed the dialog. Exiting...");
// //             System.exit(0);
// //         }
// //     }

// //     // Show Register View and handle transition
// //     private static void showRegisterView() {
// //         User model = new User();
// //         RegisterView registerView = new RegisterView();
// //         UserController userController = new UserController(registerView, model);
// //         registerView.setController(userController);

// //         registerView.setOnRegisterComplete(() -> {
// //             System.out.println("Registration complete. Proceeding to Login.");
// //             showLoginView();
// //         });

// //         registerView.setVisible(true);
// //     }

// //     // Show login view and handle login logic
// //     private static void showLoginView() {
// //         LoginView loginView = new LoginView();

// //         loginView.setLoginAction(e -> {
// //             String username = loginView.getUsername();
// //             String password = loginView.getPassword();

// //             boolean isValidLogin = validateLogin(username, password);

// //             if (isValidLogin) {
// //                 loginView.setVisible(false);

// //                 if (username.equals("admin")) {
// //                     System.out.println("Login successful! Redirecting to Admin Dashboard...");
// //                     AdminDashboardView adminDashboardView = new AdminDashboardView();
// //                     adminDashboardView.setVisible(true);
// //                 } else {
// //                     System.out.println("Login successful! Redirecting to User Dashboard...");

// //                     // ✅ Create shared reservation system
// //                     ReservationSystem reservationSystem = new ReservationSystem();

// //                     // ✅ Create the view first (to pass into controller)
// //                     UserDashboardView userDashboardView = new UserDashboardView();

// //                     // ✅ Create the controller with both reservation system and view
// //                     BookingController bookingController = new BookingController(reservationSystem, userDashboardView);

// //                     // ✅ Re-link the controller to the view
// //                     userDashboardView = new UserDashboardView(bookingController);

// //                     // ✅ Show dashboard
// //                     userDashboardView.setVisible(true);
// //                 }

// //             } else {
// //                 System.out.println("Invalid username or password. Please try again.");
// //                 loginView.showMessage("Invalid username or password. Please try again.");
// //             }
// //         });

// //         loginView.setVisible(true);
// //     }

// //     // Mocked login logic — replace with actual DB validation
// //     private static boolean validateLogin(String username, String password) {
// //         return (username.equals("admin") && password.equals("admin123")) ||
// //                (username.equals("user") && password.equals("user123"));
// //     }
// // // }
// // import controller.UserController;
// // import controller.BookingController;
// // import model.ReservationSystem;
// // import model.User;
// // import view.RegisterView;
// // import view.LoginView;
// // import view.UserDashboardView;
// // import view.AdminDashboardView;

// // import javax.swing.*;
// // import java.awt.*;
// // import java.awt.event.ActionEvent;

// // public class Main {
// //     public static void main(String[] args) {
// //         // Show Welcome Page with buttons for Login and Register
// //         JFrame welcomeFrame = new JFrame("Welcome to the E-Ticketing System");
// //         welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
// //         welcomeFrame.setSize(400, 200);
// //         welcomeFrame.setLocationRelativeTo(null); // Center the window

// //         // Create a panel for layout
// //         JPanel panel = new JPanel();
// //         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

// //         // Create a welcome message
// //         JLabel welcomeLabel = new JLabel("Welcome to the E-Ticketing System!", JLabel.CENTER);
// //         welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));

// //         // Create Login and Register buttons
// //         JButton loginButton = new JButton("Login");
// //         JButton registerButton = new JButton("Register");

// //         // Add actions to buttons
// //         loginButton.addActionListener((ActionEvent e) -> {
// //             welcomeFrame.setVisible(false);
// //             showLoginView();
// //         });

// //         registerButton.addActionListener((ActionEvent e) -> {
// //             welcomeFrame.setVisible(false);
// //             showRegisterView();
// //         });

// //         // Add components to the panel
// //         panel.add(welcomeLabel);
// //         panel.add(Box.createVerticalStrut(20)); // Add spacing
// //         panel.add(loginButton);
// //         panel.add(Box.createVerticalStrut(10)); // Add spacing
// //         panel.add(registerButton);

// //         // Add panel to the frame and make the window visible
// //         welcomeFrame.add(panel);
// //         welcomeFrame.setVisible(true);
// //     }

// //     // Show Register View and handle transition
// //     private static void showRegisterView() {
// //         User model = new User();
// //         RegisterView registerView = new RegisterView();
// //         UserController userController = new UserController(registerView, model);
// //         registerView.setController(userController);

// //         registerView.setOnRegisterComplete(() -> {
// //             System.out.println("Registration complete. Proceeding to Login.");
// //             showLoginView();
// //         });

// //         registerView.setVisible(true);
// //     }

// //     // Show login view and handle login logic
// //     private static void showLoginView() {
// //         LoginView loginView = new LoginView();

// //         loginView.setLoginAction(e -> {
// //             String username = loginView.getUsername();
// //             String password = loginView.getPassword();

// //             boolean isValidLogin = validateLogin(username, password);

// //             if (isValidLogin) {
// //                 loginView.setVisible(false);

// //                 if (username.equals("admin")) {
// //                     System.out.println("Login successful! Redirecting to Admin Dashboard...");
// //                     AdminDashboardView adminDashboardView = new AdminDashboardView();
// //                     adminDashboardView.setVisible(true);
// //                 } else {
// //                     System.out.println("Login successful! Redirecting to User Dashboard...");

// //                     // ✅ Create shared reservation system
// //                     ReservationSystem reservationSystem = new ReservationSystem();

// //                     // ✅ Create the view first (to pass into controller)
// //                     UserDashboardView userDashboardView = new UserDashboardView();

// //                     // ✅ Create the controller with both reservation system and view
// //                     BookingController bookingController = new BookingController(reservationSystem, userDashboardView);

// //                     // ✅ Re-link the controller to the view
// //                     userDashboardView = new UserDashboardView(bookingController);

// //                     // ✅ Show dashboard
// //                     userDashboardView.setVisible(true);
// //                 }

// //             } else {
// //                 System.out.println("Invalid username or password. Please try again.");
// //                 loginView.showMessage("Invalid username or password. Please try again.");
// //             }
// //         });

// //         loginView.setVisible(true);
// //     }

// //     // Mocked login logic — replace with actual DB validation
// //     private static boolean validateLogin(String username, String password) {
// //         return (username.equals("admin") && password.equals("admin123")) ||
// //                (username.equals("user") && password.equals("user123"));
// //     }
// // }
// import controller.UserController;
// import controller.AdminController;
// import controller.BookingController;
// import model.ReservationSystem;
// import model.User;
// import view.RegisterView;
// import view.LoginView;
// import view.UserDashboardView;
// import view.AdminDashboard; // Ensure this import is added
// import view.AdminDashboard;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.net.URL;
// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.PreparedStatement;

// public class Main {
//     public static void main(String[] args) {
//         // Show Welcome Page with buttons for Login and Register
//         JFrame welcomeFrame = new JFrame("Welcome to the E-Ticketing System");
//         welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         welcomeFrame.setSize(600, 400);
//         welcomeFrame.setLocationRelativeTo(null); // Center the window

//         // Create a custom JPanel to draw the background
//         JPanel panel = new BackgroundPanel();
//         panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

//         // Create a welcome message and center it
//         JLabel welcomeLabel = new JLabel("Welcome to the E-Ticketing System!", JLabel.CENTER);
//         welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
//         welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the label

//         // Create Login and Register buttons
//         JButton loginButton = new JButton("Login");
//         JButton registerButton = new JButton("Register");

//         // Add actions to buttons
//         loginButton.addActionListener((ActionEvent e) -> {
//             welcomeFrame.setVisible(false);
//             showLoginView();
//         });

//         registerButton.addActionListener((ActionEvent e) -> {
//             welcomeFrame.setVisible(false);
//             showRegisterView();
//         });

//         // Add components to the panel
//         panel.add(welcomeLabel);
//         panel.add(Box.createVerticalStrut(50)); // Add spacing
//         panel.add(loginButton);
//         panel.add(Box.createVerticalStrut(10)); // Add spacing
//         panel.add(registerButton);

//         // Add panel to the frame and make the window visible
//         welcomeFrame.add(panel);
//         welcomeFrame.setVisible(true);
//     }

//     // Show Register View and handle transition
//     private static void showRegisterView() {
//         User model = new User();
//         RegisterView registerView = new RegisterView();
//         UserController userController = new UserController(registerView, model);
//         registerView.setController(userController);

//         registerView.setOnRegisterComplete(() -> {
//             System.out.println("Registration complete. Proceeding to Login.");
//             showLoginView();
//         });

//         registerView.setVisible(true);
//     }

//     private static void showLoginView() {
//         LoginView loginView = new LoginView();
//         User userModel = new User(); // Create model instance
    
//         loginView.setLoginAction(e -> {
//             String username = loginView.getUsername();
//             String password = loginView.getPassword();
    
//             // Use DB login check instead of mock
//             boolean isValidLogin = userModel.login(username, password);
    
//             if (isValidLogin) {
//                 loginView.setVisible(false);
    
//                 if (username.equals("admin")) {
//                     System.out.println("Login successful! Redirecting to Admin Dashboard...");
//                     AdminDashboard adminDashboardView = new AdminDashboard();
//                     AdminController adminController = new AdminController(adminDashboardView);
//                     adminDashboardView.setVisible(true);
//                 } else {
//                     System.out.println("Login successful! Redirecting to User Dashboard...");
    
//                     ReservationSystem reservationSystem = new ReservationSystem();
//                     UserDashboardView userDashboardView = new UserDashboardView();
//                     BookingController bookingController = new BookingController(reservationSystem, userDashboardView);
//                     userDashboardView = new UserDashboardView(bookingController);
//                     userDashboardView.setVisible(true);
//                 }
    
//             } else {
//                 System.out.println("Invalid username or password. Please try again.");
//                 loginView.showMessage("Invalid username or password. Please try again.");
//             }
//         });
    
//         loginView.setVisible(true);
//     }
    

//     // Method to authenticate user from the database
// public boolean login(String username, String password) {
//     try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
//         String query = "SELECT * FROM users WHERE username = ? AND password = ?";
//         PreparedStatement stmt = conn.prepareStatement(query);
//         stmt.setString(1, username);
//         stmt.setString(2, password);

//         var rs = stmt.executeQuery();
//         return rs.next(); // If a result exists, login is valid

//     } catch (Exception e) {
//         e.printStackTrace();
//         return false;
//     }
// }


//     // Custom JPanel to add the background image
//     static class BackgroundPanel extends JPanel {
//         private Image backgroundImage;

//         public BackgroundPanel() {
//             try {
//                 // Load image from resources or URL
//                 URL imageUrl = getClass().getClassLoader().getResource("/train.jpg");

//                 if (imageUrl != null) {
//                     backgroundImage = new ImageIcon(imageUrl).getImage();
//                 }
//             } catch (Exception e) {
//                 System.out.println("Error loading image: " + e.getMessage());
//             }
//         }

//         @Override
//         protected void paintComponent(Graphics g) {
//             super.paintComponent(g);
//             if (backgroundImage != null) {
//                 // Draw the image to fill the entire panel
//                 g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
//             }
//         }
//     }
// }

import controller.UserController;
import controller.AdminController;
import controller.BookingController;
import model.ReservationSystem;
import model.User;
import view.RegisterView;
import view.LoginView;
import view.UserDashboardView;
import view.AdminDashboard;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Main {
    public static void main(String[] args) {
        // Show Welcome Page with buttons for Login and Register
         JFrame welcomeFrame = new JFrame("Welcome to the E-Ticketing System");
        welcomeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcomeFrame.setSize(600, 400);
        welcomeFrame.setLocationRelativeTo(null); // Center the window

        // Create a custom JPanel to draw the background
        JPanel panel = new BackgroundPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Welcome message
        JLabel welcomeLabel = new JLabel("Welcome to the E-Ticketing System!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.addActionListener((ActionEvent e) -> {
            welcomeFrame.setVisible(false);
            showLoginView();
        });

        registerButton.addActionListener((ActionEvent e) -> {
            welcomeFrame.setVisible(false);
            showRegisterView();
        });

        panel.add(Box.createVerticalStrut(30));
        panel.add(welcomeLabel);
        panel.add(Box.createVerticalStrut(50));
        panel.add(loginButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(registerButton);

        welcomeFrame.add(panel);
        welcomeFrame.setVisible(true);
    }

    // Show Register View and handle transition
    private static void showRegisterView() {
        User model = new User();
        RegisterView registerView = new RegisterView();
        UserController userController = new UserController(registerView, model);
        registerView.setController(userController);

        registerView.setOnRegisterComplete(() -> {
            System.out.println("Registration complete. Proceeding to Login.");
            showLoginView();
        });

        registerView.setVisible(true);
    }

    // Show Login View and handle login logic
    private static void showLoginView() {
        LoginView loginView = new LoginView();
        User userModel = new User(); // DB-connected model

        loginView.setLoginAction(e -> {
            String username = loginView.getUsername();
            String password = loginView.getPassword();

            boolean isValidLogin = userModel.login(username, password); // DB validation

            if (isValidLogin) {
                loginView.setVisible(false);

                if (username.equals("admin")) {
                    System.out.println("Login successful! Redirecting to Admin Dashboard...");
                    AdminDashboard adminDashboard = new AdminDashboard();
                    AdminController adminController = new AdminController(adminDashboard);
                    adminDashboard.setVisible(true);
                } else {
                    System.out.println("Login successful! Redirecting to User Dashboard...");

                    ReservationSystem reservationSystem = new ReservationSystem();
                    UserDashboardView userDashboardView = new UserDashboardView();
                    BookingController bookingController = new BookingController(reservationSystem, userDashboardView);
                    userDashboardView = new UserDashboardView(bookingController);
                    userDashboardView.setVisible(true);
                }

            } else {
                System.out.println("Invalid username or password. Please try again.");
                loginView.showMessage("Invalid username or password. Please try again.");
            }
        });

        loginView.setVisible(true);
    }

    // Background panel with image
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel() {
            try {
                // You can also directly use: new ImageIcon("train.jpg").getImage() if it's in the root
                java.net.URL imageUrl = getClass().getClassLoader().getResource("train.jpg");
                if (imageUrl != null) {
                    backgroundImage = new ImageIcon(imageUrl).getImage();
                } else {
                    System.out.println("Image not found: train.jpg");
                }
            } catch (Exception e) {
                System.out.println("Error loading background image: " + e.getMessage());
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
