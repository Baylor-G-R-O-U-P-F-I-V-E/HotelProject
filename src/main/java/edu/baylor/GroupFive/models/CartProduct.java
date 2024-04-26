package edu.baylor.GroupFive.models;


/**
 * The CartProduct class represents an item in a shopping cart
 *
 * @author Afraz
 * */
public class CartProduct {
    private int quantity;

    /**
     * Constructs a CartProduct object with the specified attributes.
     *
     * @param quantity Amount of this item in the cart.
     * @author Afraz
     * */
    public CartProduct(int quantity) {
        this.setQuantity(quantity);
    }

    // >>>> Getters >>>>
    public int getQuantity() { return quantity; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setQuantity(int quantity) { this.quantity = quantity; }
    // <<<< Setters <<<<
}
