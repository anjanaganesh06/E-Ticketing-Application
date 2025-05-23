package model;

import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PaymentManager {
    private static PaymentManager instance;

    private PaymentManager() {
        // Private constructor to prevent external instantiation
    }

    public static PaymentManager getInstance() {
        if (instance == null) {
            instance = new PaymentManager();
        }
        return instance;
    }

    public boolean insertPayment(Connection connection, Payment payment) {
        String query = "INSERT INTO payments (payment_mode, payment_timestamp, amount) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, payment.getMode());
            stmt.setTimestamp(2, payment.getTimestamp());
            stmt.setDouble(3, payment.getAmount());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
