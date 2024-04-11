package edu.baylor.GroupFive.models;


public class CartProduct {
    private int quantity;

    public CartProduct(int quantity_) {
        quantity = quantity_;
    }

    // >>>> Getters >>>>
    public int getQuantity() { return quantity; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setQuantity(int quantity) { this.quantity = quantity; }
    // <<<< Setters <<<<
}
