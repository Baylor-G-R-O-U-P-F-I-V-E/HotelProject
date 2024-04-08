package edu.baylor.GroupFive.services;

import edu.baylor.GroupFive.controllers.ReservationController;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.database.reservationDAO.ReservationDatabaseConnection;
import edu.baylor.GroupFive.database.roomDAO.RoomDatabaseConnection;
import edu.baylor.GroupFive.exceptions.BadConnectionException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class ReservationServices {

    public static List<Reservation> getReservations() /* throws BadConnectionException */  {
        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
        return conn.getReservations();
    }
    
    /*
    public static Reservation getReservation(String reservationID) throws SQLException{
        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
        Reservation reservation = conn.getReservation(reservationID);
        return reservation;
    }
    */

    public static Boolean addReservation(Date startDate, Date endDate, String roomID, String guestID) /* throws BadConnectionException */  {

        RoomDatabaseConnection roomConn = new RoomDatabaseConnection();
        Room currRoom = roomConn.getRoom(Integer.parseInt(roomID));
        if(currRoom == null) return null;
            Double roomPrice = currRoom.getDailyPrice();

        Double stayPrice = roomPrice * ChronoUnit.DAYS.between(startDate.toInstant(),endDate.toInstant());

        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
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
        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
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
        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
        return conn.cancelReservation(roomNumber,startDate);
    }

    public static Reservation getInfo(Integer roomNumber, Date startDate) /* throws BadConnectionException */  {
        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
        return conn.getInfo(roomNumber,startDate);
    }

    public static Boolean checkIfAvailable(Integer roomNumber, Date startDate, Date endDate) /* throws BadConnectionException */ {
        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
        return conn.checkIfAvailable(roomNumber.toString(),startDate,endDate);
    }




}