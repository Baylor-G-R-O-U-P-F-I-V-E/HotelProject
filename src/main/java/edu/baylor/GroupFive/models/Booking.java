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

    Booking(
            Date startDate_,
            Date endDate_,
            boolean active_,
            boolean canceled_,
            Account holder_,
            long id_
            ){
        this.startDate = startDate_;
        this.endDate = endDate_;
        this.bill = new StayBill();
        this.active = true;
        this.canceled = false;
        this.holder = holder_;
        this.id = id_;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public StayBill getBill() {
        return bill;
    }

    public void setBill(StayBill bill) {
        this.bill = bill;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean canceled) {
        this.canceled = canceled;
    }

    public Account getHolder() {
        return holder;
    }

    public void setHolder(Account holder) {
        this.holder = holder;
    }

}
