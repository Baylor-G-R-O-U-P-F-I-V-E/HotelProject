package edu.baylor.GroupFive.database.daos;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public abstract class BaseDAO<T> {

    private static final Logger logger = LogManager.getLogger(BaseDAO.class.getName());

    protected static Connection getConnection() {

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

    public abstract T get(int id) throws SQLException;

    public abstract List<T> getAll() throws SQLException;

    /**
     * Either inserts or updates based on our database
     */
    public abstract Integer save(T t) throws SQLException;

    public abstract Integer insert(T t) throws SQLException;

    public abstract Integer update(T t) throws SQLException;

    /**
     * We should not actually be deleting anything from the database except for
     * drastic reasons
     */
    public abstract Integer delete(T t) throws SQLException;
}
