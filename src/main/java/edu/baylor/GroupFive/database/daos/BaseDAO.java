package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.util.exceptions.BadConnectionException;
import edu.baylor.GroupFive.util.logging.G5Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * This class is an abstract class that represents the base functionalities
 * for all *DAO objects in our project. We allow the user to retrieve
 * a connection to our database along with the following functionality:
 *
 * {@link BaseDAO#get(int)} 
 * {@link BaseDAO#getAll()} 
 * {@link BaseDAO#save(Object)} 
 * {@link BaseDAO#insert(Object)} 
 * {@link BaseDAO#update(Object)} 
 * {@link BaseDAO#delete(Object)} 
 * 
 * @author Brendon
 * @author Cole
 * */
public abstract class BaseDAO<T> {

    private static String dbhost = "jdbc:derby:FinalProject;";

    /**
     * Retrieves connection to our database and returns it
     *
     * @return connection to our database
     * @throws BadConnectionException
     * @author Brendon
     * @author Icko
     */
    protected Connection getConnection() throws BadConnectionException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbhost, "", "");
            assert(connection != null);             
        } catch (SQLException e) {
            G5Logger.logger.info("Could not establish database connection");
            throw new BadConnectionException();
        }
        return connection;
    }

    /**
     * Formats a Date object into a String representation
     * 
     * @param myDate Date object.
     * @return String representation of {@code myDate}.
     * @deprecated Use {@link edu.baylor.GroupFive.util.CoreUtils#formatDate(Date)} instead.
     * @author Cole
     */
    protected static String formatDate(Date myDate) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(myDate.getTime());
    }

    /**
     * Retrieve object in database matching a specific id.
     *
     * @param id Id of object to retrieve
     * @return Object if exists. {@code null} otherwise.
     * @throws SQLException If error occurs during database communication.
     * @author Brendon
     */
    public abstract T get(int id) throws SQLException;

    /**
     * Retrieve all objects in our database.
     *
     * @return A List of objects.
     * @throws SQLException If error occurs during database communication.
     * @author Brendon
     */
    public abstract List<T> getAll() throws SQLException;

    /**
     * Either inserts or updates based on our database.
     *
     * @param t Object to save.
     * @return Number of rows affected by save.
     * @throws SQLException If error occurs during database communication.
     * @author Brendon
     */
    public abstract Integer save(T t) throws SQLException;

    /**
     * Inserts an object into our database.
     *
     * @param t Object to insert.
     * @return Number of rows affected by save.
     * @throws SQLException If error occurs during database communication.
     * @author Brendon
     */
    public abstract Integer insert(T t) throws SQLException;

    /**
     * Updates (modifies) an existing object in our database.
     *
     * @param t Object with new changes.
     * @return Number of rows affected by update.
     * @throws SQLException If error occurs during database communication.
     * @author Brendon
     */
    public abstract Integer update(T t) throws SQLException;

    /**
     * "Deletes" an object from our database.
     * We should not actually be deleting anything from the database except for
     * drastic reasons. As such, typical functionality will set an {@code active}
     * flag from {@code true} to {@code false}.
     *
     * @param t Object to delete.
     * @return Number of rows afffected by deletion.
     * @throws SQLException If error occurs during database communication.
     * @author Brendon
     */
    public abstract Integer delete(T t) throws SQLException;
}
