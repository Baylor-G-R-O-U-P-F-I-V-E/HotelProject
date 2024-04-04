package edu.baylor.GroupFive.database;

import edu.baylor.GroupFive.models.Room;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.List;

public class dbSetup {
    public dbSetup(){
        System.out.println("Running");
        Connection connection;
        try {
            connection = DriverManager.getConnection("jdbc:derby:FinalProject;create=true", "", "");
            if(connection == null) {
                System.out.println("Could not connect");
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
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (101,1, 'VintageCharm',true,'KING',2,98.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (102,1, 'NatureRetreat',false,'KING',2,97.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (103,1, 'UrbanElegance',true,'SINGLE',2,77.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (104,1, 'UrbanElegance',true,'SINGLE',2,89.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (105,1, 'VintageCharm',false,'QUEEN',2,99.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (106,1, 'NatureRetreat',true,'SINGLE',2,101.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (107,1, 'NatureRetreat',false,'DOUBLE',2,94.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (108,1, 'NatureRetreat',false,'QUEEN',2,92.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (109,1, 'VintageCharm',true,'KING',2,98.22)" ,
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
                    System.out.println("DROP RESERVATION ERROR");
                    System.out.println(e.getMessage());
                }
                try{
                    statement.executeUpdate(sqlDropRoom);
                }catch(SQLException e){
                    System.out.println("DROP ROOM ERROR");
                    System.out.println(e.getMessage());
                }
                try{
                    statement.executeUpdate(sqlDropUser);
                }catch(SQLException e){
                    System.out.println("DROP USER ERROR");
                    System.out.println(e.getMessage());
                }


                try{
                    statement.executeUpdate(sqlCreateRoom);
                }catch(SQLException e){
                    System.out.println("CREATE ROOM ERROR");
                    System.out.println(e.getMessage());
                }
                try{
                    statement.executeUpdate(sqlCreateUser);
                }catch(SQLException e){
                    System.out.println("CREATE USER ERROR");
                    System.out.println(e.getMessage());
                }
                try{
                    statement.executeUpdate(sqlCreateReservation);
                }catch(SQLException e){
                    System.out.println("CREATE RESERVATION ERROR");
                    System.out.println(e.getMessage());
                }

                int count = 0;
                try{

                    for(String r : sqlInserts){
                        count++;
                        statement.executeUpdate(r);
                    }

                }catch(SQLException e){
                    System.out.println("INSERTION ERROR " + count);
                    System.out.println(e.getMessage());
                }

                try{

                    ResultSet rs = statement.executeQuery(sqlQ);
                    while(rs.next()){
                        System.out.println(rs.getDate("startDate") + " " + rs.getString("roomNumber") + " " + rs.getDouble("price"));
                    }
                }catch(SQLException e){
                    System.out.println("SELECT ERROR");
                    System.out.println(e.getMessage());
                }


            } catch (SQLException e) {
                System.out.println(e.getMessage());
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
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }

    }

}
