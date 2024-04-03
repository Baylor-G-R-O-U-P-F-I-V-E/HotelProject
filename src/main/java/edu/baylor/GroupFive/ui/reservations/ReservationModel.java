package edu.baylor.GroupFive.ui.reservations;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import edu.baylor.GroupFive.controllers.ReservationController;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;

public class ReservationModel extends HotelModel implements DataModel {

    public ReservationModel(String[] columnNames, Class<?>[] columnClass) {
        super(columnNames, columnClass);

        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

     public void getData() throws RuntimeException {
       // Fetch room data from the database
        List<Reservation> reservations = ReservationController.getAllReservations();
        
        // Check if data was fetched successfully
        if (reservations == null) {
            throw new RuntimeException("Error fetching data from the database");
        }

        // Add the data to the table
        for (Reservation reservation : reservations) {
            try {
                // Add the row to the table
                addRow(new Object[] {String.valueOf(reservation.getRoomID()), formatDate(reservation.getStartDate()), formatDate(reservation.getEndDate()), String.valueOf(reservation.getGuestID()), String.valueOf(reservation.getPrice())});
                // Print the row to the console
                System.out.println("Added row to table: " + reservation.getRoomID() + ", " + reservation.getStartDate() + ", " + reservation.getEndDate() + ", " + reservation.getGuestID() + ", " + reservation.getPrice());
                System.out.println("Added row to table: " + reservation.getRoomID() + ", " + reservation.getStartDate() + ", " + reservation.getEndDate() + ", " + reservation.getGuestID() + ", " + reservation.getPrice());
            } catch (Exception e) {
                // Log any errors
                System.out.println("Error adding row to table");
            }
        }
    }

    public String formatDate(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        return formatter.format(date);
    }

}
