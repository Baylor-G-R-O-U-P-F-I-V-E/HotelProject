package edu.baylor.GroupFive.database;

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
                    "CONSTRAINT PK_1 PRIMARY KEY(userID))";
            String sqlCreateRoom = "CREATE TABLE Room(" +
                    "roomID INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY(Start with 1, Increment by 1)," +
                    "theme VARCHAR(50)," +
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

            List<String> sqlInserts = List.of("INSERT INTO users(firstName, lastNAME) VALUES('Joe','Smith')" ,
                    "INSERT INTO USERs(firstName, lastNAME) VALUES('Kevin','James')",
                    "INSERT INTO USERs(firstName, lastNAME) VALUES('Axel','Washington')" ,
                    "INSERT INTO USERs(firstName, lastNAME) VALUES('Andrew','Wiles')" ,
                    "INSERT INTO USERs(firstName, lastNAME) VALUES('Larry','AB')" ,
                    "INSERT INTO USERs(firstName, lastNAME) VALUES('Josh','Smith')" ,
                    "INSERT INTO USERs(firstName, lastNAME) VALUES('Tyler','Lee')" ,
                    "INSERT INTO USERs(firstName, lastNAME) VALUES('Antoine','Wu')" ,
                    "INSERT INTO USERs(firstName, lastNAME) VALUES('Everett','Anderson')" ,
                    "INSERT INTO USERs(firstName, lastNAME) VALUES('Joe','Smith')" ,
                    "INSERT INTO ROOM(theme) VALUES ('TEST')" ,
                    "INSERT INTO ROOM(theme) VALUES ('TEST')" ,
                    "INSERT INTO ROOM(theme) VALUES ('TEST')" ,
                    "INSERT INTO ROOM(theme) VALUES ('TEST')" ,
                    "INSERT INTO ROOM(theme) VALUES ('TEST')" ,
                    "INSERT INTO ROOM(theme) VALUES ('TEST')" ,
                    "INSERT INTO ROOM(theme) VALUES ('TEST')" ,
                    "INSERT INTO ROOM(theme) VALUES ('TEST')" ,
                    "INSERT INTO ROOM(theme) VALUES ('TEST')" ,
                    "INSERT INTO ROOM(theme) VALUES ('TEST')" ,
                    "INSERT INTO ROOM(theme) VALUES ('TEST')" ,
                    "INSERT INTO ROOM(theme) VALUES ('TEST')" ,
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
