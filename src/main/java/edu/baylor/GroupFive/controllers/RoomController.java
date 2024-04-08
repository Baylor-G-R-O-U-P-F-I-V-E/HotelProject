package edu.baylor.GroupFive.controllers;

import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.services.RoomServices;

import java.util.Date;
import java.util.List;

 /**
  * Was supposed to handle room operations
  *
  * @deprecated use {@link #PageButton()} instead.
  * */
@Deprecated
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
