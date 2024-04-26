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
     * 
     * @deprecated Use {@link CoreUtils#formatDate(Date)} instead
     */
    protected static String formatDate(Date myDate) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(myDate.getTime());
    }

    /**
     *
     * @param id
     * @return
     * @throws SQLException
     */
    public abstract T get(int id) throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public abstract List<T> getAll() throws SQLException;

    /**
     * Either inserts or updates based on our database
     *
     * @param t
     * @return
     * @throws SQLException
     */
    public abstract Integer save(T t) throws SQLException;

    /**
     *
     * @param t
     * @return
     * @throws SQLException
     */
    public abstract Integer insert(T t) throws SQLException;

    /**
     *
     * @param t
     * @return
     * @throws SQLException
     */
    public abstract Integer update(T t) throws SQLException;

    /**
     * We should not actually be deleting anything from the database except for
     * drastic reasons
     *
     * @param t
     * @return
     * @throws SQLException
     */
    public abstract Integer delete(T t) throws SQLException;
}
