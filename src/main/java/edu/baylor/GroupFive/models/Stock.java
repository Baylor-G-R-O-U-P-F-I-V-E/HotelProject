package edu.baylor.GroupFive.models;

/**
 * The Stock class represents the total number of items for a product currently in stock
 *
 * @author Cole
 * */
public class Stock {
    private Integer productID;

    private Integer stock;

    /**
     * Constructs a Product with the specified attributes.
     *
     * @param productID Unique id of the product
     * @param stock Number of item in stock
     * */
    public Stock(Integer productID, Integer stock) {
        this.productID = productID;
        this.stock = stock;
    }
    public Integer getProductID() {
        return productID;
    }

    public void setProductID(Integer productID) {
        this.productID = productID;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }


}
