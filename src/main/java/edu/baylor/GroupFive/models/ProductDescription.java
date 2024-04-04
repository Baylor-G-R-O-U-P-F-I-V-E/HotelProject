package edu.baylor.GroupFive.models;

public class ProductDescription {
    private double price;
    private String productName;
    private String productType;

    public ProductDescription() {
        price = -1;
        productName = "";
        productType = "";
    }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public String getProductName() { return productName; }

    public void setProductName(String productName) { this.productName = productName; }

    public String getProductType() { return productType; }

    public void setProductType(String productType) { this.productType = productType; }
}
