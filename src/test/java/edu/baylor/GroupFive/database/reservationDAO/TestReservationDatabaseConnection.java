package edu.baylor.GroupFive.database.reservationDAO;
import edu.baylor.GroupFive.database.dbSetup;
import edu.baylor.GroupFive.models.Reservation;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class TestReservationDatabaseConnection {

    ReservationDatabaseConnection conn;




    @BeforeEach
    void init(){
        dbSetup db = new dbSetup();

    }


    @Test
    public void checkAvailable1(){
        dbSetup db = new dbSetup();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        String sdate = "01/01/2200";
        String edate = "05/01/2200";
        Boolean isAvailable;
        try {
            Date startDate = formatter.parse(sdate);
            Date endDate = formatter.parse(edate);
            ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
            isAvailable = conn.checkIfAvailable("2",startDate,endDate);
        } catch (ParseException e) {
            System.out.println("exception in checkifavailable test code");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("ERROR in check availability");
            throw new RuntimeException(e);
        }
        assert(isAvailable == true);
    }




    @Test
    public void checkAvailable2(){
        dbSetup db = new dbSetup();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        String sdate = "15/12/2024";
        String edate = "20/12/2024";
        Boolean isAvailable;
        try {
            Date startDate = formatter.parse(sdate);
            Date endDate = formatter.parse(edate);
            //System.out.println(startDate.getYear() + " " + endDate.getTime());
            ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
            isAvailable = conn.checkIfAvailable("2",startDate,endDate);
            System.out.println(isAvailable);
        } catch (ParseException e) {
            System.out.println("exception in checkifavailable test code");
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("ERROR in check availability");
            throw new RuntimeException(e);
        }
        assert(isAvailable == false);
    }
    @Test
    public void selectExisting(){
        dbSetup db = new dbSetup();
        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
        Reservation myRes;
        try {
             myRes = conn.getInfo(1, new Date("07/20/2024"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(myRes.toString());
        assert(myRes != null);
    }

    @Test
    public void selectNonExisting(){
        dbSetup db = new dbSetup();
        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
        Reservation myRes;
        try {
            myRes = conn.getInfo(2, new Date("01/01/2008"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        assert(myRes == null);
    }


    @Test
    public void addReservation(){
        dbSetup db = new dbSetup();
        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();

        Date start = new Date("1/12/2009");
        Date end = new Date("1/15/2009");


        Reservation newReservation = new Reservation(start,end,"3","2",12.34);
        Integer res = null;
        try {
            res = conn.addReservation(newReservation);
            System.out.println(res + "--");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        assert(res != null);




    }

    @Test
    public void cancelReservation(){
        dbSetup db = new dbSetup();
        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();
        Reservation r;
        try {
            r = conn.getInfo(3,new Date("07/22/2024"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //just showing that the reservation starts in the db
        assert(r != null);


        try {
            conn.cancelReservation(3,new Date("07/22/2024"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        r = null;
        try {
            r = conn.getInfo(3,new Date("07/22/2024"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        //showing that the reservation is no longer in the db
        assert(r == null);


    }
    @Test
    public void getAllReservations(){
        dbSetup db = new dbSetup();
        ReservationDatabaseConnection conn = new ReservationDatabaseConnection();

        List<Reservation> r = conn.getReservations();
        for(Reservation a : r){
            System.out.println(a.toString());
        }
        assert (r.size() > 5);
    }

}
