package edu.baylor.GroupFive.database.reservationDAO;

import java.io.*;
import java.nio.file.FileSystemNotFoundException;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;
import java.util.Date;

import edu.baylor.GroupFive.models.Reservation;

public class ReservationDatabaseConnection {

    public ReservationDatabaseConnection(){}

    private Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:derby:FinalProject;", "", "");
            if(connection == null) {
                System.out.println("Could not connect");
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
        return connection;
    }

    public String addReservation(Reservation reservation) throws SQLException {
        Connection connection = getConnection();
        if(connection == null){
            System.out.println("Connection Failed");
            return null;
        }
        Statement statement = null;
        String rowID = null;
        // startDate endDate price guestID roomID
        String sqlInsert = "INSERT INTO Reservation(START_DATE,END_DATE,PRICE,GUEST_ID,ROOM_ID) VALUES('" + formatDate(reservation.getStartDate()) + "','" +
                formatDate(reservation.getEndDate()) + "'," + reservation.getPrice() + "," + reservation.getGuestID() + "," + reservation.getRoomID() + ")";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlInsert);
            ResultSet r = statement.executeQuery("SELECT MAX(reservationID) FROM RESERVATION");
            rowID = r.getString("reservationID");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }


        return rowID;
    }


    public List<Reservation> getReservations(){
        Connection connection =  getConnection();
        if(connection == null){
            System.out.println("Connection Failed");
            return null;
        }

        Statement statement = null;
        String sqlQuery = "SELECT * FROM Reservation";
        ResultSet rs = null;
        List<Reservation> output = new ArrayList<>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlQuery);
            while(rs.next()){
                Reservation out = new Reservation(rs.getDate("startDate"),
                        rs.getDate("endDate"),
                        rs.getString("guestID"),
                        rs.getString("roomID"),
                        rs.getString("reservationID"),
                        rs.getDouble("price"));
                output.add(out);

            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
        return output;
    }

    public Boolean cancelReservation(String reservationID) throws SQLException {


        Connection connection = getConnection();
        if(connection == null){
            System.out.println("Connection Failed");
            return null;
        }

        Statement statement = null;
        String sqlDelete = "DELETE FROM reservation WHERE reservationID = '" + reservationID + "'";
        try {
            statement = connection.createStatement();
            statement.execute(sqlDelete);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        return true;


    }

    public Reservation getInfo(String reservationID) throws SQLException {
        Connection connection = getConnection();
        if(connection == null){
            System.out.println("Connection Failed");
            return null;
        }

        ResultSet rs = null;
        Statement statement = null;
        String sqlQuery = "SELECT * FROM reservation WHERE reservationID = 'reservationID'";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlQuery);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        rs.next();
        Reservation out = new Reservation(rs.getDate("startDate"),
                rs.getDate("endDate"),
                rs.getString("guestID"),
                rs.getString("roomID"),
                rs.getString("reservationID"),
                rs.getDouble("price"));
        return out;

    }



    public Boolean checkIfAvailable(String roomID, Date startDate, Date endDate) throws SQLException {
        //'20150131'
        Connection connection = getConnection();
        if(connection == null){
            System.out.println("Connection Failed");
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd", Locale.ENGLISH);

        ResultSet rs = null;
        Statement statement = null;
        String sqlQuery = "SELECT * FROM reservation WHERE roomid='" + roomID + "'";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlQuery);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }finally {
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
        ArrayList<ArrayList<Date>> mem = new ArrayList<>();
        while(rs.next()){
            ArrayList<Date> temp = new ArrayList<>();
            temp.add(rs.getDate("startDate"));
            temp.add(rs.getDate("endDate"));
            mem.add(temp);
        }

        for(ArrayList<Date> r : mem){

            if((startDate.after(r.get(0)) || startDate.equals(r.get(0))) && startDate.before(r.get(1))){
                return false;
            }
            if(endDate.after(r.get(0)) && (endDate.equals(r.get(1)) || endDate.before(r.get(1)))){
                return false;
            }

            if(startDate.after(r.get(0)) || startDate.equals(r.get(0)) &&
                (endDate.equals(r.get(1)) || endDate.before(r.get(1)))){
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