package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.DbSetup;
import edu.baylor.GroupFive.models.Reservation;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import java.sql.SQLException;

public class ReservationServiceTest {

    ReservationServices conn;

    //this doesnt actually work right for some reason
    @BeforeEach
    void init(){
        DbSetup db = new DbSetup();

    }

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




    @Test
    public void checkAvailable2(){
        DbSetup db = new DbSetup();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        String sdate = "15/12/2024";
        String edate = "20/12/2024";
        Boolean isAvailable;
        try {
            Date startDate = formatter.parse(sdate);
            Date endDate = formatter.parse(edate);
            //System.out.println(startDate.getYear() + " " + endDate.getTime());
            ReservationServices conn = new ReservationServices();
            isAvailable = conn.checkIfAvailable(102,startDate,endDate);
            System.out.println(isAvailable);
        } catch (ParseException | SQLException e) {
            System.out.println("exception in checkifavailable test code");
            throw new RuntimeException(e);
        }
        assert(isAvailable == false);
    }
    @Test
    public void selectExisting() throws SQLException{
        DbSetup db = new DbSetup();
        ReservationServices conn = new ReservationServices();
        Reservation myRes;

             myRes = conn.get(101, new Date("07/20/2024"));


        System.out.println(myRes.toString());
        assert(myRes != null);
    }

    @Test
    public void selectNonExisting() throws SQLException{
        DbSetup db = new DbSetup();
        ReservationServices conn = new ReservationServices();
        Reservation myRes;

            myRes = conn.get(102, new Date("01/01/2008"));



        assert(myRes == null);
    }


    //@Test
    public void addReservation(){
        DbSetup db = new DbSetup();
        ReservationServices conn = new ReservationServices();

        Date start = new Date("1/12/2009");
        Date end = new Date("1/15/2009");


        // TODO reservation now requires an id
        Reservation newReservation = new Reservation(1, start,end,"Axel112",102,12.34);
        Integer res = null;

        try {
            res = conn.insert(newReservation);
        } catch (SQLException e) {
            System.out.println("exception in addReservation test code");
        }
        System.out.println(res + "--");

        assert(res.equals(1));

    }

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
        assert(r == null);

    }
    @Test
    public void getAllReservations(){
        DbSetup db = new DbSetup();
        ReservationServices conn = new ReservationServices();
        List<Reservation> r = null;

        try {
            r = conn.getAll();
        } catch (SQLException e) {
            System.out.println("exception in getAllReservations test code");
            return;
        }
        for(Reservation a : r){
            System.out.println(a.toString());
        }
        assert (r.size() > 5);
    }

}
