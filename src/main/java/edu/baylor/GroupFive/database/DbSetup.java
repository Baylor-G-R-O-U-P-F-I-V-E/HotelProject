package edu.baylor.GroupFive.database;

import edu.baylor.GroupFive.util.CoreUtils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

import java.text.ParseException;

public class DbSetup {

    private static final Logger logger = LogManager.getLogger(DbSetup.class.getName());

    // ALL QUERIES MOVED TO BOTTOM OF CLASS - brendon

    public DbSetup() {

        logger.info("Running");

        dbTearDown();

        try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {

            try {
                // Try to select from the "USERs" table
                statement.executeQuery("SELECT * FROM USERs");
            } catch (SQLException e) {
                // The "USERs" table does not exist, so create it
                statement.executeUpdate(sqlCreateUserTable);
            }
        
            try {
                // Try to select from the "ROOM" table
                statement.executeQuery("SELECT * FROM ROOM");
            } catch (SQLException e) {
                // The "ROOM" table does not exist, so create it
                statement.executeUpdate(sqlCreateRoomTable);
            }
        
            try {
                // Try to select from the "RESERVATION" table
                statement.executeQuery("SELECT * FROM RESERVATIONs");
            } catch (SQLException e) {
                // The "RESERVATION" table does not exist, so create it
                statement.executeUpdate(sqlCreateReservationTable);
            }

            try {
                // Try to select from the "TRANSACTIONS" table
                statement.executeQuery("SELECT * FROM TRANSACTIONS");
            } catch (SQLException e) {
                // The "TRANSACTIONS" table does not exist, so create it
                statement.executeUpdate(sqlCreateTransactionsTable);
            }

        } catch (SQLException e) {
            logger.info("ERROR");
            logger.info(e.getMessage());
            throw new RuntimeException(e);
        }

        dbInit();

    }

    private static void dbTearDown() {
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

            // Check if FK_12 constraint exists
            rs = dbm.getImportedKeys(null, null, "RESERVATIONs");
            if (rs.next()) {
                // Drop the FK_12 constraint
                statement.executeUpdate("ALTER TABLE RESERVATIONs DROP CONSTRAINT FK_12");
            } else {
                // FK_12 does not exist
                logger.info("FK_12 does not exist");
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

            statement.executeUpdate(sqlDropReservationTable);
            statement.executeUpdate(sqlDropRoomTable);
            statement.executeUpdate(sqlDropUserTable);
            statement.executeUpdate(sqlDropTransactionsTable);
        } catch (SQLException e) {
            logger.warn("SQLException in dbTearDown");
            e.printStackTrace();
        }
    }

    private static void dbInit() {

        try (Connection connection = DriverManager.getConnection(url, user, password); Statement statement = connection.createStatement()) {
            
            logger.info("Initializing database tables");
            PreparedStatement ps;

            // Inserts all records, avoiding duplicates
            ps = connection.prepareStatement(BASE_USER_INSERT_QUERY);
            initializeUsers(ps);
            ps = connection.prepareStatement(BASE_ROOM_INSERT_QUERY);
            initializeRooms(ps);
            ps = connection.prepareStatement(BASE_RESERVATION_INSERT_QUERY);
            initializeReservations(ps);
            ps = connection.prepareStatement(BASE_TRANSACTION_INSERT_QUERY);
            initializeTransactions(ps);
            
//             int count = 0;
//             try{
// 
//                 logger.info("Initializing database tables");
//                 for(String r : sqlInserts){
//                     count++;
//                     statement.executeUpdate(r);
//                 }
// 
//             }catch(SQLException e){
//                 logger.info("INSERTION ERROR " + count);
//                 logger.info(e.getMessage());
//             }

            String sqlQ = "SELECT * FROM RESERVATIONs";
            ResultSet rs = statement.executeQuery(sqlQ);
            logger.info("Current reservations in database...");
            while (rs.next()) {
                logger.info(CoreUtils.getUtilDate(rs.getDate("startDate")) + " " + rs.getString("roomNumber") + " " + rs.getDouble("price"));
            }

            sqlQ = "SELECT * FROM USERS";
            rs = statement.executeQuery(sqlQ);
            logger.info("Current users in database...");
            while (rs.next()) {
                logger.info(rs.getString("username") + " " + rs.getString("password"));
            }

            sqlQ = "SELECT * FROM ROOM";
            rs = statement.executeQuery(sqlQ);
            logger.info("Current rooms in database...");
            while (rs.next()) {
                logger.info(rs.getInt("roomNumber") + " " + rs.getString("theme"));
            }

            sqlQ = "SELECT * FROM TRANSACTIONS";
            rs = statement.executeQuery(sqlQ);
            logger.info("Current transactions in database...");
            while (rs.next()) {
                logger.info(rs.getInt("id") + " " + rs.getString("description") + " " + rs.getDouble("amount"));
            }

        } catch (SQLException e) {
            logger.info("ERROR");
            logger.info(e.getMessage());
            throw new RuntimeException(e);
        }

    }

