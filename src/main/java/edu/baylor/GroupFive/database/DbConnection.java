 /**
  * Author: Icko
  * Date Created: 4/8/2024
  * Date Last Modified 4/9/2024
  * */

package edu.baylor.GroupFive.database;

import edu.baylor.GroupFive.util.exceptions.BadConnectionException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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

    public static Connection getConnection() throws BadConnectionException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbhost, "", "");
             /**
              * DriverManager.getConnection should not be returning null [1].
              * If it returns null, there is something wrong with how we
              * are trying to establish the connection (i hope)
              * */
            assert(connection != null);
        } catch (SQLException e) {
             /**
              * Normal behavior for DriverManager.getConnection failure
              * is to throw a SQLException [2]
              * */
            logger.info("Could not establish database connection");
            throw new BadConnectionException();
        }
        return connection;
    }
}

/**
 * [1]: https://stackoverflow.com/questions/26024939/drivermanager-getconnection-returns-null
 * [2]: https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html#getConnection-java.lang.String-
 * */
