package edu.baylor.GroupFive.models;

/**
 * The ProductDescription represents the information concerning a product in our
 * inventory
 *
 * @author Afraz
 * */
public class ProductDescription {
    private double price;
    private String productName;
    private String productType;

    /**
     * Constructs a ProductDescription object with the specified attributes.
     *
     * @param price Price of the product.
     * @param productName Name of the product.
     * @param productType Type of product.
     * @author Afraz
     * */
    public ProductDescription(
            double price,
            String productName,
            String productType
            ) {
        this.setPrice(price);
        this.setProductName(productName);
        this.setProductType(productType);
    }

    // >>>> Getters >>>>
    public double getPrice() { return price; }
    public String getProductName() { return productName; }
    public String getProductType() { return productType; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setPrice(double price) { this.price = price; }
    public void setProductName(String productName) { this.productName = productName; }
    public void setProductType(String productType) { this.productType = productType; }
    // <<<< Setters <<<<

}