    private static final String url = "jdbc:derby:FinalProject;create=true";
    private static final String user = "";
    private static final String password = "";
    private static final String sqlDropReservationTable = "DROP TABLE RESERVATIONs";
    private static final String sqlDropRoomTable = "DROP TABLE ROOM";
    private static final String sqlDropUserTable = "DROP TABLE USERs";
    private static final String sqlDropTransactionsTable = "DROP TABLE TRANSACTIONS";
    private static final String sqlCreateUserTable = "CREATE TABLE USERs(" +
            "firstName VARCHAR(30)," +
            "lastName VARCHAR(30)," +
            "username VARCHAR(30) NOT NULL ," +
            "password VARCHAR(256)," +
            "privilege VARCHAR(20)," +
            "CONSTRAINT PK_USER PRIMARY KEY(username))";

    private static final String sqlCreateRoomTable = "CREATE TABLE ROOM(" +
            "roomNumber INTEGER NOT NULL , " +
            "quality INTEGER," +
            "theme VARCHAR(50)," +
            "smoking Boolean," +
            "bedType VARCHAR(10)," +
            "numBeds INTEGER," +
            "dailyPrice DECIMAL(5,2)," +
            "CONSTRAINT PK_ROOM PRIMARY KEY(roomNumber))";

    private static final String sqlCreateReservationTable = "CREATE TABLE RESERVATIONs(" +
            "startDate DATE," +
            "endDate Date," +
            "price DECIMAL(5,2)," +
            "guestusername VARCHAR(30)," +
            "roomNumber INTEGER," +
            "id INTEGER," +
            "active BOOLEAN," +
            "checkedIn BOOLEAN," +
            "CONSTRAINT FK_12 FOREIGN KEY (guestusername) REFERENCES users(username)," +
            "CONSTRAINT FK_23 FOREIGN KEY (roomNumber) REFERENCES ROOM(roomNumber)," +
            "CONSTRAINT PK_RES3 PRIMARY KEY(roomNumber, startDate)" +
            ")";
    
    private static final String sqlCreateTransactionsTable = "CREATE TABLE TRANSACTIONS(" +
            "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1)," +
            "amount DECIMAL(5,2)," +
            "purchaseDate DATE," +
            "description VARCHAR(100)," +
            "username VARCHAR(30)," +
            "CONSTRAINT FK_34 FOREIGN KEY (username) REFERENCES users(username)," +
            "CONSTRAINT PK_TRANS PRIMARY KEY(id)" +
            ")";


