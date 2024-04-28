package edu.baylor.GroupFive.database.controllers;

import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.database.services.RoomServices;

import java.util.Date;
import java.util.List;

/**
 * This class represents a handler between our UI layer and our application
 * layer. Methods from this class are called from our UI layer. {@code RoomController}
 * then determines which Service object in our application layer to continue 
 * operations with. The status of each operation is then returned to the UI layer.
 *
 * @author Cole
 * @author Brendon
 * @author Icko
 * */
public class RoomController {
    /**
     * This function returns all rooms in our database.
     *
     * @return List of all rooms in our database
     * @see RoomServices#getRooms()
     * */
    public static List<Room> getAllRooms() {
        return RoomServices.getRooms();
    }

    /**
     * This function searches for a room in our database matching a given room
     * number. If it exists, the room is returned. Otherwise {@code null} is
     * returned.
     *
     * @param roomNumber Room number of Room.
     * @return Room if exists in database. {@code null} otherwise
     * @see RoomServices#getRoom(Integer)
     * */
    public static Room getRoomInfo(Integer roomNumber){
        //WILL RETURN NULL IF NO ROOM EXISTS (I think) -Cole
        return RoomServices.getRoom(roomNumber);
    }

    /**
     * This function takes in a room object and updates our database with
     * the new information.
     *
     * @param updatedInfo Room with updated information.
     * @return {@code true} if room was modified successfully. {@code false} otherwise
     * @see RoomServices#modifyRoom(Room)
     * */
    public static Boolean modifyRoom(Room updatedInfo){
        //true only if room already exists and modification is successful
        return RoomServices.modifyRoom(updatedInfo);
    }

    /**
     * This function takes in a room object and adds it to our database.
     *
     * @param newRoom Room to add.
     * @return {@code true} if room was added successfully. {@code false} otherwise
     * @see RoomServices#addRoom(Room)
     * */
    public static Boolean addRoom(Room newRoom){
        //true if room is added
        return RoomServices.addRoom(newRoom);
    }

    /**
     * This function takes in a start and end date and returns a list of rooms
     * that are available during that time interval.
     *
     * @param startDate Starting date of interval.
     * @param endDate End date of interval.
     * @return List of available rooms between {@code startDate} and {@code endDate}
     * @see RoomServices#getAvailableRooms(Date, Date)
     * */
    public static List<Room> getAvailableRooms(Date startDate, Date endDate){
        return RoomServices.getAvailableRooms(startDate,endDate);
    }

    public static Boolean deleteRoom(int roomNum){
        return RoomServices.deleteRoom(roomNum);
    }
}
