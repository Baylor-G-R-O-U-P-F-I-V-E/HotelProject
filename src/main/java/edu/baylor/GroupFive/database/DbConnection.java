 /**
  * Author: Icko
  * Date Created: 4/8/2024
  * Date Last Modified 4/9/2024
  * */

package edu.baylor.GroupFive.database;

import edu.baylor.GroupFive.util.CoreUtils;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;
import edu.baylor.GroupFive.util.logging.G5Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

import java.lang.Deprecated;

 /**
  * DbConnection
  *
  * DbConnection establishes a connection to our database. This object has
  * a private constructor, meaning this class is not meant to be instantiated.
  * This class only contains static methods related to database connectivity
  *
  * @author Icko
  * */
public class DbConnection {

    private DbConnection() {}

     /**
      * Establishes a Connection with a database and returns it.
      *
      * [1]: DriverManager.getConnection should not be returning null.
      *      If it returns null, there is something wrong with how we
      *      are trying to establish the connection (i hope)
      * [2]: Normal behavior for DriverManager.getConnection failure
      *      is to throw a SQLException
      *
      * @return Connection to a database
      * @throws BadConnectionException If no connection can be established
      */
    public static Connection getConnection() throws BadConnectionException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(CoreUtils.DB_URL, "", "");
            assert(connection != null);             // [1]
        } catch (SQLException e) {                  // [2]
            G5Logger.logger.error("Could not establish database connection");
            throw new BadConnectionException();
        }
        return connection;
    }

     /**
      * Closes a PreparedStatement.
      *
      * @deprecated Open statements using try blocks with resource management.
      * @param statement PreparedStatement to close
      */
    @Deprecated
    public static void closeStatement(PreparedStatement statement){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                G5Logger.logger.error("Error closing statement");
            }
        }

    }

     /**
      * Closes a Statement.
      *
      * @deprecated Open statements using try blocks with resource management.
      * @param statement Statement to close
      */
    @Deprecated
    public static void closeStatement(Statement statement){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                G5Logger.logger.error("Error closing statement");
            }
        }

    }

     /**
      * Closes a Connection.
      *
      * @deprecated Open connections using try blocks with resource management.
      * @param connection Connection to close
      */
    @Deprecated
    public static void closeConnection(Connection connection){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                G5Logger.logger.error("Error closing connection");
            }
        }
    }
}

/**
 * [1]: https://stackoverflow.com/questions/26024939/drivermanager-getconnection-returns-null
 * [2]: https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html#getConnection-java.lang.String-
 * */
