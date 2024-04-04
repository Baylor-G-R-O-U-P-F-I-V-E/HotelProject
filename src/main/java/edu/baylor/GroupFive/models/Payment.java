package edu.baylor.GroupFive.models;

import java.util.Date;

public class Payment {
    private Date date;
    private long id;
    private float amount;
    private StayBill bill;

    Payment(
            Date date_,
            long id_,
            float amount_,
            StayBill bill_
            ){
        this.date = date_;
        this.id = id_;
        this.amount = amount_;
        this.bill = bill_;
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

}
