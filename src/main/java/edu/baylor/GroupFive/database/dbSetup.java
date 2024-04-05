package edu.baylor.GroupFive.database;

import edu.baylor.GroupFive.database.reservationDAO.ReservationDatabaseConnection;
import edu.baylor.GroupFive.models.Room;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;

public class dbSetup {
    private static final Logger logger = LogManager.getLogger(dbSetup.class.getName());

    public dbSetup(){
        logger.info("Running");
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:derby:FinalProject;create=true", "", "");
            if(connection == null) {
                logger.info("Could not connect");
                return;
            }
            Statement statement = null;
            String rowID = null;
            // startDate endDate price guestID roomID
            String sqlDropReservation = "DROP TABLE reservations";
            String sqlDropRoom = "DROP TABLE room";
            String sqlDropUser = "DROP TABLE users";


            //startDate endDate price guestID roomID
            String sqlCreateUser = "CREATE TABLE users(" +
                    "firstName VARCHAR(30)," +
                    "lastName VARCHAR(30)," +
                    "username VARCHAR(30) NOT NULL ," +
                    "password VARCHAR(30)," +
                    "privilege VARCHAR(20)," +
                    "CONSTRAINT PK_USER PRIMARY KEY(username))";

            String sqlCreateRoom = "CREATE TABLE Room(" +
                    "roomNumber INTEGER NOT NULL , "+
                    "quality INTEGER," +
                    "theme VARCHAR(50)," +
                    "smoking Boolean," +
                    "bedType VARCHAR(10),"+
                    "numBeds INTEGER," +
                    "dailyPrice DECIMAL(5,2)," +
                    "CONSTRAINT PK_ROOM PRIMARY KEY(roomNumber))";
            String sqlCreateReservation =
                    "CREATE TABLE reservations(" +
                            "startDate DATE," +
                            "endDate Date," +
                            "price DECIMAL(5,2)," +
                            "guestusername VARCHAR(30)," + 
                            "roomNumber INTEGER," +
                            "CONSTRAINT FK_12 FOREIGN KEY (guestusername) REFERENCES users(username)," +
                            "CONSTRAINT FK_23 FOREIGN KEY (roomNumber) REFERENCES ROOM(roomNumber)," +
                            "CONSTRAINT PK_RES3 PRIMARY KEY(roomNumber, startDate)" +
                            ")";

            List<String> sqlInserts = List.of("INSERT INTO users(firstName, lastNAME, username,password,privilege) VALUES('Joe','Smith','Bongo','p1234', 'admin')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Kevin','James', 'KevDog', 'password', 'clerk')",
                    "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Axel','Washington', 'Axel112', 'password', 'clerk')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Andrew','Wiles', 'BigA', 'password', 'guest')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Larry','AB', 'LarryTheLobster', 'password', 'guest')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Josh','Smith', 'Jman', 'password', 'guest')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Tyler','Lee', 'T-Lee', 'password', 'guest')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Antoine','Wu', 'Ant', 'password', 'guest')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password, privilege) VALUES('Everett','Anderson', 'andyEv', 'password', 'guest')" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (101,1  , 'Jungle',true,'KING',2,98.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (102,1  , 'Carnival',false,'KING',2,97.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (103,1 , 'Jungle',true,'TWIN',2,77.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (104,1 , 'Jungle',true,'TWIN',2,89.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (105,1 , 'Jungle',false,'QUEEN',2,99.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (106,1 , 'rustic',true,'TWIN',2,101.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (107,1 , 'Base',false,'TWIN',2,94.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (108,1 , 'Base',false,'QUEEN',2,92.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (109,1 , 'Jungle',true,'KING',2,98.22)" ,
                    "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber) VALUES ('12/17/2024','12/19/2024',97.99,'Axel112',102)" ,
                    "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber) VALUES ('07/12/2024','07/22/2024',95.99,'Axel112',103)" ,
                    "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber) VALUES ('07/20/2024','07/23/2024',96.99,'Axel112',101)" ,
                    "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber) VALUES ('07/20/2024','07/23/2024',97.99,'Axel112',104)" ,
                    "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber) VALUES ('07/11/2024','07/13/2024',88.99,'Axel112',105)" ,
                    "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber) VALUES ('07/09/2024','07/12/2024',97.99,'Axel112',101)" ,
                    "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber) VALUES ('07/10/2024','07/17/2024',88.99,'Axel112',102)" ,
                    "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber) VALUES ('07/22/2024','07/25/2024',97.99,'Axel112',103)" ,
                    "INSERT INTO RESERVATIONs( startDate, endDate, price, guestusername, roomNumber) VALUES ('07/14/2024','07/19/2024',97.99,'Axel112',104)");

            //mm/dd/yyyy
            String sqlQ = "SELECT * FROM  RESERVATIONs";

            try {
                statement = connection.createStatement();
                try{
                    statement.executeUpdate(sqlDropReservation);
                }catch(SQLException e){
                    logger.info("DROP RESERVATION ERROR");
                    logger.info(e.getMessage());
                }
                try{
                    statement.executeUpdate(sqlDropRoom);
                }catch(SQLException e){
                    logger.info("DROP ROOM ERROR");
                    logger.info(e.getMessage());
                }
                try{
                    statement.executeUpdate(sqlDropUser);
                }catch(SQLException e){
                    logger.info("DROP USER ERROR");
                    logger.info(e.getMessage());
                }


                try{
                    statement.executeUpdate(sqlCreateRoom);
                }catch(SQLException e){
                    logger.info("CREATE ROOM ERROR");
                    logger.info(e.getMessage());
                }
                try{
                    statement.executeUpdate(sqlCreateUser);
                }catch(SQLException e){
                    logger.info("CREATE USER ERROR");
                    logger.info(e.getMessage());
                }
                try{
                    statement.executeUpdate(sqlCreateReservation);
                }catch(SQLException e){
                    logger.info("CREATE RESERVATION ERROR");
                    logger.info(e.getMessage());
                }

                int count = 0;
                try{

                    for(String r : sqlInserts){
                        count++;
                        statement.executeUpdate(r);
                    }

                }catch(SQLException e){
                    logger.info("INSERTION ERROR " + count);
                    logger.info(e.getMessage());
                }

                try{

                    ResultSet rs = statement.executeQuery(sqlQ);
                    while(rs.next()){
                        logger.info(rs.getDate("startDate") + " " + rs.getString("roomNumber") + " " + rs.getDouble("price"));
                    }
                }catch(SQLException e){
                    logger.info("SELECT ERROR");
                    logger.info(e.getMessage());
                }


            } catch (SQLException e) {
                logger.info(e.getMessage());
                return;
            }finally {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            }


        } catch (SQLException e) {
            logger.info("ERROR");
            throw new RuntimeException(e);
        }

    }

}
