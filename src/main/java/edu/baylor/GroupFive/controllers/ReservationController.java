package edu.baylor.GroupFive.controllers;

import edu.baylor.GroupFive.models.Account;
import edu.baylor.GroupFive.models.QualityDescription;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.services.ReservationServices;
import edu.baylor.GroupFive.services.RoomServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservationController {
    private static final Logger logger = LogManager.getLogger(ReservationController.class.getName());
    private static final Marker RESERVATIONS = MarkerManager.getMarker("RESERVATIONS");
    public static boolean bookRoom(User account, Date startDate, Date endDate, Room room){
        logger.info(RESERVATIONS, "Attempting to book room #"+room.getRoomNumber()+" for user "+account.getUsername()+"...");
        String guestID = account.getUsername();
        Boolean added = ReservationServices.addReservation(startDate, endDate, String.valueOf(room.getRoomNumber()), guestID);
        if (Boolean.FALSE.equals(added)) {
            logger.info(RESERVATIONS, "Failed to book "+account.getUsername()+"'s reservation for room #"+room.getRoomNumber());
            return false;
        }
        logger.info(RESERVATIONS, "Successfully booked "+account.getUsername()+"'s reservation for room #"+room.getRoomNumber());
        return true;
    }

    public static Boolean modifyReservation(Reservation newInfo, Date oldStart){
        return ReservationServices.modifyReservation(newInfo,oldStart);
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



}
