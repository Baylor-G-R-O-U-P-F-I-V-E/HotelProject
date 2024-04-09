package edu.baylor.GroupFive.ui.modifyReservation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.swing.JTextField;

import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.database.daos.ReservationDAO;
import edu.baylor.GroupFive.ui.utils.BadInputDialog;
import edu.baylor.GroupFive.ui.utils.DatePanel;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;

public class ModifyReservationActionListener implements ActionListener {
    private InputDelegate page;
    private JTextField roomField, priceField;
    private DatePanel startDate, endDate;
    private String originalRoom;
    private Date originalStart;
    private static String title = "Login Error";

    private static final String SUCCESS_MSG = """
        Changes made!
        """;

    private static final String BAD_CONNECTION_MSG = """
        Oopsie! We could not establish a connection to the database!
        """;

     /**
      * makeBadInputDialog
      *
      * Attempts to create a JDialog panel containing our error message
      * */
    private void makeBadInputDialog(String msg) {
        try {
            new BadInputDialog(msg, title);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public ModifyReservationActionListener(Page loginPage, String originalRoom, Date originalStart, JTextField roomField, JTextField priceField, DatePanel startDate, DatePanel endDate) {
        this.page = loginPage;
        this.roomField = roomField;
        this.priceField = priceField;
        this.startDate = startDate;
        this.endDate = endDate;
        this.originalRoom = originalRoom;
        this.originalStart = originalStart;
    }

     /**
      * ModifyReservationActionListener::actionPerformed
      *
      * Error checks values within cells and attempts to change reservation
      * */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Establish connection to database
        ReservationDAO reservationDAO = new ReservationDAO();

        // Create a default message
        String message = "Changes successful!";

        // Get the room
        String room = roomField.getText();

        // If the room is empty, show an error message
        if (room.isEmpty()) {
            message = """
                        Oopsie! You forgot to enter the room number.
                    """;
            makeBadInputDialog(message);
            return;
        }

        // Get the price
        String price = priceField.getText();

        // If the price is empty, show an error message
        if (price.isEmpty()) {
            message = """
                        Oopsie! You forgot to enter the price.
                    """;
            makeBadInputDialog(message);
            return;
        }

        // Get the dates
        Date start = startDate.getDate();
        Date end = endDate.getDate();
        Date today = Date.from(ZonedDateTime.now(ZoneId.of("America/Chicago")).toInstant());
        System.out.println(today.toString());

        // Check if start date is before today
        if (start.before(today)) {
            message = """
                        Oopsie! The start date must be after today!
                    """;
            makeBadInputDialog(message);
            return;
        }

        // Check if start is after end
        if (start.after(end)) {
            message = """
                        Oopsie! The end date must be after the start date.
                    """;
            makeBadInputDialog(message);
            return;
        }

        // Get the reservation
        Reservation reservation = ReservationController.getInfo(Integer.parseInt(originalRoom), originalStart);

        // Update the reservation
        reservation.setRoomID(room);
        reservation.setPrice(Double.parseDouble(price));
        reservation.setStartDate(start);
        reservation.setEndDate(end);

        // Update the reservation in the database
        boolean result = ReservationController.modifyReservation(reservation, originalRoom, originalStart);

        // If the update failed, show an error message
        if (!result) {
            message = """
                        Oopsie! The reservation could not be updated.
                    """;
            makeBadInputDialog(message);
            return;
        }

        // Check if reservation is available
        // TODO check implementation
        // if (!reservationDAO.checkIfAvailable(room, start, end)) {
        //     message = """
        //         Oopsie! The reservation is unavailable
        //         """;
        //     makeBadInputDialog(message);
        //     return;
        // }

        page.onPageSwitch("home");
        
    }
}
