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
public class TestDatabase {
    private static final Logger logger = LogManager.getLogger(TestDatabase.class.getName());

    // ALL QUERIES MOVED TO BOTTOM OF CLASS - brendon

    /**
     * Tears down our database, creates all tables, then inserts initial values
     */
    public TestDatabase() {

        logger.info("Running");

        logger.info("Initializing database tables");
        PreparedStatement ps;

        try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {

            try {
                // Try to select from the "USERs" table
                statement.executeQuery("SELECT * FROM USERs");
            } catch (SQLException e) {
                // The "USERs" table does not exist, so create it
                G5Logger.logger.info("Creating User Table");
                statement.executeUpdate(sqlCreateUserTable);
                ps = connection.prepareStatement(BASE_USER_INSERT_QUERY);
                initializeUsers(ps);
            }
        
            try {
                // Try to select from the "ROOM" table
                statement.executeQuery("SELECT * FROM ROOM");
            } catch (SQLException e) {
                // The "ROOM" table does not exist, so create it
                G5Logger.logger.info("Creating Room Table");
                statement.executeUpdate(sqlCreateRoomTable);
                ps = connection.prepareStatement(BASE_ROOM_INSERT_QUERY);
                initializeRooms(ps);
            }
        
            try {
                // Try to select from the "RESERVATION" table
                statement.executeQuery("SELECT * FROM RESERVATIONs");
            } catch (SQLException e) {
                // The "RESERVATION" table does not exist, so create it
                G5Logger.logger.info("Creating Reservation Table");
                statement.executeUpdate(sqlCreateReservationTable);
                ps = connection.prepareStatement(BASE_RESERVATION_INSERT_QUERY);
                initializeReservations(ps);
            }

            try {
                // Try to select from the "TRANSACTIONS" table
                statement.executeQuery("SELECT * FROM TRANSACTIONS");
            } catch (SQLException e) {
                // The "TRANSACTIONS" table does not exist, so create it
                G5Logger.logger.info("Creating Transactions Table");
                statement.executeUpdate(sqlCreateTransactionsTable);
                ps = connection.prepareStatement(BASE_TRANSACTION_INSERT_QUERY);
                initializeTransactions(ps);
            }

            try {
                // Try to select from the "PRODUCTS" table
                statement.executeQuery("SELECT * FROM PRODUCTS");
            } catch (SQLException e) {
                // The "PRODUCTS" table does not exist, so create it
                G5Logger.logger.info("Creating Products Table");
                statement.executeUpdate(sqlCreateProductsTable);
                ps = connection.prepareStatement(BASE_PRODUCT_INSERT_QUERY);
                initializeProducts(ps);
            }


            try {
                // Try to select from the "STOCKS" table
                statement.executeQuery("SELECT * FROM STOCKS");
            } catch (SQLException e) {
                // The "STOCKS" table does not exist, so create it
                G5Logger.logger.info("Creating Stocks Table");
                statement.executeUpdate(sqlCreateStocksTable);
                ps = connection.prepareStatement(BASE_STOCK_INSERT_QUERY);
                initializeStocks(ps);
            }
        } catch (SQLException e) {
            logger.info("ERROR");
            logger.info(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    public static void dbTearDown() {
        try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {
            
            logger.info("Tearing down database tables");

            // Check if FK_34 constraint exists
            DatabaseMetaData dbm = connection.getMetaData();
            ResultSet rs = dbm.getImportedKeys(null, null, "TRANSACTIONS");
            if (rs.next()) {
                // Drop the FK_34 constraint
                statement.executeUpdate("ALTER TABLE TRANSACTIONS DROP CONSTRAINT FK_34");
            } else {
                // FK_34 does not exist
                logger.info("FK_34 does not exist");
            }

            // Check if FK_23 constraint exists
            rs = dbm.getImportedKeys(null, null, "RESERVATIONs");
            if (rs.next()) {
                // Drop the FK_23 constraint
                statement.executeUpdate("ALTER TABLE RESERVATIONs DROP CONSTRAINT FK_23");
            } else {
                // FK_23 does not exist
                logger.info("FK_23 does not exist");
            }

            // Check if PK_USER constraint exists
            rs = dbm.getPrimaryKeys(null, null, "USERs");
            if (rs.next()) {
                // Drop the PK_USER constraint
                statement.executeUpdate("ALTER TABLE USERs DROP CONSTRAINT PK_USER");
            } else {
                // PK_USER does not exist
                logger.info("PK_USER does not exist");
            }

            // Check if FK_12 constraint exists
            rs = dbm.getImportedKeys(null, null, "RESERVATIONs");
            if (rs.next()) {
                // Drop the FK_12 constraint
                statement.executeUpdate("ALTER TABLE RESERVATIONs DROP CONSTRAINT FK_12");
            } else {
                // FK_12 does not exist
                logger.info("FK_12 does not exist");
            }

            statement.executeUpdate(sqlDropReservationTable);
            statement.executeUpdate(sqlDropRoomTable);
            statement.executeUpdate(sqlDropUserTable);
            statement.executeUpdate(sqlDropTransactionsTable);
            statement.executeUpdate(sqlDropStockTable);
            statement.executeUpdate(sqlDropProductTable);
        } catch (SQLException e) {
            logger.warn("SQLException in dbTearDown");
            e.printStackTrace();
        }
    }

    /**
     * Displays the values in the database
     */
    public static void dbInfo() {

        try (Connection connection = DriverManager.getConnection(url, user, password); Statement statement = connection.createStatement()) {

            // Get reservation information

            String sqlQ = "SELECT * FROM RESERVATIONs";
            ResultSet rs = statement.executeQuery(sqlQ);
            logger.info("Current reservations in database...");
            while (rs.next()) {
                logger.info(CoreUtils.getUtilDate(rs.getDate("startDate")) + " " + rs.getString("roomNumber") + " " + rs.getDouble("price"));
            }

            // Get user information

            sqlQ = "SELECT * FROM USERS";
            rs = statement.executeQuery(sqlQ);
            logger.info("Current users in database...");
            while (rs.next()) {
                logger.info(rs.getString("username") + " " + rs.getString("password"));
            }

            // Get room information

            sqlQ = "SELECT * FROM ROOM";
            rs = statement.executeQuery(sqlQ);
            logger.info("Current rooms in database...");
            while (rs.next()) {
                logger.info(rs.getInt("roomNumber") + " " + rs.getString("theme"));
            }

            // Get transaction information

            sqlQ = "SELECT * FROM TRANSACTIONS";
            rs = statement.executeQuery(sqlQ);
            logger.info("Current transactions in database...");
            while (rs.next()) {
                logger.info(rs.getInt("id") + " " + rs.getString("description") + " " + rs.getDouble("amount"));
            }

            // Get product information

            sqlQ = "SELECT * FROM PRODUCTS";
            rs = statement.executeQuery(sqlQ);
            logger.info("Current products in database...");
            while (rs.next()){
                logger.info(rs.getInt("id") + " " + rs.getString("productName") + " " + rs.getDouble("baseCost"));
            }

            // Get stock information

            sqlQ = "SELECT * FROM STOCKS";
            rs = statement.executeQuery(sqlQ);
            logger.info("Current stocks in database...");
            while (rs.next()){
                logger.info(rs.getInt("id") + " " + rs.getString("productId") + " " + rs.getDouble("stock"));
            }

        } catch (SQLException e) {
            logger.info("ERROR");
            logger.info(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private static final String url = "jdbc:derby:FinalProjectTestDb;create=true";
    private static final String user = "";
    private static final String password = "";
    private static final String sqlDropReservationTable = "DROP TABLE RESERVATIONs";
    private static final String sqlDropProductTable = "DROP TABLE PRODUCTS";
    private static final String sqlDropStockTable = "DROP TABLE STOCKS";
    private static final String sqlDropRoomTable = "DROP TABLE ROOM";
    private static final String sqlDropUserTable = "DROP TABLE USERs";
    private static final String sqlDropTransactionsTable = "DROP TABLE TRANSACTIONS";
    private static final String sqlCreateUserTable = "CREATE TABLE USERs(" +
            "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
            "firstName VARCHAR(30)," +
            "lastName VARCHAR(30)," +
            "username VARCHAR(30) NOT NULL ," +
            "password VARCHAR(256)," +
            "privilege VARCHAR(20)," +
            "CONSTRAINT PK_USER PRIMARY KEY(id)," +
            "CONSTRAINT UQ_USER UNIQUE(username))";

    private static final String sqlCreateRoomTable = "CREATE TABLE ROOM(" +
            "roomNumber INTEGER NOT NULL, " +
            "quality VARCHAR(15)," +
            "theme VARCHAR(50)," +
            "smoking Boolean," +
            "bedType VARCHAR(10)," +
            "numBeds INTEGER," +
            "dailyPrice DECIMAL(5,2)," +
            "CONSTRAINT PK_ROOM PRIMARY KEY(roomNumber))";

    private static final String sqlCreateReservationTable = "CREATE TABLE RESERVATIONs(" +
            "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
            "startDate DATE," +
            "endDate Date," +
            "price DECIMAL(5,2)," +
            "guestusername VARCHAR(30) NOT NULL," +
            "roomNumber INTEGER," +
            "active BOOLEAN," +
            "checkedIn BOOLEAN," +
            "CONSTRAINT PK_RES3 PRIMARY KEY(roomNumber, startDate)" +
            ")";
    
    private static final String sqlCreateTransactionsTable = "CREATE TABLE TRANSACTIONS(" +
            "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
            "amount DECIMAL(5,2)," +
            "purchaseDate DATE," +
            "description VARCHAR(100)," +
            "username VARCHAR(30)," +
            "CONSTRAINT PK_TRANS PRIMARY KEY(id)" +
            ")";

    private static final String sqlCreateProductsTable = "CREATE TABLE PRODUCTS(id  INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
    "productName VARCHAR(100), " +
    "baseCost DECIMAL(5,2)," +
    "description VARCHAR(200)," +
    "CONSTRAINT PK_PRODUCTS PRIMARY KEY(id))";

    private static final String sqlCreateStocksTable = "CREATE TABLE STOCKS(id  INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
            "productId INTEGER," +
            "stock INTEGER," +
            "CONSTRAINT PK_STOCKS PRIMARY KEY(id),"+
            "CONSTRAINT FK_STOCKS FOREIGN KEY (productid) REFERENCES products(id))";


    private static final String BASE_USER_INSERT_QUERY = "INSERT INTO USERS(firstName, lastName, userName, password, privilege) VALUES ( ?, ?, ?, ?, ? )";
    private static final String BASE_ROOM_INSERT_QUERY = "INSERT INTO ROOM(roomNumber, quality, theme, smoking, bedType, numBeds, dailyPrice) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
    private static final String BASE_RESERVATION_INSERT_QUERY = "INSERT INTO RESERVATIONS(startDate, endDate, price, guestUsername, roomNumber, active, checkedIn) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
    private static final String BASE_TRANSACTION_INSERT_QUERY = "INSERT INTO TRANSACTIONS(amount, purchaseDate, description, username) VALUES ( ?, ?, ?, ? )";
    private static final String BASE_PRODUCT_INSERT_QUERY = "INSERT INTO PRODUCTS(productName, baseCost, description) VALUES (?,?,?)";
    private static final String BASE_STOCK_INSERT_QUERY = "INSERT INTO STOCKS(productId, stock) VALUES (?,?)";

    private static final List<Object[]> userInits = new ArrayList<>();
    private static final List<Object[]> roomInits = new ArrayList<>();
    private static final List<Object[]> reservationInits = new ArrayList<>();
    private static final List<Object[]> transactionInits = new ArrayList<>();
    private static final List<Object[]> productInits = new ArrayList<>();
    private static final List<Object[]> stockInits = new ArrayList<>();



    /**
     * Initializes our initial values for inserting into database
     */
    static {
        userInits.add(new Object[] { "Joe",     "Smith",        "Bongo",            "p1234",    "admin" });
        userInits.add(new Object[] { "Kevin",   "James",        "KevDog",           "1234",     "clerk" });
        userInits.add(new Object[] { "Axel",    "Washington",   "Axel112",          "1234",     "clerk" });
        userInits.add(new Object[] { "Andrew",  "Wiles",        "BigA",             "1234",     "clerk" });
        userInits.add(new Object[] { "Larry",   "AB",           "LarryTheLobster",  "1234",     "guest" });
        userInits.add(new Object[] { "Josh",    "Smith",        "Jman",             "1234",     "guest" });
        userInits.add(new Object[] { "Tyler",   "Lee",          "T-Lee",            "1234",     "guest" });
        userInits.add(new Object[] { "Antoine", "Wu",           "Ant",              "1234",     "guest" });
        userInits.add(new Object[] { "Everett", "Anderson",     "andyEv",           "1234",     "guest" });
        userInits.add(new Object[] { "Icko",    "Iben",         "ickoxii",          "sicem",    "guest" });

        roomInits.add(new Object[] { 101, "economy", "VintageCharm",    true,     "KING",     2,    98.22 });
        roomInits.add(new Object[] { 102, "comfort", "NatureRetreat",   false,    "KING",     2,    97.22 });
        roomInits.add(new Object[] { 103, "business", "UrbanElegance",   true,     "SINGLE",   2,    77.22 });
        roomInits.add(new Object[] { 104, "executive", "UrbanElegance",   true,     "SINGLE",   2,    89.22 });
        roomInits.add(new Object[] { 105, "comfort", "VintageCharm",    false,    "QUEEN",    2,    99.22 });
        roomInits.add(new Object[] { 106, "economy", "NatureRetreat",   true,     "SINGLE",   2,    101.22 });
        roomInits.add(new Object[] { 107, "economy", "NatureRetreat",   false,    "DOUBLE",   2,    94.22 });
        roomInits.add(new Object[] { 108, "comfort", "NatureRetreat",   false,    "QUEEN",    2,    92.22 });
        roomInits.add(new Object[] { 109, "executive", "VintageCharm",    true,     "KING",     2,    98.22 });

        reservationInits.add(new Object[] { "12/17/2024",   "12/19/2024",   97.99,  "Axel112",            102, true,     false });
        reservationInits.add(new Object[] { "07/12/2024",   "07/22/2024",   95.99,  "LarryTheLobster",    103, true,     false });
        reservationInits.add(new Object[] { "07/20/2024",   "07/23/2024",   96.99,  "BigA",               101, true,     false });
        reservationInits.add(new Object[] { "07/20/2024",   "07/23/2024",   97.99,  "Jman",               104, true,     true });
        reservationInits.add(new Object[] { "07/11/2024",   "07/13/2024",   88.99,  "T-Lee",              105, false,    false });
        reservationInits.add(new Object[] { "07/09/2024",   "07/12/2024",   97.99,  "andyEv",             101, false,    false });
        reservationInits.add(new Object[] { "07/10/2024",   "07/17/2024",   88.99,  "KevDog",             102, true,     true });
        reservationInits.add(new Object[] { "07/22/2024",   "07/25/2024",   97.99,  "Bongo",              103, true,     false });
        reservationInits.add(new Object[] { "07/14/2024",   "07/19/2024",   97.99,  "Ant",                104, true,     true });

        transactionInits.add(new Object[] { 3.79, "07/14/2024", "Yogurt", "Ant" });
        transactionInits.add(new Object[] { 4.05, "07/14/2024", "Cereal", "Ant" });
        transactionInits.add(new Object[] { 42.60, "07/15/2024", "Room service order", "Ant" });
        transactionInits.add(new Object[] { 14.60, "07/17/2024", "Room service order", "Bongo" });
        transactionInits.add(new Object[] { 4.50, "07/13/2024", "Large Soda", "Bongo" });
        transactionInits.add(new Object[] { 3.00, "08/23/2024", "Candy Bar", "Ant" });
        transactionInits.add(new Object[] { 97.99, "10/14/2024", "Room service order", "LarryTheLobster" });
        transactionInits.add(new Object[] { 2.79, "10/16/2024", "Soap", "LarryTheLobster" });
        transactionInits.add(new Object[] { 23.68, "06/14/2024", "Shop order", "Axel112" });
        transactionInits.add(new Object[] { 15.00, "06/11/2024", "Shop order", "Axel112" });

        productInits.add(new Object[] {"Choclate Bar", 1.29, "A generic bar of choclate"});
        productInits.add(new Object[] {"Popcorn", 2.49, "A generic bag of popcorn"});
        productInits.add(new Object[] {"Hershey's", 2.29, "A Hershey's bar of choclate"});
        productInits.add(new Object[] {"Birthday Cake", 31.09, "A Cake for a Birthday or a bad day"});
        productInits.add(new Object[] {"Choclate Ice Cream", 5.29, "A pint of choclate ice cream"});
        productInits.add(new Object[] {"Vanilla Ice Cream", 5.29, "A pint of Vanilla ie cream"});

        stockInits.add(new Object[] {1, 27});
        stockInits.add(new Object[] {2, 297});
        stockInits.add(new Object[] {3, 17});
        stockInits.add(new Object[] {4, 77});
        stockInits.add(new Object[] {5, 124});
        stockInits.add(new Object[] {6, 95});


    }

     /**
      * Initializes Users table in our database.
      *
      * @param statement PreparedStatement containing sql insert query
      * @throws SQLException If error occurs during database communication
      * */
    private static void initializeUsers(PreparedStatement statement) throws SQLException {
        for (Object[] user : userInits) {
            statement.setString(1, (String) user[0]);
            statement.setString(2, (String) user[1]);
            statement.setString(3, (String) user[2]);
            statement.setString(4, CoreUtils.hashPassword((String) user[3]));
            statement.setString(5, (String) user[4]);
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    logger.warn("Attempted to insert a duplicate user key, skipping this record.");
                } else {
                    throw e;
                }
            }
        }
    }

     /**
      * Initializes Rooms table in our database.
      *
      * @param statement PreparedStatement containing sql insert query
      * @throws SQLException If error occurs during database communication
      * */
    private static void initializeRooms(PreparedStatement statement) throws SQLException {
        for (Object[] room : roomInits) {
            statement.setInt(1, (int) room[0]);
            statement.setString(2, (String) room[1]);
            statement.setString(3, (String) room[2]);
            statement.setBoolean(4, (boolean) room[3]);
            statement.setString(5, (String) room[4]);
            statement.setInt(6, (int) room[5]);
            statement.setDouble(7, (double) room[6]);
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    logger.warn("Attempted to insert a duplicate room key, skipping this record.");
                } else {
                    throw e;
                }
            }
        }
    }

     /**
      * Initializes Reservations table in our database
      *
      * @param statement PreparedStatement containing sql insert query
      * @throws SQLException If error occurs during database communication
      * */
    private static void initializeReservations(PreparedStatement statement) throws SQLException {
        for (Object[] reservation : reservationInits) {
            try {
                statement.setDate(1, CoreUtils.getSqlDate((String) reservation[0]));            
                statement.setDate(2, CoreUtils.getSqlDate((String) reservation[1]));
            } catch (ParseException e ) {
                logger.warn("Error parsing dates in initializeReservations");
            }
            statement.setDouble(3, (double) reservation[2]);
            statement.setString(4, (String) reservation[3]);
            statement.setInt(5, (int) reservation[4]);
            statement.setBoolean(6, (boolean) reservation[5]);
            statement.setBoolean(7, (boolean) reservation[6]);

            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    logger.warn("Attempted to insert a duplicate reservation key, skipping this record.");
                } else {
                    throw e;
                }
            }
        }
    }

