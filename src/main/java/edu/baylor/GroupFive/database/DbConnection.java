 /**
  * Author: Icko
  * Date Created: 4/8/2024
  * Date Last Modified 4/9/2024
  * */

package edu.baylor.GroupFive.database;

import edu.baylor.GroupFive.util.exceptions.BadConnectionException;
import edu.baylor.GroupFive.util.logging.G5Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;

 /**
  * DbConnection
  *
  * DbConnection establishes a connection to our database. This object has
  * a private constructor, meaning this class is not meant to be instantiated.
  * This class only contains static methods related to database connectivity
  * */
public class DbConnection {
    private static final Logger logger = LogManager.getLogger(DbConnection.class.getName());
    private static String dbhost = "jdbc:derby:FinalProject;";

    private DbConnection() {}

     /**
      * [1]: DriverManager.getConnection should not be returning null.
      *      If it returns null, there is something wrong with how we
      *      are trying to establish the connection (i hope)
      * [2]: Normal behavior for DriverManager.getConnection failure
      *      is to throw a SQLException
      * */
    public static Connection getConnection() throws BadConnectionException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbhost, "", "");
            assert(connection != null);             // [1]
        } catch (SQLException e) {                  // [2]
            G5Logger.logger.error("Could not establish database connection");
            throw new BadConnectionException();
        }
        return connection;
    }

    public static void closeStatement(PreparedStatement statement){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                G5Logger.logger.error("Error closing statement");
            }
        }

    }

    public static void closeStatement(Statement statement){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                G5Logger.logger.error("Error closing statement");
            }
        }

    }

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
