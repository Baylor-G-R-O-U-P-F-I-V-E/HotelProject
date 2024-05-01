package edu.baylor.GroupFive.models;

import edu.baylor.GroupFive.util.CoreUtils;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Reservation class represents a reservation.
 *
 * @author Icko
 * */
public class Reservation {

    private int     dbId;

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
     *
     * @deprecated Use {@link #Reservation(int, Date, Date, String, int, Double)},
     *     {@link #Reservation(int, Date, Date, String, int, Double, boolean, boolean)},
     *     or {@link #Reservation(Reservation)}
     * @param id Unique id of this reservation.
     * @param startDate Start date of reservation.
     * @param endDate End date of reservation.
     * @param guestUsername Username of guest this reservation is for.
     * @param roomNumber String room number of room this reservation is in.
     * @param price Price of this reservation.
     * */
    @Deprecated
    public Reservation(int id, Date startDate, Date endDate,
                       String guestUsername, String roomNumber, Double price) {
        this.setDbId(id);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setGuestID(guestUsername);
        this.setRoomID(Integer.parseInt(roomNumber));
        this.setPrice(price);
        this.setActiveStatus(false);
        this.setCheckedInStatus(false);
    }

    /**
     * Constructs a Reservation object with the specified attributes.
     *
     * @param id Unique id of this reservation.
     * @param startDate Start date of reservation.
     * @param endDate End date of reservation.
     * @param guestUsername Username of guest this reservation is for.
     * @param roomNumber Integer room number of room this reservation is in.
     * @param price Price of this reservation.
     * */
    public Reservation(int id, Date startDate, Date endDate,
                       String guestUsername, int roomNumber, Double price) {
        this.setDbId(id);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setGuestID(guestUsername);
        this.setRoomID(roomNumber);
        this.setPrice(price);
        this.setActiveStatus(false);
        this.setCheckedInStatus(false);
    }

    /**
     * Now i need more versions bc i added status's... kms.
     * Constructs a Reservation object with the specified attributes.
     *
     * @param id Unique id of this reservation.
     * @param startDate Start date of reservation.
     * @param endDate End date of reservation.
     * @param guestUsername Username of guest this reservation is for.
     * @param roomNumber Integer room number of room this reservation is in.
     * @param price Price of this reservation.
     * @param active If this reservation has been canceled or not.
     * @param checkedIn If guest has checked in or not.
     * */
    public Reservation(int id, Date startDate, Date endDate,
                       String guestUsername, int roomNumber, Double price,
                       boolean active, boolean checkedIn) {
        this.setDbId(id);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setGuestID(guestUsername);
        this.setRoomID(roomNumber);
        this.setPrice(price);
        this.setActiveStatus(active);
        this.setCheckedInStatus(checkedIn);
    }

    /**
     * Constructs a Reservation object with the specified attributes and no id.
     *
     * @param startDate Start date of reservation.
     * @param endDate End date of reservation.
     * @param guestUsername Username of guest this reservation is for.
     * @param roomNumber Integer room number of room this reservation is in.
     * @param price Price of this reservation.
     * */
    public Reservation(Date startDate, Date endDate,
                       String guestUsername, String roomNumber, Double price, 
                       boolean active, boolean checkedIn) {
        this.setDbId(-1);
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setGuestID(guestUsername);
        this.setRoomID(Integer.parseInt(roomNumber));
        this.setPrice(price);
        this.setActiveStatus(active);
        this.setCheckedInStatus(checkedIn);
    }

    /**
     * Performs a deep copy of another reservation object.
     *
     * @param reservation Reservation to make a copy of.
     * */
    public Reservation(Reservation reservation) {
        this.setDbId(reservation.getDbId());
        this.setStartDate(reservation.getStartDate());
        this.setEndDate(reservation.getEndDate());
        this.setGuestID(reservation.getGuestUsername());
        this.setRoomID(reservation.getRoomNumber());
        this.setPrice(reservation.getPrice());
        this.setActiveStatus(reservation.getActiveStatus());
        this.setCheckedInStatus(reservation.getCheckedInStatus());
    }
    // <<<< Constructors <<<<

    // >>>> Getters >>>>
    public int getDbId() { return dbId; }
    public Date getStartDate(){ return startDate; }
    public Date getEndDate(){ return endDate; }
    public String getGuestUsername(){ return guestUsername; }
    public int getRoomNumber(){ return roomNumber; }
    public Double getPrice(){ return price; }
    public boolean getActiveStatus() { return active; }
    public boolean getCheckedInStatus() { return checkedIn; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setDbId(int id) { dbId = id; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public void setGuestID(String guestUsername) { this.guestUsername = guestUsername; }
    public void setRoomID(int roomNumber) { this.roomNumber = roomNumber; }
    public void setRoomID(String roomNumber) { this.roomNumber = Integer.parseInt(roomNumber); }
    public void setPrice(Double price) { this.price = price; }
    public void setActiveStatus(boolean active) { this.active = active; }
    public void setCheckedInStatus(boolean checkedIn) { this.checkedIn = checkedIn; }
    // <<<< Setters <<<<

    /**
     * Compares this Reservation to another object.
     *
     * @param o Object to compare to.
     * @return {@code true} if {@code o} is equivalent this Reservation. {@code false} otherwise.
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservation that = (Reservation) o;
        return roomNumber == that.roomNumber && active == that.active && checkedIn == that.checkedIn && startDate.equals(that.startDate) && endDate.equals(that.endDate) && guestUsername.equals(that.guestUsername) && price.equals(that.price);
    }

    /**
     * Returns a String representation of this reservation object.
     *
     * @return String representation of this reservation object.
     * */
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(CoreUtils.DATE_FORMAT); // before: "MM/dd/yyyy"
        String out = "Guest Username: " + guestUsername + "\nStart Date: " + dateFormat.format(startDate) + "\nEnd Date: " + dateFormat.format(endDate) + "\nRoom Number: " + roomNumber + "\nPrice: " + price;
        return out;
    }

}
