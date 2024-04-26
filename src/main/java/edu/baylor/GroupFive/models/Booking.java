package edu.baylor.GroupFive.models;

import java.util.Date;

/**
 * The Booking class represents a reservation.
 *
 * @author Afraz
 * */
public class Booking {

    private Date startDate;
    private Date endDate;
    private StayBill bill;
    private boolean active;
    private boolean canceled;
    private Account holder;
    private long id;

    /**
     * Constructs a Booking object with the specified attributes.
     *
     * @param startDate Reservations start date.
     * @param endDate Reservations end date.
     * @param active Whether this reservation is still active.
     * @param canceled Flag is this reservation is canceled or not.
     * @param holder Account tied to this reservation
     * @param id Database id, if exists
     * @author Afraz
     * */
    public Booking(Date startDate,
                   Date endDate,
                   boolean active,
                   boolean canceled,
                   Account holder,
                   long id ){
        this.setStartDate(startDate);
        this.setEndDate(endDate);
        this.setBill(new StayBill());
        this.setActive(active);
        this.setCanceled(canceled);
        this.setHolder(holder);
        this.setId(id);
    }

    // >>>> Getters >>>>
    public long getId() { return id; }
    public Date getStartDate() { return startDate; }
    public Date getEndDate() { return endDate; }
    public StayBill getBill() { return bill; }
    public boolean getActiveStatus() { return active; }
    public boolean getCanceledStatus() { return canceled; }
    public Account getHolder() { return holder; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setId(long id) { this.id = id; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }
    public void setBill(StayBill bill) { this.bill = bill; }
    public void setActive(boolean active) { this.active = active; }
    public void setCanceled(boolean canceled) { this.canceled = canceled; }
    public void setHolder(Account holder) { this.holder = holder; }
    // <<<< Setters <<<<

}
