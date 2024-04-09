package edu.baylor.GroupFive.database.reservationDAO;
import edu.baylor.GroupFive.database.daos.ReservationDAO;
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

public class TestReservationDAO {

    ReservationDAO conn;

    //this doesnt actually work right for some reason
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
            ReservationDAO conn = new ReservationDAO();
            isAvailable = conn.checkIfAvailable("2",startDate,endDate);
        } catch (ParseException e) {
            System.out.println("exception in checkifavailable test code");
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
            ReservationDAO conn = new ReservationDAO();
            isAvailable = conn.checkIfAvailable("102",startDate,endDate);
            System.out.println(isAvailable);
        } catch (ParseException e) {
            System.out.println("exception in checkifavailable test code");
            throw new RuntimeException(e);
        }
        assert(isAvailable == false);
    }
    @Test
    public void selectExisting(){
        dbSetup db = new dbSetup();
        ReservationDAO conn = new ReservationDAO();
        Reservation myRes;

             myRes = conn.getInfo(101, new Date("07/20/2024"));


        System.out.println(myRes.toString());
        assert(myRes != null);
    }

    @Test
    public void selectNonExisting(){
        dbSetup db = new dbSetup();
        ReservationDAO conn = new ReservationDAO();
        Reservation myRes;

            myRes = conn.getInfo(102, new Date("01/01/2008"));



        assert(myRes == null);
    }


    //@Test
    public void addReservation(){
        dbSetup db = new dbSetup();
        ReservationDAO conn = new ReservationDAO();

        Date start = new Date("1/12/2009");
        Date end = new Date("1/15/2009");


        Reservation newReservation = new Reservation(start,end,"Axel112","102",12.34);
        Boolean res = null;

        res = conn.addReservation(newReservation);
        System.out.println(res + "--");

        assert(res.equals(true));




    }

    @Test
    public void cancelReservation(){
        dbSetup db = new dbSetup();
        ReservationDAO conn = new ReservationDAO();
        Reservation r;

            r = conn.getInfo(103,new Date("07/22/2024"));

        //just showing that the reservation starts in the db
        assert(r != null);



        conn.cancelReservation(3,new Date("07/22/2024"));


        r = null;

            r = conn.getInfo(3,new Date("07/22/2024"));

        //showing that the reservation is no longer in the db
        assert(r == null);


    }
    @Test
    public void getAllReservations(){
        dbSetup db = new dbSetup();
        ReservationDAO conn = new ReservationDAO();

        List<Reservation> r = conn.getReservations();
        for(Reservation a : r){
            System.out.println(a.toString());
        }
        assert (r.size() > 5);
    }

}
