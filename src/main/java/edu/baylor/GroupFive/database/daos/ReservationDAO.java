package edu.baylor.GroupFive.database.daos;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.BadPaddingException;

import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;
import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.models.Reservation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReservationDAO extends BaseDAO{
    private static final Logger logger = LogManager.getLogger(ReservationDAO.class.getName());

    public ReservationDAO(){}

    //works well enough


     /**
      * addReservation
      *
      * pre-conditions:
      *     * reservation is initialized with the information of a Reservation to add
      *
      * post-condition:
      *     * Reservation is added to database
      *     * Throws BadConnectionException if connection cannot be established to database
      *
      * return:
      *     * false if error occurs
      * */
    //works well enough
    public static Boolean addReservation(Reservation reservation) {
        Connection connection = getConnection();
        if(connection == null){
            logger.info("Connection Failed");
            // throw new BadConnectionException();
            return null;
        }
        Statement statement = null;

        // startDate endDate price guestUsername roomNumber

        String sqlInsert = "INSERT INTO Reservations(STARTDATE,ENDDATE,PRICE,GUESTUsername,ROOMNumber) VALUES('" + formatDate(reservation.getStartDate()) + "','" +
                formatDate(reservation.getEndDate()) + "'," + reservation.getPrice() + ",'" + reservation.getGuestUsername() + "'," + reservation.getRoomNumber() + ")";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            logger.info(e.getMessage());
            return false;
        }finally {
            closeConnection(connection);
            closeStatement(statement);
        }
        return true;
    }


    //works well enough
    public static  List<Reservation> getReservations() /* throws BadConnectionException */ {
        Connection connection =  getConnection();
        if(connection == null){
            logger.info("Connection Failed");
            // throw new BadConnectionException();
            return null;
        }

        Statement statement = null;
        String sqlQuery = "SELECT * FROM Reservations";
        ResultSet rs = null;
        List<Reservation> output = new ArrayList<>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlQuery);
            while(rs.next()){
                Reservation out = new Reservation(rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getString("guestUsername"),
                        rs.getString("roomNumber"),
                        rs.getDouble("price"));
                output.add(out);
            }


        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return output;
    }

    //works well enough
    public static Boolean cancelReservation(Integer roomNumber, Date startDate) /* throws BadConnectionException */ {
        Connection connection = getConnection();
        if(connection == null){
            logger.info("Connection Failed");
            // throw new BadConnectionException();
            return null;
        }

        Statement statement = null;
        String sqlDelete = "DELETE FROM reservations WHERE roomNumber = " + roomNumber + " AND startDate = '" + formatDate(startDate) + "'";
        try {
            statement = connection.createStatement();
            statement.execute(sqlDelete);


        } catch (SQLException e) {
            logger.info(e.getMessage());
            return false;
        }finally {
            closeStatement(statement);
            closeConnection(connection);
        }
        return true;


    }

    //works well enough
    public static Reservation getInfo(Integer roomNumber, Date startDate) /* throws BadConnectionException */ {
        Connection connection = getConnection();
        if(connection == null){
            logger.info("Connection Failed");
            // throw new BadConnectionException();
            return null;
        }

        ResultSet rs;
        ResultSet id;
        Statement statement= null;

        String sqlQuery = "SELECT * FROM reservations WHERE roomNumber = " + roomNumber + " AND startDate = '" + formatDate(startDate) + "'";
        try {
            statement = connection.createStatement();

            rs = statement.executeQuery(sqlQuery);
            while(rs.next()){
                Reservation out = new Reservation(rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getString("guestUsername"),
                        rs.getString("roomNumber"),
                        rs.getDouble("price"));

                return out;
            }

        } catch (SQLException e) {
            logger.info(e.getMessage());
            return null;
        }finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        return null;
    }


    public static Boolean checkIfAvailable(String roomNumber, Date startDate, Date endDate) /* throws BadConnectionException */ {
        //'20150131'
        Connection connection = getConnection();
        if(connection == null){
            logger.info("Connection Failed");
            // throw new BadConnectionException();
            return null;
        }

        ArrayList<ArrayList<Date>> mem;
        ResultSet rs = null;
        Statement statement = null;
        String sqlQuery = "SELECT * FROM reservations WHERE roomNumber=" + roomNumber;
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlQuery);
            mem = new ArrayList<>();
            while(rs.next()){
                ArrayList<Date> temp = new ArrayList<>();
                temp.add(rs.getDate("startDate"));
                temp.add(rs.getDate("endDate"));
                mem.add(temp);
            }

        } catch (SQLException e) {
            logger.info("RDC check if available failed");
            logger.info(e.getMessage());
            return null;
        }finally {
            closeStatement(statement);
            closeConnection(connection);
        }

        for(ArrayList<Date> r : mem){
            if(isOverlapping(startDate,endDate,r.get(0),r.get(1))){
                return false;
            }
        }
        return true;
    }

    private static Boolean isOverlapping(Date startOne, Date endOne, Date startTwo, Date endTwo){
        //r is two
        logger.info(startTwo + " " + endTwo + " : " + startOne + " " + endOne);

        if((startOne.after(startTwo) || startOne.equals(startTwo)) && startOne.before(endTwo)){
            logger.info("3");
            return true;
        }
        if(endOne.after(startTwo) && (endOne.equals(endTwo) || endOne.before(endTwo))){
            logger.info("2");
            return true;
        }

        if((startOne.before(startTwo) || startOne.equals(startTwo)) &&
                (endOne.equals(endTwo) || endOne.after(endTwo))){
            logger.info("1");
            return true;
        }
        return false;
    }


}
