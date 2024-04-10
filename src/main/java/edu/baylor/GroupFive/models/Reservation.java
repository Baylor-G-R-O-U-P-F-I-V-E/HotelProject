 /**
  * Author: Icko
  * */
package edu.baylor.GroupFive.models;

import edu.baylor.GroupFive.util.CoreUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reservation {

    private int     id;
    private Date    startDate;
    private Date    endDate;
    private String  guestUsername;
    private int     roomNumber;
    private Double  price;

    private boolean active; // false if reservation cancelled
    private boolean checkedIn;

    // >>>> Constructors >>>>
     /**
      * Because for some reason we have a previous version that takes in a
      * String roomNumber and I don't want to go through the entire code-base
      * and change everywhere that this is a problem
      * */
     /**
      * @deprecated Use {@link #Reservation(int, Date, Date, String, int, Double)},
      *     {@link #Reservation(int, Date, Date, String, int, Double, boolean, boolean)},
      *     or {@link #Reservation(Reservation)}
      * */
    @Deprecated
    public Reservation(int id_, Date startDate_, Date endDate_,
                       String guestUsername_, String roomNumber_, Double price_) {
        id = id_;
        startDate = startDate_;
        endDate = endDate_;
        guestUsername = guestUsername_;
        roomNumber = Integer.parseInt(roomNumber_);
        price = price_;
        active = false;
        checkedIn = false;
    }

    public Reservation(int id_, Date startDate_, Date endDate_,
                       String guestUsername_, int roomNumber_, Double price_) {
        id = id_;
        startDate = startDate_;
        endDate = endDate_;
        guestUsername = guestUsername_;
        roomNumber = roomNumber_;
        price = price_;
        active = false;
        checkedIn = false;
    }

    // Now i need more versions bc i added status's... kms
    public Reservation(int id_, Date startDate_, Date endDate_,
                       String guestUsername_, int roomNumber_, Double price_,
                       boolean active_, boolean checkedIn_) {
        id = id_;
        startDate = startDate_;
        endDate = endDate_;
        guestUsername = guestUsername_;
        roomNumber = roomNumber_;
        price = price_;
        active = active_;
        checkedIn = checkedIn_;
    }

    public Reservation(Reservation reservation) {
        id = reservation.getId();
        startDate = reservation.getStartDate();
        endDate = reservation.getEndDate();
        guestUsername = reservation.getGuestUsername();
        roomNumber = reservation.getRoomNumber();
        price = reservation.getPrice();
        active = reservation.getActiveStatus();
        checkedIn = reservation.getCheckedInStatus();
    }
    // <<<< Constructors <<<<

    // >>>> Getters >>>>
    public int getId() { return id; }
    public Date getStartDate(){ return startDate; }
    public Date getEndDate(){ return endDate; }
    public String getGuestUsername(){ return guestUsername; }
    public int getRoomNumber(){ return roomNumber; }
    public Double getPrice(){ return price; }
    public boolean getActiveStatus() { return active; }
    public boolean getCheckedInStatus() { return checkedIn; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setId(int id_) { id = id_; }
    public void setStartDate(Date startDate_) { startDate = startDate_; }
    public void setEndDate(Date endDate_) { endDate = endDate_; }
    public void setGuestID(String guestUsername_) { guestUsername = guestUsername_; }
    public void setRoomID(int roomNumber_) { roomNumber = roomNumber_; }
    public void setRoomID(String roomNumber_) { roomNumber = Integer.parseInt(roomNumber_); }
    public void setPrice(Double price_) { price = price_; }
    public void setActiveStatus(boolean active_) { active = active_; }
    public void setCheckedInStatus(boolean checkedIn_) { checkedIn = checkedIn_; }
    // <<<< Setters <<<<

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CoreUtils.DATE_FORMAT); // before: "MM/dd/yyyy"
        String out = "Guest Username: " + guestUsername + "\nStart Date: " + dateFormat.format(startDate) + "\nEnd Date: " + dateFormat.format(endDate) + "\nRoom Number: " + roomNumber + "\nPrice: " + price;
        return out;
    }

}
