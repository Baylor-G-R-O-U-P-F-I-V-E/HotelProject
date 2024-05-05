package edu.baylor.GroupFive.models;

import java.util.Date;

/**
 * The Transaction class represents a transaction
 *
 * @author Afraz
 * @author Icko
 * */
public class Transaction {
    private long id;
    private float amount;
    private Date purchaseDate;
    private String description, username;

    /**
     * Constructs a Transaction class with the specified attributes.
     *
     * @param username Username of the User conducting this transaction.
     * @param description Description of the transaction.
     * @param purchaseDate Date this transaction occurred.
     * @param amount Dollar amount of this transaction.
     * */
    public Transaction(String username, String description, Date purchaseDate, float amount) {
        this.setAmount(amount);
        this.setPurchaseDate(purchaseDate);
        this.setDescription(description);
        this.setUsername(username);
    }
    public Transaction(String username, String description, Date purchaseDate, float amount, int id) {
        this.setAmount(amount);
        this.setPurchaseDate(purchaseDate);
        this.setDescription(description);
        this.setUsername(username);
        this.setId(id);
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
