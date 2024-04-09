 /**
  * Authors: Icko, Cole
  * */

package edu.baylor.GroupFive.database.services;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;

import edu.baylor.GroupFive.util.CoreUtils;
import edu.baylor.GroupFive.database.daos.ReservationDao;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;
import edu.baylor.GroupFive.database.DbConnection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ReservationServices implements ReservationDao {
    private static final Logger logger = LogManager.getLogger(ReservationServices.class.getName());

    public ReservationServices(){}

    public Reservation get(int id) throws SQLException {
        Reservation out = null; // Result of our query

        // Establish database connection
        Connection connection = null;
        try {
            connection =  DbConnection.getConnection();
        } catch (BadConnectionException ex) {
            logger.info("DbConnection failed");
            return null;
        }

        // Build query
        String sqlQuery = "SELECT * FROM reservations WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sqlQuery); // [1]
        statement.setInt(1, id);

        // Execute query
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            out = new Reservation(
                    rs.getInt("id"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate"),
                    rs.getString("guestUsername"),
                    rs.getString("roomNumber"),
                    rs.getDouble("price")
                    );
        }

        // Close resources
        // TODO statement and connection should never be null, right?
        statement.close();
        connection.close();

        return out;
    }

    public List<Reservation> getAll() throws SQLException {
        List<Reservation> out = null; // Result of our query

        // Establish database connection
        Connection connection;
        try {
            connection =  DbConnection.getConnection();
        } catch (BadConnectionException ex) {
            logger.info("DbConnection failed");
            return null;
        }
        
        // Build query
        String sqlQuery = "SELECT * FROM Reservations";
        PreparedStatement statement = connection.prepareStatement(sqlQuery); // [1]

        // Execute query
        ResultSet rs = statement.executeQuery();
        while(rs.next()){
            if(out == null) {
                out = new ArrayList<>();
            }

            out.add(new Reservation(
                    rs.getInt("id"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate"),
                    rs.getString("guestUsername"),
                    rs.getString("roomNumber"),
                    rs.getDouble("price")
                        ));
        }

        return out;
    }

     /**
      * save
      *
      * This method either inserts or updates behind-the-scenes
      * */
    // TODO implement
    public Integer save(Reservation reservation) throws SQLException {
        return -1;
    }

    public Integer insert(Reservation reservation) throws SQLException {
        // Establish database connection
        Connection connection;
        try {
            connection = DbConnection.getConnection();
        } catch (BadConnectionException ex) {
            logger.info("DbConnection failed");
            return null;
        }

        // Build query
        // 1  2         3       4     5             6
        // id startDate endDate price guestUsername roomNumber
        String sqlInsert = "INSERT INTO Reservations(ID,STARTDATE,ENDDATE,PRICE,GUESTUsername,ROOMNumber) VALUES( ?, ?, ?, ?, ?, ? )";
        PreparedStatement statement = connection.prepareStatement(sqlInsert);
        statement.setInt(1, reservation.getId());
        statement.setDate(2, CoreUtils.getSqlDate(reservation.getStartDate()));
        statement.setDate(3, CoreUtils.getSqlDate(reservation.getEndDate()));
        statement.setDouble(4, reservation.getPrice());
        statement.setString(5, reservation.getGuestUsername());
        statement.setInt(6, reservation.getRoomNumber());

        // Execute query
        int result = statement.executeUpdate();

        // Close connections
        statement.close();
        connection.close();

        return result;
    }

    public Integer update(Reservation reservation) throws SQLException {
        // Establish database connection
        Connection connection;
        try {
            connection =  DbConnection.getConnection();
        } catch (BadConnectionException ex) {
            logger.info("DbConnection failed");
            return null;
        }

        // Build query
        String sqlUpdate = "UPDATE Reservations set STARTDATE = ?, ENDDATE = ?, PRICE = ?, GUESTUsername = ?, ROOMNumber = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sqlUpdate);
        statement.setDate(1, CoreUtils.getSqlDate(reservation.getStartDate()));
        statement.setDate(2, CoreUtils.getSqlDate(reservation.getEndDate()));
        statement.setDouble(3, reservation.getPrice());
        statement.setString(4, reservation.getGuestUsername());
        statement.setInt(6, reservation.getRoomNumber());
        statement.setInt(6, reservation.getId());

        // Execute query
        int result = statement.executeUpdate();

        // Close connections
        statement.close();
        connection.close();

        return result;
    }

    public Integer delete(Reservation reservation) throws SQLException {
        // Establish database connection
        Connection connection;
        try {
            connection =  DbConnection.getConnection();
        } catch (BadConnectionException ex) {
            logger.info("DbConnection failed");
            return null;
        }

        // Build query
        String sqlDelete = "DELETE FROM reservations WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sqlDelete);
        statement.setInt(1, reservation.getId());

        // Execute query
        int result = statement.executeUpdate();

        // Close connections
        statement.close();
        connection.close();
        
        return result;
    }

    //works well enough
     /**
      * TODO
      * changed from receiving roomnumber and startdate to receiving a Reservation
      * check where this fucks up calls
      *
      * Note: Was there a certain reason why we were taking in a room number and
      * start date instead of a reservation object?
      * */
    public Reservation getInfo(Integer roomNumber, Date startDate) throws SQLException {
        Connection connection;
        try {
            connection =  DbConnection.getConnection();
        } catch (BadConnectionException ex) {
            logger.info("DbConnection failed");
            return null;
        }

        ResultSet rs;
        ResultSet id;
        java.sql.Statement statement= null;

        String sqlQuery = "SELECT * FROM reservations WHERE roomNumber = " + roomNumber + " AND startDate = '" + formatDate(startDate) + "'";
        try {
            statement = connection.createStatement();

            rs = statement.executeQuery(sqlQuery);
            while(rs.next()){
                Reservation out = new Reservation(
                        rs.getInt("id"),
                        rs.getDate("startDate"),
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
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return null;
    }

    // TODO should roomNumber be changed from String to Integer??
    public Boolean checkIfAvailable(String roomNumber, Date startDate, Date endDate) throws SQLException {
        //'20150131'
        Connection connection;
        try {
            connection =  DbConnection.getConnection();
        } catch (BadConnectionException ex) {
            logger.info("DbConnection failed");
            return null;
        }

        ArrayList<ArrayList<Date>> mem;
        ResultSet rs = null;
        java.sql.Statement statement = null;
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
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        for(ArrayList<Date> r : mem){
            logger.info(r.get(0) + " " + r.get(1) + " : " + startDate + " " + endDate);

            if((startDate.after(r.get(0)) || startDate.equals(r.get(0))) && startDate.before(r.get(1))){
                logger.info("3");
                return false;
            }
            if(endDate.after(r.get(0)) && (endDate.equals(r.get(1)) || endDate.before(r.get(1)))){
                logger.info("2");
                return false;
            }

            if((startDate.before(r.get(0)) || startDate.equals(r.get(0))) &&
                (endDate.equals(r.get(1)) || endDate.after(r.get(1)))){
                logger.info("1");
                return false;
            }
        }
        return true;
    }

    private static String formatDate(Date myDate) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(myDate.getTime());
    }

}

 /**
  * [1]: https://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html#executeQuery--
  *     * PreparedStatement.executeQuery throws SQLException
  * */
