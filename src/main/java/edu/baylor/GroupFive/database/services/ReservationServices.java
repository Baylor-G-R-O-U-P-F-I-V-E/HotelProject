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
import java.util.List;
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

     /**
      * get
      *
      * Singular find. Searches for a reservation based on their database id.
      * Throws SQLException if an error occurs communicating with the database.
      * */
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
                    rs.getInt("roomNumber"),
                    rs.getDouble("price"),
                    rs.getBoolean("active"),
                    rs.getBoolean("checkedIn")
                    );
        }

        // Close resources
        statement.close();
        connection.close();

        return out;
    }

    public Reservation get(int roomNumber, Date startDate) throws SQLException {
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
        String sqlQuery = "SELECT * FROM reservations WHERE roomNumber = ? AND startDate = ?";
        PreparedStatement statement = connection.prepareStatement(sqlQuery); // [1]
        statement.setInt(1, roomNumber);
        statement.setDate(2, CoreUtils.getSqlDate(startDate));

        // Execute query
        ResultSet rs = statement.executeQuery();
        if (rs.next()) {
            out = new Reservation(
                    rs.getInt("id"),
                    rs.getDate("startDate"),
                    rs.getDate("endDate"),
                    rs.getString("guestUsername"),
                    rs.getInt("roomNumber"),
                    rs.getDouble("price"),
                    rs.getBoolean("active"),
                    rs.getBoolean("checkedIn")
                    );
        }

        // Close resources
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
                    rs.getInt("roomNumber"),
                    rs.getDouble("price"),
                    rs.getBoolean("active"),
                    rs.getBoolean("checkedIn")));
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
        // 1      2        3      4         5           6        7        8
        // id startDate endDate price guestUsername roomNumber active checkedIn
        String sqlInsert = "INSERT INTO Reservations(ID,STARTDATE,ENDDATE,PRICE,GUESTUsername,ROOMNumber,ACTIVE,CHECKEDIN) VALUES( ?, ?, ?, ?, ?, ?, ?, ? )";
        PreparedStatement statement = connection.prepareStatement(sqlInsert);
        statement.setInt(1, reservation.getDbId());
        statement.setDate(2, CoreUtils.getSqlDate(reservation.getStartDate()));
        statement.setDate(3, CoreUtils.getSqlDate(reservation.getEndDate()));
        statement.setDouble(4, reservation.getPrice());
        statement.setString(5, reservation.getGuestUsername());
        statement.setInt(6, reservation.getRoomNumber());
        statement.setBoolean(7, reservation.getActiveStatus());
        statement.setBoolean(8, reservation.getCheckedInStatus());

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
        //     1       2       3      4         5        6       7      8
        // startDate,endDate,price,username,roomNumber,active,checkedIn,id
        String sqlUpdate = "UPDATE Reservations set STARTDATE = ?, ENDDATE = ?, PRICE = ?, GUESTUsername = ?, ROOMNumber = ?, ACTIVE = ?, CHECKEDIN = ? where id = ?";
        PreparedStatement statement = connection.prepareStatement(sqlUpdate);
        statement.setDate(1, CoreUtils.getSqlDate(reservation.getStartDate()));
        statement.setDate(2, CoreUtils.getSqlDate(reservation.getEndDate()));
        statement.setDouble(3, reservation.getPrice());
        statement.setString(4, reservation.getGuestUsername());
        statement.setInt(5, reservation.getRoomNumber());
        statement.setBoolean(6, reservation.getActiveStatus());
        statement.setBoolean(7, reservation.getCheckedInStatus());
        statement.setInt(8, reservation.getDbId());

        // Execute query
        int result = statement.executeUpdate();

        // Close connections
        statement.close();
        connection.close();

        return result;
    }

    // FIXME DO NOT ACTUALLY DELETE FROM DB, 
    // TODO status for reservations
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
        // String sqlDelete = "DELETE FROM reservations WHERE id = ?";
        String sqlDelete = "UPDATE Reservations set active = ? WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sqlDelete);
        statement.setBoolean(1, false);
        statement.setInt(2, reservation.getDbId());

        // Execute query
        int result = statement.executeUpdate();

        // Close connections
        statement.close();
        connection.close();
        
        return result;
    }

    public Boolean checkIfAvailable(int roomNumber, Date startDate, Date endDate) throws SQLException {
        //'20150131'
        // Establish database connection
        Connection connection;
        try {
            connection =  DbConnection.getConnection();
        } catch (BadConnectionException ex) {
            logger.info("DbConnection failed");
            return null;
        }

        // Build query
        String sqlQuery = "SELECT * FROM reservations WHERE roomNumber = ? AND startDate = ?";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setInt(1, roomNumber);
        statement.setDate(2, CoreUtils.getSqlDate(startDate));

        // Execute query
        ResultSet rs = statement.executeQuery();

        // Check violations
        while (rs.next()) {
            if (rs.getBoolean("active") == true && !isOverlap(startDate, endDate, rs.getDate("startDate"), rs.getDate("endDate"))) {
                return false;
            }
        }

        // Close connections
        statement.close();
        connection.close();

        return true;
    }

    // TODO need to test this
    public Boolean checkIfAvailable(Reservation reservation) throws SQLException {
        //'20150131'
        // Establish database connection
        Connection connection;
        try {
            connection =  DbConnection.getConnection();
        } catch (BadConnectionException ex) {
            logger.info("DbConnection failed");
            return null;
        }

        // Build query
        String sqlQuery = "SELECT * FROM reservations WHERE roomNumber = ? AND startDate = ?";
        PreparedStatement statement = connection.prepareStatement(sqlQuery);
        statement.setInt(1, reservation.getRoomNumber());
        statement.setDate(2, CoreUtils.getSqlDate(reservation.getStartDate()));

        // Execute query
        ResultSet rs = statement.executeQuery();

        // Check violations
        while (rs.next()) {
            // Date endDate = rs.getDate("endDate");
            // if (endDate.compareTo(reservation.getEndDate()) != 0 || rs.getBoolean("active") == true) {
                // return false;
            // }
            if (rs.getBoolean("active") == true && !isOverlap(reservation.getStartDate(), reservation.getEndDate(), rs.getDate("startDate"), rs.getDate("endDate"))) {
                return false;
            }
        }

        // Close connections
        statement.close();
        connection.close();

        return true;

        // ArrayList<ArrayList<Date>> mem;
        // try {
        //     statement = connection.createStatement();
        //     rs = statement.executeQuery(sqlQuery);
        //     mem = new ArrayList<>();
        //     while(rs.next()){
        //         ArrayList<Date> temp = new ArrayList<>();
        //         temp.add(rs.getDate("startDate"));
        //         temp.add(rs.getDate("endDate"));
        //         mem.add(temp);
        //     }

        // } catch (SQLException e) {
        //     logger.info("RDC check if available failed");
        //     logger.info(e.getMessage());
        //     return null;
        // }finally {
        //     if (statement != null) {
        //         try {
        //             statement.close();
        //         } catch (SQLException e) {
        //             throw new RuntimeException(e);
        //         }
        //     }
        //     if (connection != null) {
        //         try {
        //             connection.close();
        //         } catch (SQLException e) {
        //             throw new RuntimeException(e);
        //         }
        //     }
        // }

        // for(ArrayList<Date> r : mem){
        //     logger.info(r.get(0) + " " + r.get(1) + " : " + startDate + " " + endDate);

        //     if((startDate.after(r.get(0)) || startDate.equals(r.get(0))) && startDate.before(r.get(1))){
        //         logger.info("3");
        //         return false;
        //     }
        //     if(endDate.after(r.get(0)) && (endDate.equals(r.get(1)) || endDate.before(r.get(1)))){
        //         logger.info("2");
        //         return false;
        //     }

        //     if((startDate.before(r.get(0)) || startDate.equals(r.get(0))) &&
        //         (endDate.equals(r.get(1)) || endDate.after(r.get(1)))){
        //         logger.info("1");
        //         return false;
        //     }
        // }
    }

    private static String formatDate(Date myDate) {
        DateFormat dateFormat = new SimpleDateFormat(CoreUtils.DATE_FORMAT); // before: "MM/dd/yyyy"
        return dateFormat.format(myDate.getTime());
    }

    private static boolean isOverlap(Date start1, Date end1, Date start2, Date end2) {
        return !start1.after(end2) && !end1.before(start2);
    }

    // TODO stolen from Cole... what does this do?
    private boolean isRoomBookedOn(int roomNumber, Date startDate, Date endDate) throws SQLException {
        List<Reservation> reservations = getAll();
        List<Reservation> roomReservations = reservations.stream()
            .filter(rsv -> rsv.getRoomNumber() == roomNumber)
            .toList();
        return roomReservations.stream().anyMatch(rsv ->
            isOverlap(startDate, endDate, rsv.getStartDate(), rsv.getEndDate()));
    }

}

 /**
  * [1]: https://docs.oracle.com/javase/8/docs/api/java/sql/PreparedStatement.html#executeQuery--
  *     * PreparedStatement.executeQuery throws SQLException
  * */
