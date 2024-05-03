package edu.baylor.GroupFive.database;

import edu.baylor.GroupFive.util.CoreUtils;
import edu.baylor.GroupFive.util.logging.G5Logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager; import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

/**
 * This class generates a test database for our testing purposes.
 *
 * @author Icko
 */
public class TestDatabase extends DbSetup {
    private static final Logger logger = LogManager.getLogger(TestDatabase.class.getName());

    // ALL QUERIES MOVED TO BOTTOM OF CLASS - brendon

    @Override
    protected void initDbAuth() {
        dbUrl = "jdbc:derby:TestDatabase;create=true";
        dbUser = "";
        dbPass = "";
    }

    public TestDatabase() {
        super();
        initDbAuth();
    }

}
