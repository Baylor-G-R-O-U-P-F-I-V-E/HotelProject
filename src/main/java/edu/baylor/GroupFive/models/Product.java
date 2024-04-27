package edu.baylor.GroupFive.models;

/**
 * The Product class represents a physical item in our inventory.
 *
 * @author Afraz
 * @author Icko
 * */
public class Product {
    private int productID;

    private Double baseCost;
    private String description;



    /**
     * Constructs a Product with the specified attributes.
     *
     * @param productID Unique id of the product
     * @param baseCost Initial cost of product
     * @param description Description of this product
     * */
    public Product(
            int productID,
            Double baseCost,
            String description
            ) {
        this.setProductID(productID);
        this.setDescription(description);
        this.setBaseCost(baseCost);
    }


    public int getProductID() { return productID; }
    public String getDescription() { return description; }

    public void setProductID(int productID) { this.productID = productID; }
    public void setDescription(String description) { this.description = description; }
    public Double getBaseCost() {
        return baseCost;
    }

    public void setBaseCost(Double baseCost) {
        this.baseCost = baseCost;
    }


}
