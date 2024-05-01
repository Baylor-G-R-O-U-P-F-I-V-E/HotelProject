package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.DbSetup;
import edu.baylor.GroupFive.models.Reservation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import java.sql.SQLException;
import org.junit.jupiter.api.Test;

/**
 * Tests methods for {@link edu.baylor.GroupFive.database.services.ReservationServices}
 *
 * @author Cole
 */
public class ReservationServiceTest {

    ReservationServices conn;
    private static List<Reservation> reservationInits = new ArrayList<>();


    @SuppressWarnings("deprecation")
    @BeforeAll
    static void initReservations() {
        reservationInits.add(new Reservation(new Date("12/17/2024"), new Date("12/19/2024"), "Axel112", "102", 97.99, true, false));
        reservationInits.add(new Reservation(new Date("07/12/2024"), new Date("07/22/2024"), "LarryTheLobster", "103", 95.99, true, false));
        reservationInits.add(new Reservation(new Date("07/20/2024"), new Date("07/23/2024"), "BigA", "101", 96.99, true, false));
        reservationInits.add(new Reservation(new Date("07/20/2024"), new Date("07/23/2024"), "Jman", "104", 97.99, true, true));
        reservationInits.add(new Reservation(new Date("07/11/2024"), new Date("07/13/2024"), "T-Lee", "105", 88.99, false, false));
        reservationInits.add(new Reservation(new Date("07/09/2024"), new Date("07/12/2024"), "andyEv", "101", 97.99, false, false));
        reservationInits.add(new Reservation(new Date("07/10/2024"), new Date("07/17/2024"), "KevDog", "102", 88.99, true, true));
        reservationInits.add(new Reservation(new Date("07/22/2024"), new Date("07/25/2024"), "Bongo", "103", 97.99, true, false));
        reservationInits.add(new Reservation(new Date("07/14/2024"), new Date("07/19/2024"), "Ant", "104", 97.99, true, true));
    }

    /**
     * Initializes our database before each test.
     */
    @BeforeEach
    void init(){
        DbSetup db = new DbSetup();
    }

    /**
     * Tests {@link ReservationServices#checkIfAvailable(int, Date, Date)}
     * with a room and date that should be available.
     */
    @Test
    public void checkAvailable1(){
        DbSetup db = new DbSetup();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        String sdate = "01/01/2200";
        String edate = "05/01/2200";
        Boolean isAvailable;
        try {
            Date startDate = formatter.parse(sdate);
            Date endDate = formatter.parse(edate);
            ReservationServices conn = new ReservationServices();
            isAvailable = conn.checkIfAvailable(2,startDate,endDate);
        } catch (ParseException | SQLException e) {
            System.out.println("exception in checkifavailable test code");
            throw new RuntimeException(e);
        }
        assert(isAvailable == true);
    }

    /**
     * Tests {@link ReservationServices#checkIfAvailable(int, Date, Date)}
     * with a room and date that should not be available.
     * @throws SQLException 
     * @throws ParseException 
     */
    @Test
    public void checkAvailable2() throws SQLException, ParseException{
        DbSetup db = new DbSetup();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        String sdate = "15/12/2024";
        String edate = "20/12/2024";
        Boolean isAvailable;

        Date startDate = formatter.parse(sdate);
        Date endDate = formatter.parse(edate);
        
        isAvailable = conn.checkIfAvailable(102,startDate,endDate);
        
        assert(!isAvailable);
    }

    /**
     * Tests {@link ReservationServices#get(int, Date)}, retrieving a
     * Reservation in our database.
     *
     * @throws SQLException If error occurs during database communication.
     */
    @Test
    public void selectExisting() throws SQLException{
        DbSetup db = new DbSetup();
        ReservationServices conn = new ReservationServices();
        Reservation myRes;

             myRes = conn.get(101, new Date("07/20/2024"));


        System.out.println(myRes.toString());
        assert(myRes != null);
    }

    /**
     * Tests {@link ReservationServices#get(int, Date)}, retrieving a
     * Reservation not in our database.
     *
     * @throws SQLException If error occurs during database communication.
     */
    @Test
    public void selectNonExisting() throws SQLException{
        DbSetup db = new DbSetup();
        ReservationServices conn = new ReservationServices();
        Reservation myRes;

        myRes = conn.get(102, new Date("01/01/2008"));

        assert(myRes == null);
    }


    /**
     * Tests {@link ReservationServices#insert(Reservation)}.
     * @throws SQLException 
     */
    @Test
    @Disabled
    public void addReservation() throws SQLException{
        DbSetup db = new DbSetup();
        ReservationServices conn = new ReservationServices();

        Date start = new Date("1/12/2009");
        Date end = new Date("1/15/2009");


        // TODO reservation now requires an id
        Reservation newReservation = new Reservation(1, start,end,"Axel112",102,12.34);

        assert(conn.insert(newReservation) == 1);

    }

    /**
     * Tests {@link ReservationServices#delete(Reservation)}.
     *
     * @throws SQLException If error occurs during database communication.
     */
    @Test
    public void cancelReservation() throws SQLException{
        DbSetup db = new DbSetup();
        ReservationServices conn = new ReservationServices();
        Reservation r;

        r = conn.get(103,new Date("07/22/2024"));

        //just showing that the reservation starts in the db
        assert(r != null);

        conn.delete(r);

        r = null;

        r = conn.get(3,new Date("07/22/2024"));

        //showing that the reservation is no longer in the db
        //assert(r == null);

    }

    /**
     * Tests {@link ReservationServices#getAll()}.
     * @throws SQLException 
     */
    @Test
    public void getAllReservations() throws SQLException{
        DbSetup db = new DbSetup();
        ReservationServices conn = new ReservationServices();
        List<Reservation> r = null;

        r = conn.getAll();

        // Test the reservations against the initialized reservations
        for (int i = 0; i < reservationInits.size(); i++) {
            assert(r.get(i).equals(reservationInits.get(i)));
        }
        
    }

}
