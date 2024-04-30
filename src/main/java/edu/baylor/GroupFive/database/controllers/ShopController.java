package edu.baylor.GroupFive.database.controllers;

import edu.baylor.GroupFive.database.services.ProductServices;
import edu.baylor.GroupFive.models.Product;
import edu.baylor.GroupFive.models.Stock;
import edu.baylor.GroupFive.database.services.StockServices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShopController {
    // Use this controller instead of StockController to display product descriptions & prices
    // TODO: getAllProducts(): retrieve unique products with stock > 0

    /**
     * This function returns all unique products in our database
     * that are in stock.
     *
     * @return List of all unique products in our database
     * @see StockServices#getAllStock()
     * */
    public static List<Product> getAllProducts() {
        List<Stock> allStock;
        List<Product> allProducts = new ArrayList<>();
        try {
            allStock = StockServices.getAllStock();
            for(Stock stock: allStock){
                allProducts.add(ProductServices.getProduct(stock.getProductID()));
            }
            if(allProducts.size() == 0){ return null; }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allProducts;
    }

    /**
     * This function searches for a Product item in our database matching a given id
     * number. If it exists, the Product is returned. Otherwise {@code null} is
     * returned.
     *
     * @param productID product id of Product.
     * @return Product if exists in database. {@code null} otherwise
     * @see ProductServices#getProduct (Integer)
     */
    public static Product getProductInfo(Integer productID) throws SQLException {
        return ProductServices.getProduct(productID);
    }

}
