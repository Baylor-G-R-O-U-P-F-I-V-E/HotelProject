package edu.baylor.GroupFive.models;

public class ProductDescription {
    private double price;
    private String productName;
    private String productType;

    public ProductDescription(
            double price_,
            String productName_,
            String productType_
            ) {
        price = price_;
        productName = productName_;
        productType = productType_;
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
