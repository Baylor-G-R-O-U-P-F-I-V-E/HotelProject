package edu.baylor.GroupFive.models;

import java.util.Date;

public class Booking {

    private Date startDate;
    private Date endDate;
    private StayBill bill;
    private boolean active;
    private boolean canceled;
    private Account holder;
    private long id;

    public Booking(Date startDate_,
                   Date endDate_,
                   boolean active_,
                   boolean canceled_,
                   Account holder_,
                   long id_ ){
        startDate = startDate_;
        endDate = endDate_;
        bill = new StayBill();
        active = true;
        canceled = false;
        holder = holder_;
        id = id_;
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
