package edu.baylor.GroupFive.models;

import java.util.Date;
import java.util.List;

/**
 * The Purchase object represents a transaction in our shop
 *
 * @author Afraz
 * @author Icko
 * */
public class Purchase {
    private Date date;
    private float amount;
    private List<Item> items;
    private StayBill bill;
    private long id; // TODO: Should these be auto-generated inside this class if they should be unique? -Icko

    /**
     * Constructs a Purchase objects with the specified attributes
     *
     * @param date Date the purchase occurred.
     * @param amount Dollar amount of purchase.
     * @param items A List of items purchased.
     * @param bill A StayBill object.
     * @param id A unique Purchase id.
     * */
    public Purchase(Date date,
                    float amount,
                    List<Item> items,
                    StayBill bill,
                    long id) {
        this.setDate(date);
        this.setAmount(amount);
        this.setItems(items);
        this.setBill(bill);
        this.setId(id);
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
    public void setBill(StayBill bill) { this.bill = bill; }
    // <<<< Setters <<<<

}
