package edu.baylor.GroupFive.ui.reserveRoom;

import java.util.Date;
import java.util.List;

import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.database.controllers.RoomController;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.enums.BedType;
//import edu.baylor.GroupFive.models.enums.Quality;
import edu.baylor.GroupFive.models.enums.Theme;
import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;

/**
 * Model for adding reservations.
 *
 * Extends {@link edu.baylor.GroupFive.ui.utils.table.HotelModel} and
 * implements {@link edu.baylor.GroupFive.ui.utils.interfaces.DataModel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.table.HotelModel
 * @see edu.baylor.GroupFive.ui.utils.interfaces.DataModel
 * @author Brendon
 */
public class AddReservationModel extends HotelModel implements DataModel {

    /**
     * Constructs an AddReservationModel with the specified column names and classes.
     *
     * @param columnNames An array of column names.
     * @param columnClasses An array of column classes.
     */
    public AddReservationModel(String[] columnNames, Class<?>[] columnClasses) {
        super(columnNames, columnClasses);

        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    /**
     * Retrieves room data from the database and populates the table with it.
     *
     * @throws RuntimeException If there is an error fetching data from the database.
     */
     public void getData() throws RuntimeException {
        // Fetch room data from the database
        List<Room> rooms = RoomController.getAllRooms();
        
        // Check if data was fetched successfully
        if (rooms == null) {
            throw new RuntimeException("Error fetching data from the database");
        }

        // Add the data to the table
        for (Room room : rooms) {
            try {
                // Add the row to the table
                addRow(new Object[] {String.valueOf(room.getRoomNumber()), String.valueOf(room.getQuality()), formatTheme(room.getTheme()), String.valueOf(room.isSmoking()), String.valueOf(room.getNumBeds()), formatBedSize(room.getBedType()), String.valueOf(room.getDailyPrice())});
                // Print the row to the console
                System.out.println("Added row to table: " + room.getRoomNumber() + ", " + room.getQuality() + ", " + room.getTheme() + ", " + room.isSmoking() + ", " + room.getNumBeds() + ", " + formatBedSize(room.getBedType()));
            } catch (Exception e) {
                // Log any errors
                System.out.println("Error adding row to table");
            }
        }
    }

    /**
     * Filter rooms based on the specified start and end dates.
     *
     * @param startDate The start date.
     * @param endDate The end date.
     */
    public void filterRoomsByDate(Date startDate, Date endDate) {
        // Fetch room data from the database
        List<Room> rooms = RoomController.getAvailableRooms(startDate, endDate);
        
        // Check if data was fetched successfully
        if (rooms == null) {
            throw new RuntimeException("Error fetching data from the database");
        }

        // Clear the table
        clearTable();

        // Add the data to the table
        for (Room room : rooms) {
            try {
                // Add the row to the table
                addRow(new Object[] {String.valueOf(room.getRoomNumber()), String.valueOf(room.getQuality()), formatTheme(room.getTheme()), String.valueOf(room.isSmoking()), String.valueOf(room.getNumBeds()), formatBedSize(room.getBedType()), String.valueOf(room.getDailyPrice())});
                // Print the row to the console
                System.out.println("Added row to table: " + room.getRoomNumber() + ", " + room.getQuality() + ", " + room.getTheme() + ", " + room.isSmoking() + ", " + room.getNumBeds() + ", " + formatBedSize(room.getBedType()));
            } catch (Exception e) {
                // Log any errors
                System.out.println("Error adding row to table");
            }
        }
    }

    /**
     * Clears the reservation table.
     */
    private void clearTable() {
        // Clear the table
        for (int i = getRowCount() - 1; i >= 0; i--) {
            removeRow(i);
        }
    }

    /**
     * Formats the theme enum into a human-readable string representation.
     * 
     * @param theme The theme enum to be formatted.
     * @return A string representing the formatted theme.
     */
    // @Deprecated
    public String formatTheme(Theme theme) {
        // Cant we just use the enum.toString function? -Icko
        switch (theme) {
            case VintageCharm:
                return "Vintage Charm";
            case UrbanElegance:
                return "Urban Elegance";
            case NatureRetreat:
                return "Nature Retreat";
            default:
                return "Unknown";
        }
    }

    /**
     * Formates the bedtype enum into a human-readable string representation.
     *
     * @param bedtype The bedtype enum to be formatted.
     * @return A string represenging the formatted bedtype.
     */
    // @Deprecated
    public String formatBedSize(BedType bedtype) {
        // Cant we just use the enum.toString function? -Icko
        switch (bedtype) {
            case KING:
                return "King";
            case QUEEN:
                return "Queen";
            case DOUBLE:
                return "Double";
            case SINGLE:
                return "Single";
            default:
                return "Unknown";
        }
    }

}
