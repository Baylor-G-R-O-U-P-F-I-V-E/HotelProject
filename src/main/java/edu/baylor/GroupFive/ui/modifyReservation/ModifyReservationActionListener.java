package edu.baylor.GroupFive.ui.modifyReservation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;

import javax.swing.JTextField;

import edu.baylor.GroupFive.controllers.ReservationController;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.ui.utils.BadInputDialog;
import edu.baylor.GroupFive.ui.utils.DatePanel;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

public class ModifyReservationActionListener implements ActionListener {
    private InputDelegate page;
    private JTextField roomField, priceField;
    private DatePanel startDate, endDate;
    private String originalRoom;
    private Date originalStart;
    private static String title = "Login Error";

    public ModifyReservationActionListener(Page loginPage, String originalRoom, Date originalStart, JTextField roomField, JTextField priceField, DatePanel startDate, DatePanel endDate) {
        this.page = loginPage;
        this.roomField = roomField;
        this.priceField = priceField;
        this.startDate = startDate;
        this.endDate = endDate;
        this.originalRoom = originalRoom;
        this.originalStart = originalStart;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a default message
        String message = "Changes successful!";

        // Get the room

        String room = roomField.getText();

        // If the room is empty, show an error message

        if (room.isEmpty()) {
            message = """
                        Oopsie! You forgot to enter the room number.
                    """;
            try {
                new BadInputDialog(message, title);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        // Get the price

        String price = priceField.getText();

        // If the price is empty, show an error message

        if (price.isEmpty()) {
            message = """
                        Oopsie! You forgot to enter the price.
                    """;
            try {
                new BadInputDialog(message, title);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        // Get the dates

        Date start = startDate.getDate();
        Date end = endDate.getDate();

        // Check if start is after end

        if (start.after(end)) {
            message = """
                        Oopsie! The end date must be after the start date.
                    """;
            try {
                new BadInputDialog(message, title);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
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
            try {
                new BadInputDialog(message, title);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        page.onPageSwitch("home");
        
    }
}
