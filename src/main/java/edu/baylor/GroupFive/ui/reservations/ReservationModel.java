package edu.baylor.GroupFive.ui.reservations;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.lang.Deprecated;

import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;

/**
 * Model class for managing reservation data.
 *
 * Extends {@link edu.baylor.GroupFive.ui.utils.table.HotelModel} and 
 * implements {@link edu.baylor.GroupFive.ui.utils.interfaces.DataModel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.table.HotelModel
 * @see edu.baylor.GroupFive.ui.utils.interfaces.DataModel
 * @author Brendon
 */
public class ReservationModel extends HotelModel implements DataModel {

    /**
     * Constructs a ReservationModel with the specified column names and column classes.
     *
     * @param columnNames An array of column names
     * @param columnClass An array of column classes
     */
    public ReservationModel(String[] columnNames, Class<?>[] columnClass) {
        super(columnNames, columnClass);

        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    /**
     * Retrieves data from the database and populates the model.
     *
     * @throws RuntimeException If an error occurs while fetching data from the database.
     */
     public void getData() throws RuntimeException {
       // Fetch room data from the database
        List<Reservation> reservations = ReservationController.getAllActiveReservations();
        
        // Check if data was fetched successfully
        if (reservations == null) {
            throw new RuntimeException("Error fetching data from the database");
        }

        // Add the data to the table
        for (Reservation reservation : reservations) {
            try {
                // Add the row to the table
                addRow(new Object[] {String.valueOf(reservation.getRoomNumber()), formatDate(reservation.getStartDate()), formatDate(reservation.getEndDate()), reservation.getGuestUsername(), String.format("%.2f", reservation.calculatePrice())});
                // Print the row to the console
                System.out.println("Added row to table: " + reservation.getRoomNumber() + ", " + reservation.getStartDate() + ", " + reservation.getEndDate() + ", " + reservation.getGuestUsername() + ", " + reservation.getPrice());
            } catch (Exception e) {
                // Log any errors
                System.out.println("Error adding row to table");
            }
        }
    }

    /**
     * Retrieves the reservation at the specified row.
     *
     * @param row The row index of the reservation to retrieve.
     * @return The reservation at the specified row.
     */
    public Reservation getReservation(int row) {
        return ReservationController.getReservation(Integer.parseInt((String) getValueAt(row, 0)), (Date) getValueAt(row, 1));
    }

    /**
     * Formats the given date object into a string representation.
     *
     * @deprecated Use {@link edu.baylor.GroupFive.util.CoreUtils#formatDate(Date)} instead.
     * @param date The date object to be formatted.
     * @return String representation of {@code date}.
     */
    @Deprecated
    public String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        return formatter.format(date);
    }

}
