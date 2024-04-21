package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.database.DbConnection;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends BaseDAO<User> {

    public UserDAO() {}

    public List<User> getAll() {

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT * FROM users";
            ResultSet rs = statement.executeQuery(sqlQuery);
            List<User> output = new ArrayList<>();

            while (rs.next()) {
                User out = new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getString("privilege"));
                output.add(out);
            }

            return output;

        } catch (SQLException | BadConnectionException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    public User getByUsername(String username) {

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = statement.executeQuery(sqlQuery);
            List<User> output = new ArrayList<>();

            while (rs.next()) {
                //int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String userName = rs.getString("userName");
                String pswdHash = rs.getString("password");
                String privilege = rs.getString("privilege");
                User out = new User(firstName, lastName, userName, pswdHash, privilege);
                // User out = new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getString("privilege"));
                // FIXME dbg
                System.out.println("User got: " + userName + " [pswd]=" + pswdHash);
                output.add(out);
            }

            return output.get(0);

        } catch (SQLException | BadConnectionException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    /*
     * 
     * 
     * USE THIS AT SOME POINT PLEASE PRETTY PLEASE WITH A CHERRY ON TOP
     * 
     */

    public User get(int id) {

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT * FROM users WHERE id = " + id;
            ResultSet rs = statement.executeQuery(sqlQuery);
            List<User> output = new ArrayList<>();

            while (rs.next()) {
                User out = new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getString("privilege"));
                output.add(out);
            }

            return output.get(0);

        } catch (SQLException| BadConnectionException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    public Integer save(User user){

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            User exists = getByUsername(user.getUsername());

            if (exists == null){
                return insert(user);
            } else {
                return update(user);
            }

        } catch (SQLException | BadConnectionException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    public Integer insert(User user){

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            String sqlInsert = "INSERT INTO users(firstName, lastName, username, password, privilege) VALUES ('" +
                    user.getFirstName() + "','" + user.getLastName() + "','" + user.getUsername() + "','" + user.getPasswordHash() + "','" + user.getPrivilege().toString() + "')" ;
            statement.executeUpdate(sqlInsert);

            return 1;

        } catch (SQLException | BadConnectionException e) {
            System.err.println(e.getMessage());
            return 0;
        }

    }

    public Integer update(User user){

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            String sqlUpdate = "UPDATE users SET firstName = '" + user.getFirstName() + "', lastName = '" + user.getLastName() + "', password = '" + user.getPasswordHash() + "', privilege = '" + user.getPrivilege().toString() + "' WHERE username = '" + user.getUsername() + "'";
            statement.executeUpdate(sqlUpdate);

            return 1;

        } catch (SQLException | BadConnectionException e) {
            System.err.println(e.getMessage());
            return 0;
        }

    }

    public Integer delete(User user){

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {

            String sqlDelete = "DELETE FROM users WHERE username = '" + user.getUsername() + "'";
            statement.executeUpdate(sqlDelete);

            return 1;

        } catch (SQLException | BadConnectionException e) {
            System.err.println(e.getMessage());
            return 0;
        }

    }

}

