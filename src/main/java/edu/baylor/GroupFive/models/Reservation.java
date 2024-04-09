package edu.baylor.GroupFive.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

    private int id;
    private Date startDate;
    private Date endDate;
    private String guestUsername;
    private String roomNumber;
    private Double price;

    public Reservation(
            int id_,
            Date startDate_,
            Date endDate_,
            String guestUsername_,
            String roomNumber_,
            Double price_
            ){
        id = id_;
        startDate = startDate_;
        endDate = endDate_;
        guestUsername = guestUsername_;
        roomNumber = roomNumber_;
        price = price_;
    }

    public int getId() {
        return id;
    }

    public Date getStartDate(){
        return startDate;
    }

    public Date getEndDate(){
        return endDate;
    }

    public String getGuestUsername(){
        return guestUsername;
    }

    public Integer getRoomNumber(){
        return Integer.valueOf(roomNumber);
    }

    public Double getPrice(){
        return price;
    }

    public void setId(int id_) {
        id = id_;
    }

    public void setStartDate(Date startDate_) {
        startDate = startDate_;
    }

    public void setEndDate(Date endDate_) {
        endDate = endDate_;
    }

    public void setGuestID(String guestUsername_) {
        guestUsername = guestUsername_;
    }

    public void setRoomID(String roomNumber_) {
        roomNumber = roomNumber_;
    }

    public void setPrice(Double price_) {
        price = price_;
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String out = "Guest Username: " + guestUsername + "\nStart Date: " + dateFormat.format(startDate) + "\nEnd Date: " + dateFormat.format(endDate) + "\nRoom Number: " + roomNumber + "\nPrice: " + price;
        return out;
    }

}
