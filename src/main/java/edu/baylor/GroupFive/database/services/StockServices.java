package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.daos.StockDAO;
import edu.baylor.GroupFive.models.Stock;
import edu.baylor.GroupFive.util.logging.G5Logger;

import java.sql.SQLException;

/*
 * @param stockId the stock entry to have stock incremented
 * @return true if stock added successfully and false otherwise
 */
public class StockServices {
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

    public static Integer createEntry(Stock newStock){
        StockDAO stockDAO = new StockDAO();
        try {
            stockDAO.save(newStock);
        } catch (SQLException e) {
            G5Logger.logger.error(e.getMessage());
            return 0;
        }
        return 1;
    }


    public static Integer removeEntry(Stock stock){
        StockDAO stockDAO = new StockDAO();
        return stockDAO.delete(stock);
    }
}
