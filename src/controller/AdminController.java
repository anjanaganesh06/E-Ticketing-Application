package controller;

import model.Train;
import view.AdminDashboard;
import database.DatabaseConnection;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

public class AdminController {
    private final AdminDashboard view;

    public AdminController(AdminDashboard view) {
        this.view = view;
        addActionListeners();
    }

    private void addActionListeners() {
        view.setAddTrainAction(e -> addTrain());
        view.setDeleteTrainAction(e -> deleteTrain());
        view.setUpdateScheduleAction(e -> updateSchedule());
    }

    public void addTrain() {
        String name = JOptionPane.showInputDialog(view.getFrame(), "Enter Train Name:");
        String source = JOptionPane.showInputDialog(view.getFrame(), "Enter Source:");
        String destination = JOptionPane.showInputDialog(view.getFrame(), "Enter Destination:");
        String scheduleInput = JOptionPane.showInputDialog(view.getFrame(), "Enter Schedule (comma-separated, e.g., Mon 10:00,Wed 15:00):");

        if (name == null || source == null || destination == null || scheduleInput == null) {
            view.showMessage("Cancelled or incomplete details.");
            return;
        }

        List<String> schedule = Arrays.asList(scheduleInput.split(","));
        StringJoiner joiner = new StringJoiner(",");
        for (String s : schedule) {
            joiner.add(s.trim());
        }

        String query = "INSERT INTO trains (name, source, destination, schedule) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, name);
            stmt.setString(2, source);
            stmt.setString(3, destination);
            stmt.setString(4, joiner.toString());

            int rows = stmt.executeUpdate();
            view.showMessage(rows > 0 ? "Train added successfully!" : "Failed to add train.");
        } catch (SQLException e) {
            e.printStackTrace();
            view.showMessage("Database error while adding train.");
        }
    }

    public void deleteTrain() {
        String idStr = JOptionPane.showInputDialog(view.getFrame(), "Enter Train ID to delete:");
        try {
            int trainId = Integer.parseInt(idStr);
            String query = "DELETE FROM trains WHERE id = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, trainId);
                int rows = stmt.executeUpdate();
                view.showMessage(rows > 0 ? "Train deleted successfully!" : "Train not found.");
            }
        } catch (NumberFormatException e) {
            view.showMessage("Invalid train ID.");
        } catch (SQLException e) {
            e.printStackTrace();
            view.showMessage("Database error while deleting train.");
        }
    }

    public void updateSchedule() {
        String idStr = JOptionPane.showInputDialog(view.getFrame(), "Enter Train ID to update schedule:");
        try {
            int trainId = Integer.parseInt(idStr);
            String newSchedule = JOptionPane.showInputDialog(view.getFrame(), "Enter new schedule (comma-separated):");

            if (newSchedule == null || newSchedule.isBlank()) {
                view.showMessage("No schedule provided.");
                return;
            }

            List<String> schedule = Arrays.asList(newSchedule.split(","));
            StringJoiner joiner = new StringJoiner(",");
            for (String s : schedule) {
                joiner.add(s.trim());
            }

            String query = "UPDATE trains SET schedule = ? WHERE id = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, joiner.toString());
                stmt.setInt(2, trainId);
                int rows = stmt.executeUpdate();
                view.showMessage(rows > 0 ? "Schedule updated successfully!" : "Train not found.");
            }
        } catch (NumberFormatException e) {
            view.showMessage("Invalid train ID.");
        } catch (SQLException e) {
            e.printStackTrace();
            view.showMessage("Database error while updating schedule.");
        }
    }

    public void processRefund() {
        String ticketIdStr = JOptionPane.showInputDialog(view.getFrame(), "Enter Ticket ID for refund:");
        try {
            int ticketId = Integer.parseInt(ticketIdStr);
            String query = "UPDATE tickets SET refunded = TRUE WHERE id = ?";

            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, ticketId);
                int rows = stmt.executeUpdate();
                view.showMessage(rows > 0 ? "Refund processed successfully." : "Refund failed or ticket not found.");
            }
        } catch (NumberFormatException e) {
            view.showMessage("Invalid Ticket ID.");
        } catch (SQLException e) {
            e.printStackTrace();
            view.showMessage("Database error while processing refund.");
        }
    }
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}
