package edu.baylor.GroupFive.models;

import java.util.Date;

public class Payment {

    private Date date;
    private long id;
    private float amount;
    private StayBill bill;

    public Payment(Date date_,
                   long id_,
                   float amount_,
                   StayBill bill_
                   ){
        this.date = date_;
        this.id = id_;
        this.amount = amount_;
        this.bill = bill_;
    }

    // >>>> Getters >>>>
    public Date getDate() { return date; }
    public long getId() { return id; }
    public float getAmount() { return amount; }
    public StayBill getBill() { return bill; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setDate(Date date) { this.date = date; }
    public void setId(long id) { this.id = id; }
    public void setAmount(float amount) { this.amount = amount; }
    public void setBill(StayBill bill) { this.bill = bill; }
    // <<<< Setters <<<<

}
