package edu.baylor.GroupFive.database;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.ResultSet;
import java.util.List;

public class DbSetup {

    private static final Logger logger = LogManager.getLogger(DbSetup.class.getName());

    // ALL QUERIES MOVED TO BOTTOM OF CLASS - brendon

    public DbSetup() {

        logger.info("Running");

        try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {

            String rowID = null;

            // mm/dd/yyyy
            String sqlQ = "SELECT * FROM  RESERVATIONs";

            try {
                // Try to select from the "USERs" table
                statement.executeQuery("SELECT * FROM USERs");
            } catch (SQLException e) {
                // The "USERs" table does not exist, so create it
                statement.executeUpdate(sqlCreateUser);
            }
        
            try {
                // Try to select from the "ROOM" table
                statement.executeQuery("SELECT * FROM ROOM");
            } catch (SQLException e) {
                // The "ROOM" table does not exist, so create it
                statement.executeUpdate(sqlCreateRoom);
            }
        
            try {
                // Try to select from the "RESERVATION" table
                statement.executeQuery("SELECT * FROM RESERVATIONs");
            } catch (SQLException e) {
                // The "RESERVATION" table does not exist, so create it
                statement.executeUpdate(sqlCreateReservation);
            }

            logger.info("Initializing database tables");

            // Inserts all records, avoiding duplicates
            for (String r : sqlInserts) {
                
                try {
                    statement.executeUpdate(r);
                } catch (SQLException e) {

                    if (e instanceof SQLIntegrityConstraintViolationException) {
                        logger.warn("Attempted to insert a duplicate key, skipping this record.");
                        
                    } else {
                        throw e;
                    }

                }

            }

            ResultSet rs = statement.executeQuery(sqlQ);
            while (rs.next()) {
                logger.info(rs.getDate("startDate") + " " + rs.getString("roomNumber") + " " + rs.getDouble("price"));
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
    private static final String sqlDropReservation = "DROP TABLE RESERVATIONs";
    private static final String sqlDropRoom = "DROP TABLE ROOM";
    private static final String sqlDropUser = "DROP TABLE USERs";

    private static final String sqlCreateUser = "CREATE TABLE USERs(" +
            "firstName VARCHAR(30)," +
            "lastName VARCHAR(30)," +
            "username VARCHAR(30) NOT NULL ," +
            "password VARCHAR(30)," +
            "privilege VARCHAR(20)," +
            "CONSTRAINT PK_USER PRIMARY KEY(username))";

    private static final String sqlCreateRoom = "CREATE TABLE ROOM(" +
            "roomNumber INTEGER NOT NULL , " +
            "quality INTEGER," +
            "theme VARCHAR(50)," +
            "smoking Boolean," +
            "bedType VARCHAR(10)," +
            "numBeds INTEGER," +
            "dailyPrice DECIMAL(5,2)," +
            "CONSTRAINT PK_ROOM PRIMARY KEY(roomNumber))";

    private static final String sqlCreateReservation = "CREATE TABLE RESERVATIONs(" +
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

}
