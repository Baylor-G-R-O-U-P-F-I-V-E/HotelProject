package edu.baylor.GroupFive.models;

import java.util.Date;

public class Reservation {
    Date startDate;
    Date endDate;
    String guestID;
    String roomID;
    String reservationID;
    Double price;

    public Reservation(Date startDate, Date endDate, String guestID, String roomID, String reservationID, Double price){
        this.reservationID = reservationID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestID = guestID;
        this.roomID = roomID;
        this.price = price;
    }

    public Date getStartDate(){
        return startDate;
    }

    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    public Date getEndDate(){
        return endDate;
    }

    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    public String getGuestID(){
        return guestID;
    }

    public void setGuestID(String guestID){
        this.guestID = guestID;
    }

    public String getRoomID(){
        return roomID;
    }

    public void setRoomID(String roomID){
        this.roomID = roomID;
    }

    public String getReservationID(){
        return reservationID;
    }

    public void setReservationID(String reservationID){
        this.reservationID = reservationID;
    }

    public Double getPrice(){
        return price;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public String toString(){
        return "Reservation ID: " + reservationID + "\nStart Date: " + startDate + "\nEnd Date: " + endDate + "\nGuest ID: " + guestID + "\nRoom ID: " + roomID + "\nPrice: " + price;
    }

}