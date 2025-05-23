// package view;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionListener;

// public class LoginView extends JFrame {
//     private JTextField usernameField;
//     private JPasswordField passwordField;
//     private JButton loginButton;
//     private JLabel messageLabel;

//     public LoginView() {
//         setTitle("Login");
//         setSize(400, 300);
//         setLocationRelativeTo(null);  // Center the frame
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

//         // Create UI components
//         usernameField = new JTextField();
//         passwordField = new JPasswordField();
//         loginButton = new JButton("Login");
//         messageLabel = new JLabel("");

//         // Set layout and add components
//         setLayout(new GridLayout(4, 1));
//         add(new JLabel("Username:"));
//         add(usernameField);
//         add(new JLabel("Password:"));
//         add(passwordField);
//         add(loginButton);
//         add(messageLabel);

//         // Set the login button action (initially empty)
//         loginButton.addActionListener(e -> handleLogin());
//     }

//     // Getters for username and password
//     public String getUsername() {
//         return usernameField.getText();
//     }

//     public String getPassword() {
//         return new String(passwordField.getPassword());
//     }

//     // Method to handle the login logic (to be handled in the main or controller)
//     private void handleLogin() {
//         String username = getUsername();
//         String password = getPassword();

//         // Here, you should add the logic to authenticate the user (like checking credentials from DB)
//         // This is just a placeholder for now
//         if (username.equals("admin") && password.equals("admin123")) {
//             // Successful login for admin, redirect to Admin Dashboard
//             messageLabel.setText("Admin Login Successful!");
//         } else if (username.equals("user") && password.equals("user123")) {
//             // Successful login for user, redirect to User Dashboard
//             messageLabel.setText("User Login Successful!");
//         } else {
//             messageLabel.setText("Invalid credentials. Please try again.");
//         }
//     }

//     // Method to set login action handler (used in Main)
//     public void setLoginAction(ActionListener listener) {
//         loginButton.addActionListener(listener);
//     }
//     public void showMessage(String message) {
//         // You can implement this to display the message in a dialog box or a label
//         System.out.println(message); // For now, it prints the message to the console
//     }
// }

package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;

    public LoginView() {
        setTitle("Login");
        setSize(400, 300);
        setLocationRelativeTo(null);  // Center the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create UI components
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        messageLabel = new JLabel("");

        // Set layout and add components
        setLayout(new GridLayout(4, 1));
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(messageLabel);

        // Set the login button action (this will be handled by the controller)
        loginButton.addActionListener(e -> handleLogin());
    }

    // Getters for username and password
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    // Method to handle login (should now call the controller's logic)
    private void handleLogin() {
        String username = getUsername();
        String password = getPassword();
        
        // Trigger the action in the controller to authenticate
        // The actual login logic will happen in the controller now
    }

    // Method to set login action handler (used in Main)
    public void setLoginAction(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    // Method to show a message (called from the controller)
    public void showMessage(String message) {
        messageLabel.setText(message);  // Display the message in the JLabel
    }
}
