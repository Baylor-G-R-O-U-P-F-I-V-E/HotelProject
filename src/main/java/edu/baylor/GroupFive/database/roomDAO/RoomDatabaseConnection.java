package edu.baylor.GroupFive.database.roomDAO;

import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.Room;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RoomDatabaseConnection {



    public RoomDatabaseConnection(){}
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

    public List<Room> getRooms(){

        Connection connection =  getConnection();
        if(connection == null){
            System.out.println("Connection Failed");
            return null;
        }

        Statement statement = null;
        String sqlQuery = "SELECT * FROM Room";
        ResultSet rs = null;
        List<Room> output = new ArrayList<>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlQuery);
            //"INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (101,'High', 'Jungle',1,3,98.22)" ,
            //Room(int roomNumber, int quality, THEME theme, boolean smoking, int numBeds, BED_TYPE bedType, double dailyPrice) {
            while(rs.next()){
                Room out = new Room(rs.getInt("roomNumber"),
                        rs.getInt("quality"),
                        Room.THEME.ThemeB,
                        rs.getBoolean("smoking"),
                        rs.getInt("numbeds"),
                        Room.BED_TYPE.QUEEN,
                        rs.getDouble("dailyPrice")
                        );
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


        return null;
    }


    /*
     *
     * 
     * 
     */
    public Boolean addRoom(Room newRoom){
        Connection connection = getConnection();
        if(connection == null){
            System.out.println("Connection Failed");
            return null;
        }
        Statement statement = null;
        String rowID = null;
        // startDate endDate price guestID roomID
        String sqlInsert = "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (" +
                newRoom.getRoomNumber().toString() + "," + newRoom.getQuality().toString() +
                ",'ThemeNotIntegrated'," + newRoom.getSmoking().toString() + "," +
                "'NOTDONE'" + "," +
                newRoom.getNumBeds().toString()  + "," +
                newRoom.getDailyPrice().toString() + ")";
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
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


        return true;
    }

    /*
     * CHECK THIS METHOD
     * i added it just to make vscode happy - Brendon
     * 
     * 
     
     */
    public Room getRoom(Integer roomNumber){
        Connection connection =  getConnection();
        if(connection == null){
            System.out.println("Connection Failed");
            return null;
        }

        Statement statement = null;
        String sqlQuery = "SELECT * FROM Room WHERE roomNumber = " + roomNumber.toString();
        ResultSet rs = null;

        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlQuery);
            //"INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (101,'High', 'Jungle',1,3,98.22)" ,
            //Room(int roomNumber, int quality, THEME theme, boolean smoking, int numBeds, BED_TYPE bedType, double dailyPrice) {
            while(rs.next()){
                Room out = new Room(rs.getInt("roomNumber"),
                        rs.getInt("quality"),
                        Room.THEME.ThemeA,
                        rs.getBoolean("smoking"),
                        rs.getInt("numbeds"),
                        Room.BED_TYPE.KING,
                        rs.getDouble("dailyPrice")
                );
                return out;

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


        return null;
    }



    public Boolean modifyRoom(Room updatedInfo){
        Connection connection =  getConnection();
        if(connection == null){
            System.out.println("Connection Failed");
            return null;
        }



        //just checking we already have the room in the db
        Room exists = getRoom(updatedInfo.getRoomNumber());
        if(exists == null){
            return false;
        }

        Statement statement = null;
        String sqlDelete = "DELETE FROM room WHERE roomNumber = " + updatedInfo.getRoomNumber();
        try {
            statement = connection.createStatement();
            statement.execute(sqlDelete);


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
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

        return  addRoom(updatedInfo);
    }





}