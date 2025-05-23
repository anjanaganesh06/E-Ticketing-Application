package view;

import javax.swing.*;

public class BookingView {
    private JFrame frame;

    public BookingView() {
        frame = new JFrame("Booking Ticket");
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
}
