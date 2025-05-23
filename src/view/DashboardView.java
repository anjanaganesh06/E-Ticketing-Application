package view;

import javax.swing.*;
import controller.UserController;
import model.User;

public class DashboardView {
    private JFrame frame;
    private JButton loginButton;
    private JButton registerButton;

    public DashboardView() {
        frame = new JFrame("E-Ticketing System - Dashboard");
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        // Set layout and add components
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.add(new JLabel("Welcome to E-Ticketing System"));
        frame.add(loginButton);
        frame.add(registerButton);

        // Set frame properties
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // Set button actions
        loginButton.addActionListener(e -> showLoginView());
        registerButton.addActionListener(e -> showRegisterView());
    }

    // Show Login View when Login button is clicked
    private void showLoginView() {
        // Hide dashboard
        frame.setVisible(false);
        
        // Show login view
        LoginView loginView = new LoginView();
        loginView.show();
    }

    // Show Register View when Register button is clicked
    private void showRegisterView() {
        // Hide dashboard
        frame.setVisible(false);
        
        // Create model, view, and controller
    User model = new User();
    RegisterView registerView = new RegisterView();
    UserController controller = new UserController(registerView, model);
    
    // Inject controller into view
    registerView.setController(controller);

    // Show the register window
    registerView.setVisible(true);
    }

    public void show() {
        frame.setVisible(true);
    }
}
