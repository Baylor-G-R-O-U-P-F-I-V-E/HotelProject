package edu.baylor.GroupFive.ui.homePanel;

import java.util.List;

import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;
import edu.baylor.GroupFive.util.CoreUtils;
import edu.baylor.GroupFive.util.logging.G5Logger;

/**
 * Model for managing home-related data.
 * This model extends {@link edu.baylor.GroupFive.ui.utils.table.HotelModel}
 * and implements {@link edu.baylor.GroupFive.ui.utils.interfaces.DataModel},
 * providing functionality to retrieve and display home-related data for a user.
 *
 * @param columnNames An array of column names for the table.
 * @param columnClass An array of column classes for the table.
 * @param user The user for whom the data is being retrieved
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
     * @author Brendon
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
     * @author Brendon
     */
    public void getData() throws RuntimeException {
        // Fetch user data from the database
        List<Reservation> reservations = ReservationController.getReservationsForUser(user.getUsername());
        
        // Check if data was fetched successfully
        if (reservations == null) {
              throw new RuntimeException("Error fetching data from the database");
        }

        // Add the data to the table
        for (Reservation reservation : reservations) {
            try {

                // Add the row to the table
                addRow(new Object[] {String.valueOf(reservation.getRoomNumber()), CoreUtils.formatDate(reservation.getStartDate()), CoreUtils.formatDate(reservation.getEndDate()), String.valueOf(reservation.getPrice())});
                // Print the row to the console
                System.out.println("Added row to table: " + user.getFirstName() + ", " + user.getLastName() + ", " + reservation.getStartDate() + ", " + reservation.getEndDate() + ", " + user.getUsername() + ", " + reservation.getRoomNumber());

            } catch (Exception e) {
                // Log any errors
                System.out.println("Error adding row to table");
            }
        }
    }

}