     /**
      * Initializes Transaction table in our database
      *
      * @param statement PreparedStatement containing sql insert query
      * @throws SQLException If error occurs during database communication
      */
    private static void initializeTransactions(PreparedStatement statement) throws SQLException {
        for (Object[] transaction : transactionInits) {
            statement.setDouble(1, (double) transaction[0]);
            try {
                statement.setDate(2, CoreUtils.getSqlDate((String) transaction[1]));
            } catch (ParseException e) {
                logger.warn("Error parsing dates in initializeTransactions");
            }
            statement.setString(3, (String) transaction[2]);
            statement.setString(4, (String) transaction[3]);
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    logger.warn("Attempted to insert a duplicate transaction key, skipping this record.");
                } else {
                    throw e;
                }
            }
        }
    }


    /**
     * Initializes Product table in our database
     *
     * @param statement PreparedStatement containing sql insert query
     * @throws SQLException If error occurs during database communication
     */
    private static void initializeProducts(PreparedStatement statement) throws SQLException {
        for (Object[] product : productInits) {
            statement.setString(1, (String) product[0]);
            statement.setDouble(2, (Double) product[1]);
            statement.setString(3, (String) product[2]);

            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    logger.warn("Attempted to insert a duplicate key, skipping this record.");
                } else {
                    logger.warn(e.getMessage());
                    throw e;
                }
            }
        }
    }


    /**
     * Initializes Stock table in our database
     *
     * @param statement PreparedStatement containing sql insert query
     * @throws SQLException If error occurs during database communication
     */
    private static void initializeStocks(PreparedStatement statement) throws SQLException {
        for (Object[] item : stockInits) {
            statement.setInt(1, (Integer) item[0]);
            statement.setInt(2, (Integer) item[1]);

            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    logger.warn("Attempted to insert a duplicate stock key, skipping this record.");
                } else {
                    logger.warn(e.getMessage());
                    throw e;
                }
            }
        }
    }

}
