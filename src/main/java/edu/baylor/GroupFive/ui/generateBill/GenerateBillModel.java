package edu.baylor.GroupFive.ui.generateBill;

import java.util.List;

import edu.baylor.GroupFive.database.controllers.AccountController;
import edu.baylor.GroupFive.database.controllers.BillingController;
import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;

import edu.baylor.GroupFive.ui.utils.table.HotelModel;
import edu.baylor.GroupFive.util.CoreUtils;
import edu.baylor.GroupFive.util.logging.G5Logger;

/**
 *
 */
public class GenerateBillModel extends HotelModel implements DataModel {

    /**
     *
     * @param columnNames
     * @param columnClass
     */
    public GenerateBillModel(String[] columnNames, Class<?>[] columnClass) {
        super(columnNames, columnClass);

        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    /**
     *
     * @throws RuntimeException
     */
    public void getData() throws RuntimeException {
        // Fetch user data from the database
        List<Reservation> reservations = BillingController.getCurrentGuestTransactions();
        
        // Check if data was fetched successfully
        if (reservations == null) {
             throw new RuntimeException("Error fetching data from the database");
        }

        // Add the data to the table
        for (Reservation reservation : reservations) {
             try {
                User user = AccountController.getUser(reservation.getGuestUsername());

                if (user == null) {
                    G5Logger.logger.error("Error fetching user data from the database");
                } else {
                    // Add the row to the table
                    addRow(new Object[] {user.getFirstName(), user.getLastName(), CoreUtils.formatDate(reservation.getStartDate()), CoreUtils.formatDate(reservation.getEndDate()), user.getUsername(), String.valueOf(reservation.getRoomNumber())});
                    // Print the row to the console
                    System.out.println("Added row to table: " + user.getFirstName() + ", " + user.getLastName() + ", " + reservation.getStartDate() + ", " + reservation.getEndDate() + ", " + user.getUsername() + ", " + reservation.getRoomNumber());
                }

             } catch (Exception e) {
                 // Log any errors
                 System.out.println("Error adding row to table");
             }
        }
    }

}