    private static final String BASE_USER_INSERT_QUERY = "INSERT INTO USERS(firstName, lastName, userName, password, privilege) VALUES ( ?, ?, ?, ?, ? )";
    private static final String BASE_ROOM_INSERT_QUERY = "INSERT INTO ROOM(roomNumber, quality, theme, smoking, bedType, numBeds, dailyPrice) VALUES ( ?, ?, ?, ?, ?, ?, ? )";
    private static final String BASE_RESERVATION_INSERT_QUERY = "INSERT INTO RESERVATIONS(startDate, endDate, price, guestUsername, roomNumber, id, active, checkedIn) VALUES ( ?, ?, ?, ?, ?, ?, ?, ? )";
    private static final String BASE_TRANSACTION_INSERT_QUERY = "INSERT INTO TRANSACTIONS(amount, purchaseDate, description, username) VALUES ( ?, ?, ?, ? )";

    private static final List<Object[]> userInits = new ArrayList<>();
    private static final List<Object[]> roomInits = new ArrayList<>();
    private static final List<Object[]> reservationInits = new ArrayList<>();
    private static final List<Object[]> transactionInits = new ArrayList<>();

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

        roomInits.add(new Object[] { 101, 1, "VintageCharm",    true,     "KING",     2,    98.22 });
        roomInits.add(new Object[] { 102, 1, "NatureRetreat",   false,    "KING",     2,    97.22 });
        roomInits.add(new Object[] { 103, 1, "UrbanElegance",   true,     "SINGLE",   2,    77.22 });
        roomInits.add(new Object[] { 104, 1, "UrbanElegance",   true,     "SINGLE",   2,    89.22 });
        roomInits.add(new Object[] { 105, 1, "VintageCharm",    false,    "QUEEN",    2,    99.22 });
        roomInits.add(new Object[] { 106, 1, "NatureRetreat",   true,     "SINGLE",   2,    101.22 });
        roomInits.add(new Object[] { 107, 1, "NatureRetreat",   false,    "DOUBLE",   2,    94.22 });
        roomInits.add(new Object[] { 108, 1, "NatureRetreat",   false,    "QUEEN",    2,    92.22 });
        roomInits.add(new Object[] { 109, 1, "VintageCharm",    true,     "KING",     2,    98.22 });

