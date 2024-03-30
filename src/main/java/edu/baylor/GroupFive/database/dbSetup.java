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
            String sqlDropReservation = "DROP TABLE reservation";
            String sqlDropRoom = "DROP TABLE room";
            String sqlDropUser = "DROP TABLE users";


            //startDate endDate price guestID roomID
            String sqlCreateUser = "CREATE TABLE users(" +
                    "userID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1)," +
                    "firstName VARCHAR(30)," +
                    "lastName VARCHAR(30)," +
                    "username VARCHAR(30)," +
                    "password VARCHAR(30)," +
                    "CONSTRAINT PK_1 PRIMARY KEY(userID))";

            String sqlCreateRoom = "CREATE TABLE Room(" +
                    "roomID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1)," +
                    "roomNumber INTEGER, "+
                    "quality VARCHAR(20)," +
                    "theme VARCHAR(50)," +
                    "smoking INTEGER," +
                    "bedType INTEGER,"+
                    "numBeds INTEGER," +
                    "dailyPrice DECIMAL," +
                    "CONSTRAINT PK_2 PRIMARY KEY(roomID))";
            String sqlCreateReservation =
                    "CREATE TABLE reservation(" +
                            "reservationID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1), " +
                            "startDate DATE," +
                            "endDate Date," +
                            "price DECIMAL," +
                            "guestID INTEGER," +
                            "roomID INTEGER," +
                            "CONSTRAINT FK_1 FOREIGN KEY (guestID) REFERENCES users(userID)," +
                            "CONSTRAINT FK_2 FOREIGN KEY (roomID) REFERENCES ROOM(roomID)" +
                            "" +
                            ")";

            List<String> sqlInserts = List.of("INSERT INTO users(firstName, lastNAME, username,password) VALUES('Joe','Smith','Bongo','p1234')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password) VALUES('Kevin','James', 'KevDog', 'password')",
                    "INSERT INTO USERs(firstName, lastNAME, username,password) VALUES('Axel','Washington', 'Axel112', 'password')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password) VALUES('Andrew','Wiles', 'BigA', 'password')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password) VALUES('Larry','AB', 'LarryTheLobster', 'password')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password) VALUES('Josh','Smith', 'Jman', 'password')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password) VALUES('Tyler','Lee', 'T-Lee', 'password')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password) VALUES('Antoine','Wu', 'Ant', 'password')" ,
                    "INSERT INTO USERs(firstName, lastNAME, username,password) VALUES('Everett','Anderson', 'andyEv', 'password')" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (101,'High', 'Jungle',1,3,2,98.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (102,'High', 'Carnival',1,1,2,97.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (103,'Mid', 'Jungle',1,3,2,77.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (104,'Low', 'Jungle',1,4,2,89.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (105,'Mid', 'Jungle',1,5,2,99.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (106,'High', 'rustic',1,1,2,101.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (107,'Mid', 'Base',1,2,2,94.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (108,'Low', 'Base',1,3,2,92.22)" ,
                    "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (109,'Mid', 'Jungle',1,2,2,98.22)" ,
                    "INSERT INTO RESERVATION(startDate, endDate, price, guestID, roomID) VALUES ('12/17/2024','12/19/2015',97.99,1,2)" ,
                    "INSERT INTO RESERVATION(startDate, endDate, price, guestID, roomID) VALUES ('07/12/2024','07/22/2024',97.99,3,3)" ,
                    "INSERT INTO RESERVATION(startDate, endDate, price, guestID, roomID) VALUES ('07/20/2024','07/23/2024',97.99,4,1)" ,
                    "INSERT INTO RESERVATION(startDate, endDate, price, guestID, roomID) VALUES ('07/20/2024','07/23/2024',97.99,3,4)" ,
                    "INSERT INTO RESERVATION(startDate, endDate, price, guestID, roomID) VALUES ('07/11/2024','07/13/2024',97.99,1,5)" ,
                    "INSERT INTO RESERVATION(startDate, endDate, price, guestID, roomID) VALUES ('07/09/2024','07/12/2024',97.99,2,1)" ,
                    "INSERT INTO RESERVATION(startDate, endDate, price, guestID, roomID) VALUES ('07/10/2024','07/17/2024',97.99,3,2)" ,
                    "INSERT INTO RESERVATION(startDate, endDate, price, guestID, roomID) VALUES ('07/22/2024','07/25/2024',97.99,5,3)" ,
                    "INSERT INTO RESERVATION(startDate, endDate, price, guestID, roomID) VALUES ('07/14/2024','07/19/2024',97.99,6,4)");

            //mm/dd/yyyy
            String sqlQ = "SELECT * FROM  RESERVATION";

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
                        System.out.println(rs.getDate("startDate") + " " + rs.getString("reservationID") + " " + rs.getDouble("price"));
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
