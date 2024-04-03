package edu.baylor.GroupFive.services;

import edu.baylor.GroupFive.database.roomDAO.RoomDatabaseConnection;
import edu.baylor.GroupFive.models.Room;

import java.util.List;

public class RoomServices {
    public static List<Room> getRooms(){
        RoomDatabaseConnection roomConn = new RoomDatabaseConnection();
        return roomConn.getRooms();
    };

    public static Room getRoom(Integer roomNum){
        RoomDatabaseConnection conn = new RoomDatabaseConnection();
        return conn.getRoom(roomNum);
    }

    public static Boolean modifyRoom(Room updatedInfo){
        RoomDatabaseConnection conn = new RoomDatabaseConnection();
        return conn.modifyRoom(updatedInfo);
    }

    public static Boolean addRoom(Room newRoom){
        RoomDatabaseConnection conn = new RoomDatabaseConnection();
        return conn.addRoom(newRoom);
    }


}
