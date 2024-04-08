package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.models.enums.Privilege;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.models.enums.BedType;
import edu.baylor.GroupFive.models.enums.Theme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public UserDAO() {}

    private Connection getConnection(){
        Connection connection = null;
        try {
            connection = DriverManager.getConnection("jdbc:derby:FinalProject;", "", "");
            if(connection == null) {
                System.err.println("Could not connect");
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
        return connection;
    }

    public User getUser(String username){
        Connection connection =  getConnection();
        if(connection == null){
            System.err.println("Connection Failed");
            return null;
        }

        Statement statement = null;
        String sqlQuery = "SELECT * FROM users WHERE username = '" + username + "'";
        ResultSet rs = null;
        List<Reservation> output = new ArrayList<>();
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlQuery);
            while(rs.next()){
                User out = new User(rs.getString("firstname"),rs.getString("lastname"),rs.getString("username"),rs.getString("password"), rs.getString("privilege"));
                return out;
            }


        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
            System.err.println("Connection Failed");
            return null;
        }
        Statement statement = null;
        // "INSERT INTO USERs(firstName, lastNAME) VALUES('Kevin','James', 'KevDog', 'password')",
        // startDate endDate price guestID roomID
        String sqlInsert = "INSERT INTO USERS(firstName, lastName, username, password, privilege) VALUES ('" +
                 user.getFirstName() + "','" + user.getLastName() + "','" + user.getUsername() + "','" + user.getPasswordHash() + "','" + user.getPrivilege().toString() + "')" ;
        try {
            statement = connection.createStatement();
            statement.executeUpdate(sqlInsert);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return false;
        } finally {
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



    public Boolean modifyUser(User newUser){
        Connection connection =  getConnection();
        if(connection == null){
            System.err.println("Connection Failed");
            return null;
        }



        //just checking we already have the room in the db
        User exists = getUser(newUser.getUsername());
        if(exists == null){
            return false;
        }

        Statement statement = null;
        String sqlDelete = "DELETE FROM USERS WHERE username = '" + newUser.getUsername() + "'";
        try {
            statement = connection.createStatement();
            statement.execute(sqlDelete);


        } catch (SQLException e) {
            System.err.println(e.getMessage());
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

        return  addUser(newUser);
    }

}

