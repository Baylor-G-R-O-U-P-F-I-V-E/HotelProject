package edu.baylor.GroupFive.database.services;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import edu.baylor.GroupFive.database.TestDatabase;
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
        TestDatabase db = new TestDatabase();
    }

    /**
     * Tests {@link ReservationServices#checkIfAvailable(int, Date, Date)}
     * with a room and date that should be available.
     */
    @Test
    public void checkAvailable1(){
        TestDatabase db = new TestDatabase();
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
        TestDatabase db = new TestDatabase();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        String sdate = "15/12/2024";
        String edate = "20/12/2024";
        Boolean isAvailable;

        Date startDate = formatter.parse(sdate);
        Date endDate = formatter.parse(edate);
        
        ReservationServices conn = new ReservationServices();
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
        TestDatabase db = new TestDatabase();
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
        TestDatabase db = new TestDatabase();
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
    // @Disabled
    public void addReservation() throws SQLException{
        TestDatabase db = new TestDatabase();
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
        TestDatabase db = new TestDatabase();
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
        TestDatabase db = new TestDatabase();
        ReservationServices conn = new ReservationServices();
        List<Reservation> r = null;

        r = conn.getAll();

        // Test the reservations against the initialized reservations
        for (int i = 0; i < reservationInits.size(); i++) {
            assert(r.get(i).equals(reservationInits.get(i)));
        }
        
    }

    /**
     *  Tests isOverlap with start2 and end2 both before start1.
     *              s1  e1
     *  s2  e2
     * */
    @Test
    public void testIsOverlapWithNoOverlap1() {
        Date s1 = new Date("05/01/2024");
        Date e1 = new Date("05/06/2024");
        Date s2 = new Date("04/24/2024");
        Date e2 = new Date("04/30/2024");
        assertFalse(ReservationServicesExt.isOverlap(s1, e1, s2, e2));
    }

    /**
     *  Tests isOverlap with start2 and end2 both after end2.
     *  s1   e1
     *              s2  e2
     * */
    @Test
    public void testIsOverlapWithNoOverlap2() {
        Date s1 = new Date("05/01/2024");
        Date e1 = new Date("05/06/2024");
        Date s2 = new Date("05/24/2024");
        Date e2 = new Date("05/30/2024");
        assertFalse(ReservationServicesExt.isOverlap(s1, e1, s2, e2));
    }

    /**
     *  Tests isOverlap with start2 before start1 and end2 after start1 but before end1.
     *          s1      e1
     *      s2      e2
     * */
    @Test
    public void testIsOverlapWithOverlap1() {
        Date s1 = new Date("05/01/2024");
        Date e1 = new Date("05/06/2024");
        Date s2 = new Date("04/24/2024");
        Date e2 = new Date("05/03/2024");
        assertTrue(ReservationServicesExt.isOverlap(s1, e1, s2, e2));
    }

    /**
     *  Tests isOverlap with start2 after start1 but before end1 and end2 after end1.
     *          s1      e1
     *              s2      e2
     * */
    @Test
    public void testIsOverlapWithOverlap2() {
        Date s1 = new Date("05/01/2024");
        Date e1 = new Date("05/06/2024");
        Date s2 = new Date("05/03/2024");
        Date e2 = new Date("05/09/2024");
        assertTrue(ReservationServicesExt.isOverlap(s1, e1, s2, e2));
    }

    /**
     *  Tests isOverlap with start2 and end2 between start1 and end1.
     *          s1          e1
     *              s2  e2
     * */
    @Test
    public void testIsOverlapWithOverlap3() {
        Date s1 = new Date("05/01/2024");
        Date e1 = new Date("05/06/2024");
        Date s2 = new Date("05/02/2024");
        Date e2 = new Date("05/05/2024");
        assertTrue(ReservationServicesExt.isOverlap(s1, e1, s2, e2));
    }

    /**
     * Tests isOverlap with start2 before start1 and end2 after end1.
     *              s1  e1
     *          s2          e2
     * */
    @Test
    public void testIsOverlapWithOverlap4() {
        Date s1 = new Date("05/01/2024");
        Date e1 = new Date("05/06/2024");
        Date s2 = new Date("04/24/2024");
        Date e2 = new Date("05/10/2024");
        assertTrue(ReservationServicesExt.isOverlap(s1, e1, s2, e2));
    }

}
