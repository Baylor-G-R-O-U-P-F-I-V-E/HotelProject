package edu.baylor.GroupFive.database.controllers;

import edu.baylor.GroupFive.util.CoreUtils;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.database.services.ReservationServices;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.Level;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * This class represents a handler between our UI layer and our application
 * layer. Methods from this class are called from our UI layer. {@code ReservationController}
 * then determines which Service object in our application layer to continue 
 * operations with. The status of each operation is then returned to the UI layer.
 *
 * @author Brendon
 * @author Icko
 * @author Chase
 * @author Cole
 * */
public class ReservationController {
    private static final Logger logger = LogManager.getLogger(ReservationController.class.getName());
    private static final Marker RESERVATIONS = MarkerManager.getMarker("RESERVATIONS");

    private ReservationController() {}

    /**
     * This function returns all reservations in our database.
     *
     * @return List of all reservations in database
     * @see ReservationServices#getAll()
     * */
    public static List<Reservation> getAllReservations() {
        ReservationServices rs = new ReservationServices();
        logger.info("Getting all reservations");

        try {
            List<Reservation> result = rs.getAll();
            logger.info("Returning reservations");
            return result;
        } catch (SQLException ex) {
            logger.log(Level.WARN, "SQLException getting all reservations");
            logger.info(CoreUtils.stackTraceToString(ex));
        }

        return null;
    }

    /**
     * This function searches for a reservation given a room number and 
     * start date and returns that reservation if it exists.
     *
     * @param roomNumber Room number of room.
     * @param startDate Starting date.
     * @return Reservation matching {@code roomNumber} and {@code startDate}
     * @see ReservationServices#get(int, Date)
     * */
    public static Reservation getReservation(int roomNumber, Date startDate) {
        ReservationServices rs = new ReservationServices();
        logger.info("Getting reservation with room " + roomNumber + " and startDate " + startDate);

        try {
            Reservation reservation = rs.get(roomNumber, startDate);
            logger.info("Returning reservation with room " + roomNumber + " and startDate " + startDate);
            return reservation;
        } catch (SQLException ex) {
            logger.log(Level.WARN, "SQLException getting reservation with room " + roomNumber + " and startDate " + startDate);
        }

        return null;
    }

    /**
     * This function returns all reservations associated with a user
     * matching a specific {@code username}.
     *
     * @param username Users username
     * @return List of reservations tied to {@code username}
     * @see ReservationServices#getReservationsByGuest(String)
     * */
    public static List<Reservation> getReservationsForUser(String username) {
        logger.info("Getting reservations for user " + username);

        List<Reservation> reservations = ReservationServices.getReservationsByGuest(username);
        logger.info("Returning reservations for user " + username);
        return reservations;

    }

    /**
     * This function searches for a reservation in our database with a
     * given {@code id}. If it exists, it is returned, otherwise {@code null}.
     *
     * @param id Id of reservation
     * @return Reservation with id {@code id}. {@code null} otherwise.
     * @see ReservationServices#get(int)
     * */
    public static Reservation getReservation(int id) {
        ReservationServices rs = new ReservationServices();
        logger.info("Getting reservation with id " + id);

        try {
            Reservation reservation = rs.get(id);
            logger.info("Returning reservation with id " + id);
            return reservation;
        } catch (SQLException ex) {
            logger.log(Level.WARN, "SQLException getting reservation with id " + id);
        }

        return null;
    }

    /**
     * This function takes in a reservation and saves it in our database.
     *
     * @param reservation Reservation to save.
     * @return {@code true} if reservation was saved successfully. {@code false} otherwise
     * @see ReservationServices#save(Reservation)
     * */
    public static boolean saveReservation(Reservation reservation) {
        ReservationServices rs = new ReservationServices();
        logger.info("Attempting to save reservation with id " + reservation.getDbId());

        try {
            int result = rs.save(reservation);
            logger.info("Number of lines affected by query: " + result);
            if (result > 1 || result < 0) {
                logger.log(Level.ERROR, "Multiple lines changed by save");
            }
            return true;
        } catch (SQLException ex) {
            logger.log(Level.WARN, "SQLException saving reservation with id " + reservation.getDbId());
        }

        return false;
    }

