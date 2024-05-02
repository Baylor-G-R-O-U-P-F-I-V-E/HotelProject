package edu.baylor.GroupFive.ui.homePanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.database.controllers.RoomController;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.reservations.ReservationModel;
import edu.baylor.GroupFive.ui.utils.DatePanel;
import edu.baylor.GroupFive.ui.utils.Page;

public class ModifyReservationListener {

    private Page page;
    private User user;
    private Reservation reservation;

    public ModifyReservationListener(Page page, User user, Reservation reservation) {
        this.page = page;
        this.user = user;
        this.reservation = reservation;

        // Check if the reservation is null
        if (reservation == null) {
            JOptionPane.showMessageDialog(page, "Error fetching reservation data.");
            return;
        }

        // Check if the user is the guest of the reservation
        if (!reservation.getGuestUsername().equals(user.getUsername())) {
            JOptionPane.showMessageDialog(page, "You do not have permission to modify this reservation.");
            return;
        }

        // Give the user a choice, modify or cancel
        int choice = JOptionPane.showOptionDialog(page, "How would you like to modify this reservation?", "Modify Reservation", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, new Object[]{"Close", "Modify", "Cancel Reservation"}, "Close");

        if (choice == 2) {
            // Cancel the reservation
            Boolean result = ReservationController.cancelReservation(reservation);
            if (result) {
                JOptionPane.showMessageDialog(page, "Reservation cancelled successfully.");
            } else {
                JOptionPane.showMessageDialog(page, "Error cancelling reservation.");
            }
        } else if (choice == 1) {
            // Prompt the user for two new dates
            DatePanel startDatePanel = new DatePanel("");
            int result = JOptionPane.showConfirmDialog(null, startDatePanel, "Select a start date",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                Date startDate = startDatePanel.getDate();

                DatePanel endDatePanel = new DatePanel("", 1);

                result = JOptionPane.showConfirmDialog(null, endDatePanel, "Select an end date",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    Date endDate = endDatePanel.getDate();
                    System.out.println(startDate);
                    System.out.println(endDate);
                    if (startDate.after(endDate)) {
                        JOptionPane.showMessageDialog(null, "End date must be after start date.");
                        return;
                    }
                    List<Date> dates = new ArrayList<>();
                    dates.add(startDate);
                    dates.add(endDate);
                    if (validateDates(startDate, endDate)) {

                        reservation.setStartDate(startDate);
                        reservation.setEndDate(endDate);
                        // Modify the reservation
                        Boolean success = ReservationController.modifyReservation(reservation);
                        if (success) {
                            JOptionPane.showMessageDialog(page, "Reservation modified successfully.");
                        } else {
                            JOptionPane.showMessageDialog(page, "Error modifying reservation.");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter both start and end dates.");
                }
        }
            

        } else {
            // Close the dialog
        }

    }

    public Boolean validateDates(Date startDate, Date endDate) {
        // Check if the dates are valid
        if (startDate == null || endDate == null) {
            JOptionPane.showMessageDialog(page, "Please select valid dates.");
            return false;
        }

        // Check if the new start date is before the new end date
        if (startDate.after(endDate)) {
            JOptionPane.showMessageDialog(page, "Start date must be before end date.");
            return false;
        }

        // Check if the new dates are the same as the old dates
        if (startDate.equals(reservation.getStartDate()) && endDate.equals(reservation.getEndDate())) {
            JOptionPane.showMessageDialog(page, "New dates are the same as the old dates.");
            return false;
        }

        // Check if start is before current day
        if (startDate.before(new Date())) {
            JOptionPane.showMessageDialog(page, "Start date must be after today.");
            return false;
        }

        // Check if the room is available for the new dates
        if (!RoomController.isRoomAvailable(reservation.getRoomNumber(), startDate, endDate)) {
            JOptionPane.showMessageDialog(page, "Room is not available for the selected dates.");
            return false;
        }

        return true;
    }

}
