package edu.baylor.GroupFive.models;

public class Product {
    int prodictID;
    Inventory inventory;
    ProductDescription description;

    Inventory getInventory(){return this.inventory;}
    void setInventory(Inventory val) {this.inventory = val;}

    int getID(){return this.productID;}
    void setInventory(int val) {this.productID = val;}

    Inventory getInventory(){return this.inventory;}
    void setDescription(ProductDescription val) {this.description = val;}

}