package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.database.daos.ReservationDAO;
import edu.baylor.GroupFive.database.daos.RoomDAO;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class ReservationServices {

    public static List<Reservation> getReservations() /* throws BadConnectionException */  {
        ReservationDAO conn = new ReservationDAO();
        return conn.getReservations();
    }
    
    /*
    public static Reservation getReservation(String reservationID) throws SQLException{
        ReservationDAO conn = new ReservationDAO();
        Reservation reservation = conn.getReservation(reservationID);
        return reservation;
    }
    */

    public static Boolean addReservation(Date startDate, Date endDate, String roomID, String guestID) /* throws BadConnectionException */  {

        RoomDAO roomConn = new RoomDAO();
        Room currRoom = roomConn.getRoom(Integer.parseInt(roomID));
        if(currRoom == null) return null;
            Double roomPrice = currRoom.getDailyPrice();

        Double stayPrice = roomPrice * ChronoUnit.DAYS.between(startDate.toInstant(),endDate.toInstant());

        ReservationDAO conn = new ReservationDAO();
        //-1 is a tempID as id will be assigned in addReservation
        Reservation newReservation = new Reservation(startDate,endDate,guestID,roomID, stayPrice);
        Boolean isAvailable = conn.checkIfAvailable(roomID,startDate,endDate);

        if(!isAvailable){return null;}

        Boolean added = conn.addReservation(newReservation);

        //if reservationID is null, then the reservation was not added

        if (!added) {
            return false;
        }

        return true;
        
    }


    public static Boolean modifyReservation(Reservation newInfo, String originalRoom, Date oldStartDate) /* throws BadConnectionException */  {
        ReservationDAO conn = new ReservationDAO();
        Reservation temp;

        temp = conn.getInfo(Integer.parseInt(originalRoom), oldStartDate);


        if(temp == null){
            return false;
        }

        Boolean erased = conn.cancelReservation(Integer.parseInt(originalRoom), oldStartDate);
        if(!erased){
            return false;
        }

        return conn.addReservation(newInfo);

    }

    public static Boolean cancelReservation(Integer roomNumber, Date startDate) /* throws BadConnectionException */  {
        ReservationDAO conn = new ReservationDAO();
        return conn.cancelReservation(roomNumber,startDate);
    }

    public static Reservation getInfo(Integer roomNumber, Date startDate) /* throws BadConnectionException */  {
        ReservationDAO conn = new ReservationDAO();
        return conn.getInfo(roomNumber,startDate);
    }

    public static Boolean checkIfAvailable(Integer roomNumber, Date startDate, Date endDate) /* throws BadConnectionException */ {
        ReservationDAO conn = new ReservationDAO();
        return conn.checkIfAvailable(roomNumber.toString(),startDate,endDate);
    }




}