        reservationInits.add(new Object[] { "12/17/2024",   "12/19/2024",   97.99,  "Axel112",            102, 1, true,     false });
        reservationInits.add(new Object[] { "07/12/2024",   "07/22/2024",   95.99,  "LarryTheLobster",    103, 2, true,     false });
        reservationInits.add(new Object[] { "07/20/2024",   "07/23/2024",   96.99,  "BigA",               101, 3, true,     false });
        reservationInits.add(new Object[] { "07/20/2024",   "07/23/2024",   97.99,  "Jman",               104, 4, true,     true });
        reservationInits.add(new Object[] { "07/11/2024",   "07/13/2024",   88.99,  "T-Lee",              105, 5, false,    false });
        reservationInits.add(new Object[] { "07/09/2024",   "07/12/2024",   97.99,  "andyEv",             101, 6, false,    false });
        reservationInits.add(new Object[] { "07/10/2024",   "07/17/2024",   88.99,  "KevDog",             102, 7, true,     true });
        reservationInits.add(new Object[] { "07/22/2024",   "07/25/2024",   97.99,  "Bongo",              103, 8, true,     false });
        reservationInits.add(new Object[] { "07/14/2024",   "07/19/2024",   97.99,  "Ant",                104, 9, true,     true });

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
    }

     /**
      * Initializes Users table in our database
      *
      * @author Icko
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
                    logger.warn("Attempted to insert a duplicate key, skipping this record.");
                } else {
                    throw e;
                }
            }
        }
    }

     /**
      * Initializes Rooms table in our database
      *
      * @author Icko
      * */
    private static void initializeRooms(PreparedStatement statement) throws SQLException {
        for (Object[] room : roomInits) {
            statement.setInt(1, (int) room[0]);
            statement.setInt(2, (int) room[1]);
            statement.setString(3, (String) room[2]);
            statement.setBoolean(4, (boolean) room[3]);
            statement.setString(5, (String) room[4]);
            statement.setInt(6, (int) room[5]);
            statement.setDouble(7, (double) room[6]);
            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    logger.warn("Attempted to insert a duplicate key, skipping this record.");
                } else {
                    throw e;
                }
            }
        }
    }

     /**
      * Initializes Reservations table in our database
      *
      * @author Icko
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
            statement.setInt(6, (int) reservation[5]);
            statement.setBoolean(7, (boolean) reservation[6]);
            statement.setBoolean(8, (boolean) reservation[7]);

            try {
                statement.executeUpdate();
            } catch (SQLException e) {
                if (e instanceof SQLIntegrityConstraintViolationException) {
                    logger.warn("Attempted to insert a duplicate key, skipping this record.");
                } else {
                    throw e;
                }
            }
        }
    }

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
                    logger.warn("Attempted to insert a duplicate key, skipping this record.");
                } else {
                    throw e;
                }
            }
        }
    }

}
/*
    private static final List<String> sqlInserts = List.of(
            "INSERT INTO USERs(firstName, lastNAME, username,password,privilege) VALUES('Joe','Smith','Bongo','p1234', 'admin')",
            "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Kevin','James', 'KevDog', 'password', 'clerk')",
            "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Axel','Washington', 'Axel112', 'password', 'clerk')",
            "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Andrew','Wiles', 'BigA', 'password', 'guest')",
            "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Larry','AB', 'LarryTheLobster', 'password', 'guest')",
            "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Josh','Smith', 'Jman', 'password', 'guest')",
            "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Tyler','Lee', 'T-Lee', 'password', 'guest')",
            "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Antoine','Wu', 'Ant', 'password', 'guest')",
            "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Everett','Anderson', 'andyEv', 'password', 'guest')",
            "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (101,1, 'VintageCharm',true,'KING',2,98.22)",
            "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (102,1, 'NatureRetreat',false,'KING',2,97.22)",
            "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (103,1, 'UrbanElegance',true,'SINGLE',2,77.22)",
            "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (104,1, 'UrbanElegance',true,'SINGLE',2,89.22)",
            "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (105,1, 'VintageCharm',false,'QUEEN',2,99.22)",
            "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (106,1, 'NatureRetreat',true,'SINGLE',2,101.22)",
            "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (107,1, 'NatureRetreat',false,'DOUBLE',2,94.22)",
            "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (108,1, 'NatureRetreat',false,'QUEEN',2,92.22)",
            "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (109,1, 'VintageCharm',true,'KING',2,98.22)",
            "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber, id, active, checkedIn) VALUES ('12/17/2024','12/19/2024',97.99,'Axel112',102, 1, true, false)",
            "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber, id, active, checkedIn) VALUES ('07/12/2024','07/22/2024',95.99,'LarryTheLobster',103, 2, true, false)",
            "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber, id, active, checkedIn) VALUES ('07/20/2024','07/23/2024',96.99,'BigA',101, 3, true, false)",
            "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber, id, active, checkedIn) VALUES ('07/20/2024','07/23/2024',97.99,'Jman',104, 4, true, true)",
            "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber, id, active, checkedIn) VALUES ('07/11/2024','07/13/2024',88.99,'T-Lee',105, 5, false, false)",
            "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber, id, active, checkedIn) VALUES ('07/09/2024','07/12/2024',97.99,'andyEv',101, 6, false, false)",
            "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber, id, active, checkedIn) VALUES ('07/10/2024','07/17/2024',88.99,'KevDog',102, 7, true, true)",
            "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber, id, active, checkedIn) VALUES ('07/22/2024','07/25/2024',97.99,'Bongo',103, 8, true, false)",
            "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber, id, active, checkedIn) VALUES ('07/14/2024','07/19/2024',97.99,'Ant',104, 9, true, true)");
*/
