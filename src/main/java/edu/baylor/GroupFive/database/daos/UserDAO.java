package edu.baylor.GroupFive.database.daos;

import com.fasterxml.jackson.databind.ser.Serializers;
import edu.baylor.GroupFive.models.enums.Privilege;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.models.enums.BedType;
import edu.baylor.GroupFive.models.enums.Theme;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO {

    public UserDAO() {}

    public static User getUser(String username){
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
            closeConnection(connection);
            closeStatement(statement);
        }

        return null;

    }

    public static Boolean addUser(User user){
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
            closeConnection(connection);
            closeStatement(statement);
        }


        return true;
    }



    public static Boolean modifyUser(User newUser){
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
            closeConnection(connection);
            closeStatement(statement);
        }

        return  addUser(newUser);
    }

}

