package edu.baylor.GroupFive.models;

/**
 * The Stock class represents the total number of items for a product currently in stock
 *
 * @author Cole
 * */
public class Stock {
    private Integer stockID;
    private Integer productID;

    private Integer stock;

    /**
     * Constructs a Product with the specified attributes.
     *
     * @param productID Unique id of the product
     * @param stock Number of item in stock
     * */
    public Stock(Integer stockID, Integer productID, Integer stock) {
        setProductID(productID);
        setStock(stock);
        setStockID(stockID);
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


    public Integer getStockID() {
        return stockID;
    }

    public void setStockID(Integer stockID) {
        this.stockID = stockID;
    }
}
