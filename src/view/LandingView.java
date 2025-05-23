package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class LandingView extends JFrame {
    private JButton registerButton;
    private JButton loginButton;

    public LandingView() {
        setTitle("Welcome to E-Ticketing System");
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        registerButton = new JButton("Register");
        loginButton = new JButton("Login");

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.add(registerButton);
        panel.add(loginButton);

        add(panel, BorderLayout.CENTER);
    }

    public void setRegisterAction(ActionListener action) {
        registerButton.addActionListener(action);
    }

    public void setLoginAction(ActionListener action) {
        loginButton.addActionListener(action);
    }
}
