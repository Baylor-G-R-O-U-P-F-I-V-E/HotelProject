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

public class DbConnection {
    private static final Logger logger = LogManager.getLogger(DbConnection.class.getName());
    private static String dbhost = "jdbc:derby:FinalProject;";
    // private static String url;
    // private static String user;
    // private static String password;

    private DbConnection() {}

    public static Connection getConnection() throws BadConnectionException {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbhost, "", "");
            if(connection == null) {
                logger.info("Could not connect");
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
        return connection;
    }
}
