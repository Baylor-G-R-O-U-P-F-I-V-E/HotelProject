package edu.baylor.GroupFive.database.daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseDAO {

    private static final Logger logger = LogManager.getLogger(BaseDAO.class.getName());
    
    protected static Connection getConnection(){
        
        try (Connection connection = DriverManager.getConnection("jdbc:derby:FinalProject;", "", "")) {
            return connection;

        } catch (SQLException e) {
            logger.info("Could not connect");
            return null;
        }
        
    }

    /**
     * 
     * @deprecated Use {@link CoreUtils#formatDate(Date)} instead
     */
    protected static String formatDate(Date myDate) {
        DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        return dateFormat.format(myDate.getTime());
    }
}
