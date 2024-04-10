package edu.baylor.GroupFive.models;

public class Product {
    private int productID;
    private Inventory inventory;
    private ProductDescription description;

    public Product(
            int productID_,
            Inventory inventory_,
            ProductDescription description_
            ) {
        productID = productID_;
        inventory = inventory_;
        description = description_;
    }

    // >>>> Getters >>>>
    public int getProductID() { return productID; }
    public ProductDescription getDescription() { return description; }
    public Inventory getInventory(){return this.inventory;}
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setProductID(int productID) { this.productID = productID; }
    public void setDescription(ProductDescription description) { this.description = description; }
    public void setInventory(Inventory val) {this.inventory = val;}
    // <<<< Setters <<<<

}
