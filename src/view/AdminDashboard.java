package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class AdminDashboard extends JFrame {
    private JFrame frame;
    private JButton addTrainButton;
    private JButton deleteTrainButton;
    private JButton updateScheduleButton;
 
    private JLabel header;

    public AdminDashboard() {
        frame = new JFrame("Admin Dashboard");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(5, 1, 10, 10));

        header = new JLabel("Admin Control Panel", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 18));

        addTrainButton = new JButton("Add Train");
        deleteTrainButton = new JButton("Delete Train");
        updateScheduleButton = new JButton("Update Train Schedule");

        frame.add(header);
        frame.add(addTrainButton);
        frame.add(deleteTrainButton);
        frame.add(updateScheduleButton);

        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }

    // 👇 These methods are used in AdminController
    public JFrame getFrame() {
        return frame;
    }

    public void setAddTrainAction(ActionListener listener) {
        addTrainButton.addActionListener(listener);
    }

    public void setDeleteTrainAction(ActionListener listener) {
        deleteTrainButton.addActionListener(listener);
    }

    public void setUpdateScheduleAction(ActionListener listener) {
        updateScheduleButton.addActionListener(listener);
    }

   

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
