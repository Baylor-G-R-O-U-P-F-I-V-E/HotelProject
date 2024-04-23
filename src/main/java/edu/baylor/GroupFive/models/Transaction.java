package edu.baylor.GroupFive.models;

import java.util.Date;

public class Transaction {
    private long id;
    private float amount;
    private Date purchaseDate;
    private String description, username;

    public Transaction(String username, String description, Date purchaseDate, float amount) {
        this.amount = amount;
        this.purchaseDate = purchaseDate;
        this.description = description;
        this.username = username;
    }

    // Getters
    public long getId() {
        return id;
    }

    public float getAmount() {
        return amount;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public String getDescription() {
        return description;
    }

    public String getUsername() {
        return username;
    }

    // Setters

    public void setId(long id) {
        this.id = id;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
