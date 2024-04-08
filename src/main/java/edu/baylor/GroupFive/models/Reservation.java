package edu.baylor.GroupFive.models;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

    private int id; // TODO implement reservation id
    private Date startDate;
    private Date endDate;
    private String guestUsername;
    private String roomNumber;
    private Double price;

    public Reservation(
            Date startDate,
            Date endDate,
            String guestUsername,
            String roomNumber,
            Double price
            ){
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestUsername = guestUsername;
        this.roomNumber = roomNumber;
        this.price = price;
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

    public void setStartDate(Date startDate){
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate){
        this.endDate = endDate;
    }

    public void setGuestID(String guestUsername){
        this.guestUsername = guestUsername;
    }

    public void setRoomID(String roomNumber){
        this.roomNumber = roomNumber;
    }

    public void setPrice(Double price){
        this.price = price;
    }

    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String out = "Guest Username: " + guestUsername + "\nStart Date: " + dateFormat.format(startDate) + "\nEnd Date: " + dateFormat.format(endDate) + "\nRoom Number: " + roomNumber + "\nPrice: " + price;
        return out;
    }

}
