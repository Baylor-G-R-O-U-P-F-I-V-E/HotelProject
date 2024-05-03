package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.daos.StockDAO;
import edu.baylor.GroupFive.models.Stock;
import edu.baylor.GroupFive.util.logging.G5Logger;

import java.sql.SQLException;
import java.util.List;

/*
 * @param stockId the stock entry to have stock incremented
 * @return true if stock added successfully and false otherwise
 */
public class StockServices {

    /**
     * Returns all stock in our database.
     *
     * @return A List of all stock
     */
    public static List<Stock> getAllStock() throws SQLException {
        StockDAO stockConn = new StockDAO();
        return stockConn.getAll();
    };

    /**
     * Searches for a stock in our database given a stock ID. If it exists,
     * the Stock is returned. Otherwise {@code null} is returned.
     *
     * @param stockID stock number.
     * @return {@code Stock} object if it exists. {@code null} otherwise.
     */
    public static Stock getStock(Integer stockID) throws SQLException {
        StockDAO conn = new StockDAO();
        return conn.get(stockID);
    }

    /**
     * Modifies a stock in our database.
     *
     * @param updatedInfo stock with modified information
     * @return {@code true} if successful modification. {@code false} otherwise.
     */
    public static Boolean modifyStock(Stock updatedInfo) throws SQLException {
        StockDAO conn = new StockDAO();
        return conn.save(updatedInfo) == 1 ? true : false;
    }

    /**
     * @param stockId id of stock entry to fill
     * @param addToStock number of items to add to stock
     * @return true is added successfully and false otherwise
     */
    public static Boolean fillStock(Integer stockId, Integer addToStock){
        StockDAO stockDAO = new StockDAO();
        try {
            Stock stock = stockDAO.get(stockId);
            stock.setStock(stock.getStock() + addToStock);
            stockDAO.save(stock);
        } catch (SQLException e) {
            G5Logger.logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    public static Boolean deleteStock(Integer stockID) throws SQLException {
        StockDAO conn = new StockDAO();
        Stock stock = conn.get(stockID);
        return conn.delete(stock) == 1;
    }

    /**
     * @param stockId id of stock entry that needs items deducted
     * @param removeFromStock number of items to take from stock entry
     * @return true if successful and false otherwise
     */
    public static Boolean takeStock(Integer stockId, Integer removeFromStock){
        StockDAO stockDAO = new StockDAO();
        try {
            Stock stock = stockDAO.get(stockId);
            stock.setStock(stock.getStock() - removeFromStock);
            if(stock.getStock() < 0){return false;}
            stockDAO.save(stock);
        } catch (SQLException e) {
            G5Logger.logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    public static Boolean createEntry(Stock newStock){
        StockDAO stockDAO = new StockDAO();
        try {
            stockDAO.save(newStock);
        } catch (SQLException e) {
            G5Logger.logger.error(e.getMessage());
            return false;
        }
        return true;
    }


    public static Integer removeEntry(Stock stock){
        StockDAO stockDAO = new StockDAO();
        return stockDAO.delete(stock);
    }

    public static Integer getNumStockByProductID(Integer productID){
        StockDAO stockDAO = new StockDAO();
        return stockDAO.getByProductId(productID).getStock();
    }

    public static Stock getStockByProductID(Integer productID){
        StockDAO stockDAO = new StockDAO();
        return stockDAO.getByProductId(productID);
    }
}
