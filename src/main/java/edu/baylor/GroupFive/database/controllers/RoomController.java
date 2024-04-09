package edu.baylor.GroupFive.database.controllers;

import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.database.services.RoomServices;

import java.util.Date;
import java.util.List;

public class RoomController {
    public static Room getRoomInfo(Integer roomNumber){
        //WILL RETURN NULL IF NO ROOM EXISTS (I think) -Cole
        return RoomServices.getRoom(roomNumber);
    }

    public Boolean modifyRoom(Room updatedInfo){
        //true only if room already exists and modification is successful
        return RoomServices.modifyRoom(updatedInfo);
    }

    public Boolean addRoom(Room newRoom){
        //true if room is added
        return RoomServices.addRoom(newRoom);
    }


    public static List<Room> getAvailableRooms(Date startDate, Date endDate){
        return RoomServices.getAvailableRooms(startDate,endDate);
    }

}