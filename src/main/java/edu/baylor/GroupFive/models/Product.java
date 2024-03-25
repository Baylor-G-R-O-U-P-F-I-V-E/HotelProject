package edu.baylor.GroupFive.models;


public class Product {
    private Inventory inventory;
    private ProductDescription description;
    private int productID;

    int getID(){return this.productID;}
    void setInventory(int val) {this.productID = val;}

    Inventory getInventory(){return this.inventory;}
    void setDescription(ProductDescription val) {this.description = val;}

}