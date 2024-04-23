package edu.baylor.GroupFive.ui.modifyRoom;

import java.util.List;

import edu.baylor.GroupFive.database.controllers.RoomController;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.enums.BedType;
import edu.baylor.GroupFive.models.enums.Theme;
import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;

public class RoomsModel extends HotelModel implements DataModel {

    public RoomsModel(String[] columnNames, Class<?>[] columnClass) {
        super(columnNames, columnClass);

        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }

    public void getData() {

        List<Room> rooms = RoomController.getAllRooms();

        for (Room room : rooms) {
            Object[] data = {
                    room.getRoomNumber(),
                    Theme.formatTheme(room.getTheme()),
                    room.getQuality(),
                    BedType.formatBedType(room.getBedType()),
                    room.getNumBeds(),
                    room.isSmoking(),
                    room.getDailyPrice()
            };
            addRow(data);
        }

    }

}
