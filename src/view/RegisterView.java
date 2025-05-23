package view;

import javax.swing.*;
import java.awt.*;
import controller.UserController;

public class RegisterView extends JFrame {
    private JTextField usernameField, emailField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private UserController controller;
    private RegisterCompleteListener registerCompleteListener;  // Add a listener field
   

public interface RegisterCompleteListener {
    void onRegisterComplete();
}



    public RegisterView() {
        setTitle("User Registration");
        setLayout(new GridLayout(4, 2, 10, 10));

        // Form elements
        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        registerButton = new JButton("Register");
        add(new JLabel()); // Empty label to align the button
        add(registerButton);

        // Button Action
        registerButton.addActionListener(e -> {
            System.out.println("Register button clicked!");
            if (controller != null) {
                System.out.println("Calling controller.registerUser()");
                controller.registerUser();
            } else {
                System.out.println("Controller is null");
            }
        });

        // Frame settings
        setSize(400, 200);
        setLocationRelativeTo(null); // Center the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // Called from DashboardView to inject controller
    public void setController(UserController controller) {
        this.controller = controller;
    }

    // Get user input
    public String getUsername() {
        return usernameField.getText();
    }

    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    public String getEmail() {
        return emailField.getText();
    }
    public void setOnRegisterComplete(RegisterCompleteListener listener) {
        this.registerCompleteListener = listener;
    }

    // Show message dialog
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}
