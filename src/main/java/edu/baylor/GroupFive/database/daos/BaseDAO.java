package edu.baylor.GroupFive.database.daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseDAO {

    private static final Logger logger = LogManager.getLogger(BaseDAO.class.getName());
    protected static Connection getConnection(){
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:derby:FinalProject;", "", "");
            if(connection == null) {
                logger.info("Could not connect");
                return null;
            }
        } catch (SQLException e) {
            return null;
        }
        return connection;
    }

    protected static void closeStatement(Statement statement){
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                logger.info("Error closing statement");
            }
        }

    }

    protected static void closeConnection(Connection connection){
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.info("Error closing connection");
            }
        }
    }

    protected static String formatDate(Date myDate) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(myDate.getTime());
    }
}
