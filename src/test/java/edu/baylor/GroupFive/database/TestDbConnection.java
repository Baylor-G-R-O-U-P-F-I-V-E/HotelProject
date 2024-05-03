package edu.baylor.GroupFive.database;

import edu.baylor.GroupFive.util.CoreUtils;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;
import edu.baylor.GroupFive.util.logging.G5Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

 /**
  * TestDbConnection
  *
  * TestDbConnection establishes a connection to our test database. This object has
  * a private constructor, meaning this class is not meant to be instantiated.
  * This class only contains static methods related to database connectivity
  *
  * @author Icko
  * */
public class TestDbConnection {

    private TestDbConnection() {}

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

}