    /**
     * This function takes in a new reservation and saves it in our database.
     *
     * @param reservation Reservation to insert.
     * @return {@code true} if reservation was saved successfully. {@code false} otherwise
     * @see ReservationServices#insert(Reservation)
     * */
    public static boolean createReservation(Reservation reservation) {
        ReservationServices rs = new ReservationServices();
        logger.info("Attempting to create reservation with id " + reservation.getDbId());

        try {
            int result = rs.insert(reservation);
            logger.info("Number of lines affected by query: " + result);
            if (result > 1 || result < 0) {
                logger.log(Level.ERROR, "Multiple lines changed by create");
            }
            return true;
        } catch (SQLException ex) {
            logger.log(Level.WARN, "SQLException creating reservation with id " + reservation.getDbId());
        }

        return false;
    }

    /**
     * This function takes in a reservation with updated information and
     * updates our database.
     *
     * @param reservation Reservation to update.
     * @return {@code true} if reservation was modified successfully. {@code false} otherwise
     * @see ReservationServices#update(Reservation)
     * */
    public static boolean modifyReservation(Reservation reservation) {
        ReservationServices rs = new ReservationServices();
        logger.info("Attempting to modify reservation with id " + reservation.getDbId());

        try {
            int result = rs.update(reservation);
            logger.info("Number of lines affected by query: " + result);
            if (result > 1 || result < 0) {
                logger.log(Level.ERROR, "Multiple lines changed by create");
            }
            return true;
        } catch (SQLException ex) {
            logger.log(Level.WARN, "SQLException modifying reservation with id " + reservation.getDbId());
            throw new RuntimeException(ex);
        }

        //return false;
    }

    /**
     * This function takes in a reservation and cancels it in our database.
     *
     * @param reservation Reservation to cancel.
     * @return {@code true} if reservation was cancelled successfully. {@code false} otherwise
     * @see ReservationServices#delete(Reservation)
     * */
    public static boolean cancelReservation(Reservation reservation) {
        ReservationServices rs = new ReservationServices();
        logger.info("Attempting to cancel reservation with id " + reservation.getDbId());

        try {
            int result = rs.delete(reservation);
            logger.info("Number of lines affected by query: " + result);
            if (result > 1 || result < 0) {
                logger.log(Level.ERROR, "Multiple lines changed by create");
            }
            return true;
        } catch (SQLException ex) {
            logger.log(Level.WARN, "SQLException cancelling reservation with id " + reservation.getDbId());
        }

        return false;
    }

    /**
     * This function returns all active reservations in our database.
     * @return List of all active reservations in database
     */
    public static List<Reservation> getAllActiveReservations() {
        ReservationServices rs = new ReservationServices();
        List<Reservation> reservations = rs.getAllActive();
        return reservations;
    }

    /**
     * This function takes in a pair of start and end dates and determines
     * if there is any overlap.
     *
     * @param start1 Start date of interval 1. This is a {@code java.util.Date} object.
     * @param end1 End date of interval 1. This is a {@code java.util.Date} object.
     * @param start2 Start date of interval 2. This is a {@code java.util.Date} object.
     * @param end2 End date of interval 2. This is a {@code java.util.Date} object.
     * @return {@code true} if there is an overlap. {@code false} otherwise
     * */
    private static boolean isOverlap(Date start1, Date end1, Date start2, Date end2) {
        return !(end2.before(start1) || start2.after(end1));
    }

    /**
     * This function looks through our reservations and checks if a given
     * room is booked during a certain time period.
     *
     * @param roomNumber Room number of Room.
     * @param startDate Starting date of interval.
     * @param endDate End date of interval.
     * @return {@code true} if room is booked. {@code false} otherwise
     * */
    public static boolean isRoomBookedOn(int roomNumber, Date startDate, Date endDate){
        List<Reservation> reservations = getAllActiveReservations();
        List<Reservation> roomReservations = reservations.stream()
            .filter(rsv -> rsv.getRoomNumber() == roomNumber)
            .toList();
        return roomReservations.stream().anyMatch(rsv ->
            isOverlap(startDate, endDate, rsv.getStartDate(), rsv.getEndDate()));
    }

    /**
     * This function returns a list of all reservations tied to a certain room.
     *
     * @param roomNumber Room number to query across
     * @return List of reservations linked to {@code roomNumber}
     * */
    public static List<Reservation> getRoomReservations(int roomNumber) {
        List<Reservation> reservations = getAllReservations();
        return reservations.stream()
            .filter(rsv -> rsv.getRoomNumber() == roomNumber)
            .toList();
    }

}
