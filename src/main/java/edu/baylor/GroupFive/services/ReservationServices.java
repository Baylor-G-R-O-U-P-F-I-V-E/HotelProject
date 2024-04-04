package edu.baylor.GroupFive.services;

import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.database.reservationDAO.ReservationDatabaseConnection;
import edu.baylor.GroupFive.database.roomDAO.RoomDatabaseConnection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

public class ReservationServices {

    public static List<Reservation> getReservations() throws SQLException{
        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
        List<Reservation> reservations = conn.getReservations();
        return reservations;
    }
    
    /*
    public static Reservation getReservation(String reservationID) throws SQLException{
        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
        Reservation reservation = conn.getReservation(reservationID);
        return reservation;
    }
    */

    public static String addReservation(Date startDate, Date endDate, String roomID, String guestID) throws SQLException{

        RoomDatabaseConnection roomConn = new RoomDatabaseConnection();
        Room currRoom = roomConn.getRoom(Integer.parseInt(roomID));
        if(currRoom == null) return null;
            Double roomPrice = currRoom.getDailyPrice();

        Double stayPrice = roomPrice * ChronoUnit.DAYS.between(startDate.toInstant(),endDate.toInstant());

        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
        //-1 is a tempID as id will be assigned in addReservation
        Reservation newReservation = new Reservation(startDate,endDate,guestID,roomID,  stayPrice);
        Boolean isAvailable = conn.checkIfAvailable(roomID,startDate,endDate);

        if(!isAvailable){return null;}

        Integer reservationID = conn.addReservation(newReservation);

        //if reservationID is null, then the reservation was not added

        if (reservationID == null) {
            return null;
        } else {
            return reservationID.toString();
        }
        
    }




}