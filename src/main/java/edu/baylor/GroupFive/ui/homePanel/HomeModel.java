package edu.baylor.GroupFive.ui.homePanel;

import java.text.ParseException;
import java.util.List;

import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;
import edu.baylor.GroupFive.util.CoreUtils;

/**
 * Model for managing home-related data.
 * This model extends {@link edu.baylor.GroupFive.ui.utils.table.HotelModel}
 * and implements {@link edu.baylor.GroupFive.ui.utils.interfaces.DataModel},
 * providing functionality to retrieve and display home-related data for a user.
 *
 * @see edu.baylor.GroupFive.ui.utils.table.HotelModel
 * @see edu.baylor.GroupFive.ui.utils.interfaces.DataModel
 * @author Brendon
 */
public class HomeModel extends HotelModel implements DataModel {

    private User user;

    /**
     * Constructs a HomeModel with the specified column names, column classes, and user.
     *
     * @param columnNames An array of column names.
     * @param columnClass An array of column classes.
     * @param user The user for whom the data is being retrieved.
     */
    public HomeModel(String[] columnNames, Class<?>[] columnClass, User user) {
        super(columnNames, columnClass);
        this.user = user;

        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    /**
     * Retrieves home-related data for the user from the database.
     *
     * @throws RuntimeException If an error occurs while fetching data from the database.
     */
    public void getData() throws RuntimeException {
        // Fetch user data from the database
        List<Reservation> reservations = ReservationController.getReservationsForUser(user.getUsername());

        // Check if data was fetched successfully
        if (reservations == null) {
            throw new RuntimeException("Error fetching data from the database");
        }

        // Filter out reservations that are not active
        reservations.removeIf(reservation -> !reservation.getActiveStatus());

        // Add the data to the table
        for (Reservation reservation : reservations) {
            try {

                // Add the row to the table
                addRow(new Object[] {String.valueOf(reservation.getRoomNumber()), CoreUtils.formatDate(reservation.getStartDate()), CoreUtils.formatDate(reservation.getEndDate()), String.format("%.2f", reservation.calculatePrice())});
                // Print the row to the console
                System.out.println("Added row to table: " + user.getFirstName() + ", " + user.getLastName() + ", " + reservation.getStartDate() + ", " + reservation.getEndDate() + ", " + user.getUsername() + ", " + reservation.getRoomNumber());

            } catch (Exception e) {
                // Log any errors
                System.out.println("Error adding row to table");
            }
        }
    }

    public void refreshData() {
        //Clear the table
        for (int i = getRowCount() - 1; i >= 0; i--) {
            removeRow(i);
        }
        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    /**
     * Retrieves the reservation at the specified row.
     *
     * @param row The row index of the reservation to retrieve.
     * @return The reservation at the specified row.
     * @throws ParseException 
     * @throws NumberFormatException 
     */
    public Reservation getReservation(int row) throws NumberFormatException, ParseException {
        return ReservationController.getReservation(Integer.parseInt((String) getValueAt(row, 0)), CoreUtils.getUtilDate(CoreUtils.getSqlDate((String) getValueAt(row, 1))));
    }

}
