// model/BookTicketCommand.java
package model;

import java.util.Date;
import controller.BookingController;

public class BookTicketCommand implements TicketCommand {

    private BookingController controller;
    private String name;
    private int age;
    private String gender;
    private String berthPreference;
    private String govtIdType;
    private String govtIdNumber;
    private String email;
    private int trainId;
    private Date travelDate;
    private String paymentMode;

    public BookTicketCommand(
            BookingController controller,
            String name, int age, String gender, String berthPreference,
            String govtIdType, String govtIdNumber, String email,
            int trainId, Date travelDate, String paymentMode) {

        this.controller = controller;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.berthPreference = berthPreference;
        this.govtIdType = govtIdType;
        this.govtIdNumber = govtIdNumber;
        this.email = email;
        this.trainId = trainId;
        this.travelDate = travelDate;
        this.paymentMode = paymentMode;
    }

    @Override
    public void execute() {
        controller.bookTicket(name, age, gender, berthPreference,
                govtIdType, govtIdNumber, email,
                trainId, travelDate, paymentMode);
    }
}
