package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.daos.RoomDAO;
import edu.baylor.GroupFive.database.services.ReservationServices;
import edu.baylor.GroupFive.models.Room;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RoomServices {

    public static List<Room> getRooms(){
        RoomDAO roomConn = new RoomDAO();
        return roomConn.getRooms();
    };

    public static Room getRoom(Integer roomNum){
        RoomDAO conn = new RoomDAO();
        return conn.getRoom(roomNum);
    }

    public static Boolean modifyRoom(Room updatedInfo){
        RoomDAO conn = new RoomDAO();
        return conn.modifyRoom(updatedInfo);
    }

    public static Boolean addRoom(Room newRoom){
        RoomDAO conn = new RoomDAO();
        return conn.addRoom(newRoom);
    }


    public static List<Room> getAvailableRooms(Date startDate, Date endDate){
        List<Room> allRooms = getRooms();
        ReservationServices conn = new ReservationServices();
        List<Room> availableRooms = new ArrayList<>();

        for(Room r : allRooms){
            try {
                if(conn.checkIfAvailable(r.getRoomNumber().toString(),startDate,endDate)){
                    availableRooms.add(r);
                }
            } catch (SQLException ex) {
                System.err.println("ADD LOGGER IMPLEMENTATION IN database.services.RoomServices");
                ex.printStackTrace();
            }
        }

        return availableRooms;
    }

}
