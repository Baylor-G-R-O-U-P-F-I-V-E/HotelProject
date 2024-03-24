package edu.baylor.GroupFive.database.reservationDAO;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

public class TestReservationDatabaseConnection {

    ReservationDatabaseConnection conn;


    @BeforeEach
    public void init(){
        conn = new ReservationDatabaseConnection();
    }


    // REMOVE FOR MERGE
    @Test
    @Tag("Database")
    public void testConnection(){
        //conn.connect();
    }
}
