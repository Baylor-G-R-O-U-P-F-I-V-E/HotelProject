package edu.baylor.GroupFive.database.userDAO;

import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDatabaseConnection {
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
    public User getUser(String userID){
        Connection connection =  getConnection();
        if(connection == null){
            System.out.println("Connection Failed");
            return null;
        }

        Statement statement = null;
        String sqlQuery = "SELECT * FROM users WHERE userID = " + userID;
        ResultSet rs = null;
        List<Reservation> output = new ArrayList<>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlQuery);
            while(rs.next()){
                User out = new User("","","","");
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

    public Boolean addUser(User user){
        Connection connection = getConnection();
        if(connection == null){
            System.out.println("Connection Failed");
            return null;
        }
        Statement statement = null;
        // "INSERT INTO USERs(firstName, lastNAME) VALUES('Kevin','James', 'KevDog', 'password')",
        // startDate endDate price guestID roomID
        String sqlInsert = "INSERT INTO USERS(firstName, lastName, username, password) VALUES ('" +
                 user.firstName + "','" + user.lastName + "','" + user.userName + "','" + user.passwordHash + "')" ;
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



}

