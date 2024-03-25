package edu.baylor.GroupFive.controllers;

import edu.baylor.GroupFive.models.Account;
import edu.baylor.GroupFive.models.QualityDescription;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.services.RoomServices;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationController {

    void bookRoom(Account account, Date startDate, Date endDate, Room room){

    }

    static List<Room> getAllRooms(){
        return RoomServices.getRooms();
    }

    static List<Room> findRooms(Date start, Date end, int singles, int doubles, int queens, int kings, List<Room.THEME> themes, List<QualityDescription> qualities){
        List<Room> rooms = RoomServices.getRooms();
        return rooms.stream()
            .filter(room ->{
               boolean isAvailable = room.getBookings().stream().filter(booking -> {
                   return booking.getStartDate().after(start) && booking.getEndDate().before(end);
               }).toList().isEmpty();

               boolean matches = room.getSingleBeds() >= singles
                   && room.getDoubleBeds() >= doubles
                   && room.getQueenBeds() >= queens
                   && room.getKingBeds() >= kings
                   && themes.contains(room.getTheme())
                   && qualities.contains(room.getQuality());
               return isAvailable && matches;
            })
            .toList();
    };
    void createReservation() {
        
    }
}
