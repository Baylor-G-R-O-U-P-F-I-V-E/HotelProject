package edu.baylor.GroupFive.controllers;

import edu.baylor.GroupFive.models.Account;
import edu.baylor.GroupFive.models.QualityDescription;
import edu.baylor.GroupFive.models.Room;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationController {
    ReservationController(){
        this.rooms = new ArrayList<>();
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    private List<Room> rooms;

    void bookRoom(Account account, Date startDate, Date endDate, Room room){

    }

    List<Room> findRooms(Date start, Date end, int singles, int doubles, int queens, int kings, List<Room.THEME> themes, List<QualityDescription> qualities){
        return this.rooms.stream()
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
