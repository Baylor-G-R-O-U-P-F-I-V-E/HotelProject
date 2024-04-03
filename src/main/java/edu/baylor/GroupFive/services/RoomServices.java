package edu.baylor.GroupFive.services;

import edu.baylor.GroupFive.database.roomDAO.RoomDatabaseConnection;
import edu.baylor.GroupFive.models.Room;

import java.util.List;

public class RoomServices {

    public static List<Room> getRooms(){
        RoomDatabaseConnection roomConn = new RoomDatabaseConnection();
        return roomConn.getRooms();
    };

    public static Room getRoom(String roomNumber) {
        RoomDatabaseConnection roomConn = new RoomDatabaseConnection();
        return roomConn.getRoom(Integer.parseInt(roomNumber));
    }

}
