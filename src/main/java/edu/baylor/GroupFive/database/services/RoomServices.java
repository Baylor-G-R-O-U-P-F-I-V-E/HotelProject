package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.daos.RoomDAO;
import edu.baylor.GroupFive.database.services.ReservationServices;
import edu.baylor.GroupFive.models.Room;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The RoomServices class contains static methods related to managing rooms
 * in our database.
 */
public class RoomServices {

    private RoomServices() {};

    /**
     * Returns all rooms in our database.
     *
     * @return A List of every room
     */
    public static List<Room> getRooms(){
        RoomDAO roomConn = new RoomDAO();
        return roomConn.getAll();
    };

    /**
     * Searches for a room in our database given a room number. If it exists,
     * the Room is returned. Otherwise {@code null} is returned.
     *
     * @param roomNum Room number.
     * @return {@code Room} object if it exists. {@code null} otherwise.
     */
    public static Room getRoom(Integer roomNum){
        RoomDAO conn = new RoomDAO();
        return conn.get(roomNum);
    }

    /**
     * Modifies a room in our database.
     *
     * @param updatedInfo Room with modified information
     * @return {@code true} if successful modification. {@code false} otherwise.
     */
    public static Boolean modifyRoom(Room updatedInfo){
        RoomDAO conn = new RoomDAO();
        return conn.update(updatedInfo) == 1 ? true : false;
    }

    /**
     * Adds a room to our database.
     *
     * @param newRoom Room to add.
     * @return {@code true} if successful insertion. {@code false} otherwise.
     */
    public static Boolean addRoom(Room newRoom){
        RoomDAO conn = new RoomDAO();
        return conn.insert(newRoom) == 1 ? true : false;
    }

    /**
     * Finds all available rooms within a certain time interval.
     *
     * @param startDate Start date of time interval.
     * @param endDate End date of time interval.
     * @return A List of available rooms.
     */
    public static List<Room> getAvailableRooms(Date startDate, Date endDate){
        List<Room> allRooms = getRooms();
        ReservationServices conn = new ReservationServices();
        List<Room> availableRooms = new ArrayList<>();

        for(Room r : allRooms){
            try {
                if(conn.checkIfAvailable(r.getRoomNumber(),startDate,endDate)){
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
