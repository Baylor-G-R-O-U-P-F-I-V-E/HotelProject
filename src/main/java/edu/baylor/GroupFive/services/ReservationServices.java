package edu.baylor.GroupFive.Services;

import edu.baylor.GroupFive.database.reservationDAO.Reservation;
import edu.baylor.GroupFive.database.reservationDAO.ReservationDatabaseConnection;

import java.time.temporal.ChronoUnit;
import java.util.Date;

//public class ReservationServices {
//
//    public static String addReservation(Date startDate, Date endDate, String roomID, String guestID){
//
//        RoomDatabaseConnection roomConn = new RoomDatabaseConnection();
//        Room currRoom = roomConn.getRoom(roomID);
//        if(currRoom == null) return null;
//        Double roomPrice = currRoom.dailyPrice;
//
//        Double stayPrice = roomPrice * ChronoUnit.DAYS.between(startDate.toInstant(),endDate.toInstant());
//
//        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
//        //-1 is a tempID as id will be assigned in addReservation
//        Reservation newReservation = new Reservation(startDate,endDate,guestID,roomID, "-1", stayPrice);
//        Boolean isAvailable = conn.checkIfAvailable(roomID,startDate,endDate);
//
//        if(!isAvailable){return null;}
//
//        String reservationID = conn.addReservation(newReservation);
//        Boolean isSaved = conn.save();
//        if(!isSaved) return null;
//
//        return reservationID;
//    }
//
//
//
//}