package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.database.DbConnection;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;
import edu.baylor.GroupFive.util.logging.G5Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The UserDAO class provides methods for interacting with User data in a database.
 *
 * This class implements the BaseDAO abstract class.
 * 
 * @see edu.baylor.GroupFive.database.daos.BaseDAO
 * @author Brendon
 */
public class UserDAO extends BaseDAO<User> {

    protected Connection connection;

    public UserDAO() {
        try {
            connection = DbConnection.getConnection();
        } catch (BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
        }
    }

    /**
     * Retrieves all Users in the database.
     *
     * @return A List of users.
     */
    public List<User> getAll() {

        try (Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT * FROM users";
            ResultSet rs = statement.executeQuery(sqlQuery);
            List<User> output = new ArrayList<>();

            while (rs.next()) {
                User out = new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getString("privilege"));
                output.add(out);
            }

            return output;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    /**
     * Retrieves a User from the database matching a given username.
     *
     * @param username username to match
     * @return User object if found. {@code null} otherwise.
     */
    public User getByUsername(String username) {

        try (Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT * FROM users WHERE username = '" + username + "'";
            ResultSet rs = statement.executeQuery(sqlQuery);
            List<User> output = new ArrayList<>();

            while (rs.next()) {
                int id = rs.getInt("id");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String userName = rs.getString("userName");
                String pswdHash = rs.getString("password");
                String privilege = rs.getString("privilege");
                User out = new User(firstName, lastName, userName, pswdHash, privilege);
                out.setId(id);

                // Add a print statement to see if the user is being found
                System.out.println("User got: " + userName + " [pswd]=" + pswdHash);

                // Add the user to the list
                output.add(out);
            }

            if (!output.isEmpty()) {
                return output.get(0);
            } else {
                return null;
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    /**
     * Retrieves a User from the database matching a given id.
     * USE THIS AT SOME POINT PLEASE PRETTY PLEASE WITH A CHERRY ON TOP
     *
     * @param id Id to match.
     * @return User object if found. {@code null} otherwise.
     */
    public User get(int id) {

        try (Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT * FROM users WHERE id = " + id;
            ResultSet rs = statement.executeQuery(sqlQuery);
            List<User> output = new ArrayList<>();

            while (rs.next()) {
                User out = new User(rs.getString("firstname"), rs.getString("lastname"), rs.getString("username"), rs.getString("password"), rs.getString("privilege"));
                output.add(out);
            }

            return output.get(0);

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }

    }

    /**
     * Saves a User in the database. Either inserts or updates behind the scenes.
     *
     * @param user User to save.
     * @return Number of lines affected by query.
     */
    public Integer save(User user){

        try (Statement statement = connection.createStatement()) {

            User exists = getByUsername(user.getUsername());

            if (exists == null){
                return insert(user);
            } else {
                return update(user);
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    /**
     * Inserts a User into the database.
     *
     * @param user User to insert.
     * @return Number of lines affected by query.
     */
    public Integer insert(User user) {

        try (Statement statement = connection.createStatement()) {

            String sqlInsert = "INSERT INTO users(firstName, lastName, username, password, privilege) VALUES ('" +
                    user.getFirstName() + "','" + user.getLastName() + "','" + user.getUsername() + "','" + user.getPasswordHash() + "','" + user.getPrivilege().toString() + "')" ;
            statement.executeUpdate(sqlInsert);

            return 1;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }

    }

    /**
     * Updates an existing User in the database.
     *
     * @param user User object with changes.
     * @return Number of lines affected by query.
     */
    public Integer update(User user) {
        try (Statement statement = connection.createStatement()) {
            
            // Get the original user to update the guestusername in the RESERVATIONs table
            User oldUser = get(user.getId());
    
            // Step 1: Change the user in the users table
            String sqlUpdate = "UPDATE users SET username = '" + user.getUsername() + "', firstName = '" + user.getFirstName() + "', lastName = '" + user.getLastName() + "', password = '" + user.getPasswordHash() + "', privilege = '" + user.getPrivilege().toString() + "' WHERE id = " + user.getId();
            statement.executeUpdate(sqlUpdate);

            // Step 2: Change the guestusername in the RESERVATIONs table
            String updateGuestUsername = "UPDATE RESERVATIONs SET guestusername = '" + user.getUsername() + "' WHERE guestusername = '" + oldUser.getUsername() + "'";
            statement.executeUpdate(updateGuestUsername);

            // Step 3: Change the username in the TRANSACTIONs table
            String updateUsername = "UPDATE TRANSACTIONS SET username = '" + user.getUsername() + "' WHERE username = '" + oldUser.getUsername() + "'";
    
            return 1;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }
    }

    /**
     * Deletes a User from our database.
     *
     * @param user User to delete.
     * @return Number of lines affected by query.
     */
    public Integer delete(User user){

        try (Statement statement = connection.createStatement()) {

            String sqlDelete = "DELETE FROM users WHERE id = '" + user.getId() + "'";
            statement.executeUpdate(sqlDelete);

            return 1;

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            return 0;
        }

    }

}
