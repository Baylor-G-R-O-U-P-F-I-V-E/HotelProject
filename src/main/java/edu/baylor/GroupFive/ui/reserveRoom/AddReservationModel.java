package edu.baylor.GroupFive.ui.reserveRoom;

import java.util.Date;
import java.util.List;

import edu.baylor.GroupFive.controllers.ReservationController;
import edu.baylor.GroupFive.controllers.RoomController;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.enums.BedType;
//import edu.baylor.GroupFive.models.enums.Quality;
import edu.baylor.GroupFive.models.enums.Theme;
import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;

public class AddReservationModel extends HotelModel implements DataModel {

    public AddReservationModel(String[] columnNames, Class<?>[] columnClasses) {
        super(columnNames, columnClasses);

        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

     public void getData() throws RuntimeException {
        // Fetch room data from the database
        List<Room> rooms = ReservationController.getAllRooms();
        
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

    private void clearTable() {
        // Clear the table
        for (int i = getRowCount() - 1; i >= 0; i--) {
            removeRow(i);
        }
    }

    public String formatTheme(Theme theme) {
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

    public String formatBedSize(BedType bedtype) {
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

    /*
    public String formatQuality(Quality quality) {

    }
    */

}
