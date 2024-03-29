package edu.baylor.GroupFive.ui.reserveRoom;

import java.util.List;

import edu.baylor.GroupFive.controllers.ReservationController;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.Room.BED_TYPE;
import edu.baylor.GroupFive.models.Room.THEME;
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
                addRow(new Object[] {room.getRoomNumber(), room.getQuality(), formatTheme(room.getTheme()), String.valueOf(room.isSmoking()), room.getNumBeds(), formatBedSize(room.getBedType()), String.valueOf(room.getDailyPrice())});
                // Print the row to the console
                System.out.println("Added row to table: " + room.getRoomNumber() + ", " + room.getQuality() + ", " + room.getTheme() + ", " + room.isSmoking() + ", " + room.getNumBeds() + ", " + formatBedSize(room.getBedType()));
            } catch (Exception e) {
                // Log any errors
                System.out.println("Error adding row to table");
            }
        }
    }

    public String formatTheme(THEME theme) {
        switch (theme) {
            case ThemeA:
                return "Theme A";
            case ThemeB:
                return "Theme B";
            case ThemeC:
                return "Theme C";
            default:
                return "Unknown";
        }
    }

    public String formatBedSize(BED_TYPE bedtype) {
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
