package edu.baylor.GroupFive.controllers;

import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.services.RoomServices;

public class RoomController {

    public static Room getRoom(String roomNumber) {
        return RoomServices.getRoom(roomNumber);
    }
}
