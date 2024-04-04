package edu.baylor.GroupFive.models;

import java.util.Date;

public class Payment {
    Payment(){
        this.date = null;
        this.id = -1;
        this.amount = 0;
        this.bill = null;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public StayBill getBill() {
        return bill;
    }

    public void setBill(StayBill bill) {
        this.bill = bill;
    }

    private Date date;
    private long id;
    private float amount;
    private StayBill bill;
}
