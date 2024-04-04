package edu.baylor.GroupFive.controllers;

import edu.baylor.GroupFive.models.Account;
import edu.baylor.GroupFive.models.QualityDescription;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.services.ReservationServices;
import edu.baylor.GroupFive.services.RoomServices;

import java.sql.SQLException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationController {

    public static boolean bookRoom(User account, Date startDate, Date endDate, Room room){
        String guestID = account.getUsername();
        Boolean added = ReservationServices.addReservation(startDate, endDate, String.valueOf(room.getRoomNumber()), guestID);
        if (Boolean.FALSE.equals(added)) {
            return false;
        }
        return true;
    }

    public static Boolean modifyReservation(Reservation newInfo, String originalRoom, Date oldStart){
        return ReservationServices.modifyReservation(newInfo,originalRoom, oldStart);
    }


    public static Boolean cancelReservation(Integer roomNumber, Date startDate){
        return ReservationServices.cancelReservation(roomNumber,startDate);
    }

    public static Reservation getInfo(Integer roomNumber, Date startDate){
        return ReservationServices.getInfo(roomNumber,startDate);
    }

    public static Boolean checkIfAvailable(Integer roomNumber, Date startDate, Date endDate){
        return ReservationServices.checkIfAvailable(roomNumber, startDate, endDate);
    }

    public static List<Room> getAllRooms(){
        return RoomServices.getRooms();
    }
    /*
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


         */
    public static List<Reservation> getAllReservations() {
        return ReservationServices.getReservations();
    }

    private static boolean isOverlap(Date start1, Date end1, Date start2, Date end2) {
        return !start1.after(end2) && !end1.before(start2);
    }
    public static boolean isRoomBookedOn(int roomNumber, Date startDate, Date endDate){
        List<Reservation> reservations = getAllReservations();
        List<Reservation> roomReservations = reservations.stream()
            .filter(rsv -> rsv.getRoomNumber() == roomNumber)
            .toList();
        return roomReservations.stream().anyMatch(rsv ->
            isOverlap(startDate, endDate, rsv.getStartDate(), rsv.getEndDate()));
    }


}
