package edu.baylor.GroupFive.controllers;

import edu.baylor.GroupFive.models.Account;
import edu.baylor.GroupFive.models.QualityDescription;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.services.ReservationServices;
import edu.baylor.GroupFive.services.RoomServices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationController {

    void bookRoom(Account account, Date startDate, Date endDate, Room room){

    }

    public static List<Room> getAllRooms(){
        return RoomServices.getRooms();
    }

    static List<Room> findRooms(Date start, Date end, int numBeds, Room.BED_TYPE bedType, List<Room.THEME> themes, List<QualityDescription> qualities){
        List<Room> rooms = RoomServices.getRooms();
        return rooms.stream()
            .filter(room ->{
               boolean isAvailable = room.getBookings().stream().filter(booking -> {
                   return booking.getStartDate().after(start) && booking.getEndDate().before(end);
               }).toList().isEmpty();

               boolean matches = room.getNumBeds() >= numBeds
                   && room.getBedType() == bedType
                   && themes.contains(room.getTheme())
                   && qualities.contains(room.getQuality());
               return isAvailable && matches;
            })
            .toList();
    };
    void createReservation() {
        
    }

    public static List<Reservation> getAllReservations() {
        try {
            return ReservationServices.getReservations();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
}
