package edu.baylor.GroupFive.ui.modifyReservation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Date;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.database.controllers.RoomController;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.ui.utils.BadInputDialog;
import edu.baylor.GroupFive.ui.utils.DatePanel;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

/**
 * ActionListener implementation for modifying a reservation.
 *
 * @author Brendon
 */
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
      * Makes the bad man appear.
      *
      * @param msg Message to display
      * */
    private void makeBadInputDialog(String msg) {
        try {
            new BadInputDialog(msg, title);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    /**
     * Constructs a ModifyReservationActionListener with the specified parameters.
     *
     * @param loginPage     The input delegate for handling user interactions.
     * @param originalRoom  The original room number of the reservation.
     * @param originalStart The original start date of the reservation.
     * @param roomField     The text field for entering the room number.
     * @param priceField    The text field for entering the price.
     * @param startDate     The date panel for selecting the start date.
     * @param endDate       The date panel for selecting the end date.
     */
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
      * Error checks values within cells and attempts to change reservation
      *
      * @param e the action event to be processed
      * */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Establish connection to database
        // ReservationDAOImpl reservationDAOImpl = new ReservationDAOImpl(); // FIXME changed

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

        // Check if room exists
        if (RoomController.getRoomInfo(Integer.parseInt(room)) == null) {
            message = """
                        Oopsie! The room does not exist. Please try a different room.
                    """;
            makeBadInputDialog(message);
            return;
        }

        // Get the reservation
        Reservation reservation = ReservationController.getReservation(Integer.parseInt(originalRoom), originalStart);

        // Get original end date
        Date originalEnd = reservation.getEndDate();

        // Update the reservation
        reservation.setRoomID(room);
        reservation.setPrice(Double.parseDouble(price));
        reservation.setStartDate(start);
        reservation.setEndDate(end);

        // Check if the room or dates have changed
        if (!(Integer.parseInt(room) == (Integer.parseInt(originalRoom))) || 
        !(start.after(originalStart) || start.equals(originalStart)) && (end.before(originalEnd) || end.equals(originalEnd))) {
            // Ensure the room is not already booked
            if (ReservationController.isRoomBookedOn(Integer.parseInt(room), start, end)) {
                message = """
                            Oopsie! The room is already booked during that time.
                        """;
                makeBadInputDialog(message);
                return;
            }
        }

        // Update the reservation
        boolean result = ReservationController.modifyReservation(reservation); // changed here

        // If the update failed, show an error message
        if (!result) {
            message = """
                        Oopsie! The reservation could not be updated.
                    """;
            makeBadInputDialog(message);
            return;
        }

        // Show a success message
        JOptionPane.showMessageDialog(null, SUCCESS_MSG);

        page.onPageSwitch("reservations");
        
    }
}
