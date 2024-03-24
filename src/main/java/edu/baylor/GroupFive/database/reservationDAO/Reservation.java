package edu.baylor.GroupFive.database.reservationDAO;

import java.util.Date;

public class Reservation{
    Date startDate;
    Date endDate;
    String guestID;
    String roomID;
    String reservationID;
    Double price;



    Reservation(Date startDate, Date endDate, String guestID, String roomID, String reservationID, Double price){
        this.reservationID = reservationID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestID = guestID;
        this.roomID = roomID;
        this.price = price;
    }

}