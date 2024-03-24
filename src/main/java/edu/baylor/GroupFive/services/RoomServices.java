package edu.baylor.GroupFive.services;

import edu.baylor.GroupFive.database.reservationDAO.RoomDatabaseConnection;
import edu.baylor.GroupFive.models.Room;

import java.util.List;

public class RoomServices {
    public static List<Room> getRooms(){
        RoomDatabaseConnection roomConn = new RoomDatabaseConnection();
        return roomConn.getData();
    };
}
