package edu.baylor.GroupFive.models;


public class CartProduct {
    private int quantity;

    public CartProduct(int quantity_) {
        quantity = quantity_;
    }

    public int getQuantity() { return quantity; }

    public void setQuantity(int quantity) { this.quantity = quantity; }
}
