package edu.baylor.GroupFive.models;

import java.util.Date;
import java.util.List;

public class Purchase {
    private Date date;
    private float amount;
    private List<Item> items;
    private StayBill bill;
    private long id;

    public Purchase(Date date_,
                    float amount_,
                    List<Item> items_,
                    StayBill bill_,
                    long id_) {
        this.date = date_;
        this.amount = amount_;
        this.items = items_;
        this.bill = bill_;
        this.id = id_;
    }

    // >>>> Getters >>>>
    public long getId() { return id; }
    public Date getDate() { return date; }
    public float getAmount() { return amount; }
    public List<Item> getItems() { return items; }
    public StayBill getBill() { return bill; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setId(long id) { this.id = id; }
    public void setDate(Date date) { this.date = date; }
    public void setAmount(float amount) { this.amount = amount; }
    public void setItems(List<Item> items) { this.items = items; }
    public void setBill(StayBill bill_) { bill = bill_; }
    // <<<< Setters <<<<

}
