package edu.baylor.GroupFive.models;


public class CartProduct {
    private int quantity;

    public CartProduct() {
        quantity = -1;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}