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

    public int getProductID() { return productID; }

    public void setProductID(int productID) { this.productID = productID; }

    public ProductDescription getDescription() { return description; }

    public void setDescription(ProductDescription description) { this.description = description; }

    public Inventory getInventory(){return this.inventory;}
    public void setInventory(Inventory val) {this.inventory = val;}
}
