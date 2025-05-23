package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import database.DatabaseConnection;

public class Ticket {
    private int id;
    private Train train;
    private Passenger passenger;
    private Date bookingDate;
    private Date travelDate;

    // Constructor without ID (e.g., before saving to DB)
    public Ticket(Train train, Passenger passenger, Date bookingDate, Date travelDate) {
        this.train = train;
        this.passenger = passenger;
        this.bookingDate = bookingDate;
        this.travelDate = travelDate;
    }

    // Constructor with ID (e.g., when retrieved from DB)
    public Ticket(int id, Train train, Passenger passenger, Date bookingDate, Date travelDate) {
        this.id = id;
        this.train = train;
        this.passenger = passenger;
        this.bookingDate = bookingDate;
        this.travelDate = travelDate;
    }

    // Getters
    public int getId() {
        return id;
    }

    public Train getTrain() {
        return train;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Date getBookingDate() {
        return bookingDate;
    }

    public Date getTravelDate() {
        return travelDate;
    }

    // Setters (optional)
    public void setId(int id) {
        this.id = id;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    public void setTravelDate(Date travelDate) {
        this.travelDate = travelDate;
    }

    // Utility method to show ticket info
    public String getTicketDetails() {
        if (this.passenger == null) {
            throw new NullPointerException("Passenger information is missing.");
        }
        return "Ticket ID: " + id +
                "\nPassenger: " + passenger.getName() +
                "\nTrain: " + train.getName() +
                "\nFrom: " + train.getSource() +
                "\nTo: " + train.getDestination() +
                "\nBooking Date: " + bookingDate +
                "\nTravel Date: " + travelDate;
    }

    // Equals method (used when canceling tickets by object)
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Ticket)) return false;
        Ticket other = (Ticket) obj;
        return this.id == other.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    // Removed: getPassengerById method from here
}
