package edu.baylor.GroupFive.database.controllers;

import edu.baylor.GroupFive.models.Stock;
import edu.baylor.GroupFive.database.services.StockServices;

import java.sql.SQLException;
import java.util.List;

public class StockController {

    /**
     * This function returns all stock in our database.
     *
     * @return List of all stock in our database
     * @see StockServices#getAllStock()
     * */
    public static List<Stock> getAllStock() {
        try {
            return StockServices.getAllStock();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This function searches for a Stock item in our database matching a given id
     * number. If it exists, the room is returned. Otherwise {@code null} is
     * returned.
     *
     * @param stockID stock id of Stock.
     * @return Stock if exists in database. {@code null} otherwise
     * @see StockServices#getStock (Integer)
     * */
    public static Stock getStockInfo(Integer stockID) throws SQLException {
        return StockServices.getStock(stockID);
    }

    /**
     * This function takes in a stock object and updates our database with
     * the new information.
     *
     * @param updatedInfo Stock with updated information.
     * @return {@code true} if stock was modified successfully. {@code false} otherwise
     * @see StockServices#modifyStock(Stock)
     * */
    public static Boolean modifyStock(Stock updatedInfo) throws SQLException {
        //true only if room already exists and modification is successful
        return StockServices.modifyStock(updatedInfo);
    }

    /**
     * This function takes in a stock object and adds it to our database.
     *
     * @param newStock Stock to add.
     * @return {@code true} if stock was added successfully. {@code false} otherwise
     * @see StockServices#createEntry(Stock)
     * */
    public static Boolean addStock(Stock newStock){
        //true if room is added
        return StockServices.createEntry(newStock);
    }

    /**
     * This function takes in a stock id and a number of items to add to stock
     * and adds the items to the stock.
     *
     * @param stockID stock id of Stock.
     * @param addToStock number of items to add to stock.
     * @return {@code true} if stock was added successfully. {@code false} otherwise
     * @see StockServices#fillStock(Integer, Integer)
     * */
    public static Boolean deleteStock(int stockID) throws SQLException {
        return StockServices.deleteStock(stockID);
    }

    /**
     * This function takes in a stock id and a number of items to add to stock
     * and adds the items to the stock.
     *
     * @param stockID stock id of Stock.
     * @param addToStock number of items to add to stock.
     * @return {@code true} if stock was added successfully. {@code false} otherwise
     * @see StockServices#fillStock(Integer, Integer)
     * */
    public static Boolean fillStock(Integer stockID, Integer addToStock){
        return StockServices.fillStock(stockID, addToStock);
    }

    /**
     * This function takes in a stock id and a number of items to remove from stock
     * and removes the items from the stock.
     *
     * @param stockID stock id of Stock.
     * @param removeFromStock number of items to remove from stock.
     * @return {@code true} if stock was removed successfully. {@code false} otherwise
     * @see StockServices#takeStock(Integer, Integer)
     * */
    public static Boolean takeStock(Integer stockID, Integer removeFromStock){
        return StockServices.takeStock(stockID, removeFromStock);
    }

    /**
     * This function returns the stock of a product with a given product id.
     *
     * @param productID product id of product.
     * @return Stock of product.
     * @see StockServices#getStockByProductID(Integer)
     * */
    public static Stock getStockByProductID(Integer productID) {
        return StockServices.getStockByProductID(productID);
    }

}
