package edu.baylor.GroupFive.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Purchase {
    public Purchase(){
        this.id = -1;
        this.date = null;
        this.bill = null;

    }

    private StayBill bill;

    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    private Date date;
    private float amount;
    private List<Item> items;
}
