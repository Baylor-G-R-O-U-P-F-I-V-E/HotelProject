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

public class ReservationController {
    private static final Logger logger = LogManager.getLogger(ReservationController.class.getName());
    private static final Marker RESERVATIONS = MarkerManager.getMarker("RESERVATIONS");

    private ReservationController() {}

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

    public static List<Reservation> getReservationsForUser(String username) {
        logger.info("Getting reservations for user " + username);

        List<Reservation> reservations = ReservationServices.getReservationsByGuest(username);
        logger.info("Returning reservations for user " + username);
        return reservations;

    }

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
        }

        return false;
    }

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
