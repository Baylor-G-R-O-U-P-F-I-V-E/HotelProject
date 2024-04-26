package edu.baylor.GroupFive.models;

/**
 * The Product class represents a physical item in our inventory.
 *
 * @author Afraz
 * */
public class Product {
    private int productID;
    private Inventory inventory; // TODO why does a product have an inventory?? -Icko
    private ProductDescription description;

    /**
     * Constructs a Product with the specified attributes.
     *
     * @param productID Unique id of the product
     * @param inventory For some damn reason
     * @param description Description of this product
     * @author Afraz
     * */
    public Product(
            int productID,
            Inventory inventory,
            ProductDescription description
            ) {
        this.setProductID(productID);
        this.setInventory(inventory);
        this.setDescription(description);
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
