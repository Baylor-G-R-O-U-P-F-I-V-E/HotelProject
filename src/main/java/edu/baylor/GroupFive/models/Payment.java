package edu.baylor.GroupFive.models;

import java.util.Date;

/**
 * The Payment class represents a payment action.
 *
 * @author Afraz
 * @author Icko
 * */
public class Payment {

    private Date date;
    private long id;
    private float amount;
    private StayBill bill;

    /**
     * Constructs a Payment object with the specified attributes.
     *
     * @param date Date this transaction occurred.
     * @param id Unique id for this transaction.
     * @param amount Dollar amount of this transaction.
     * @param bill StayBill object associated with this payment.
     * */
    public Payment(Date date,
                   long id,
                   float amount,
                   StayBill bill
                   ){
        this.setDate(date);
        this.setId(id);
        this.setAmount(amount);
        this.setBill(bill);
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
