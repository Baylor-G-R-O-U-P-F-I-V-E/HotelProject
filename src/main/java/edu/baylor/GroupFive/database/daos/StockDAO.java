package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.database.DbConnection;
import edu.baylor.GroupFive.database.services.ProductServices;
import edu.baylor.GroupFive.models.Product;
import edu.baylor.GroupFive.models.Stock;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;
import edu.baylor.GroupFive.util.logging.G5Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StockDAO extends BaseDAO<Stock>{

    protected Connection connection;

    public StockDAO(){
        try {
            connection = DbConnection.getConnection();
        } catch (BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
        }
    }


    /**
     * Retrieves a stock entry in our database.
     *
     * @param id The id of the Stock entry in our database
     *
     * @return the stock entry with the associated id.
     */
    @Override
    public Stock get(int id) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT * FROM STOCKS WHERE id = " + String.valueOf(id);
            ResultSet rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                Stock out = new  Stock(rs.getInt("id"),
                        rs.getInt("productID"),
                        rs.getInt("stock")
                );
                return out;
            }

            return null;

        } catch (SQLException e) {
            G5Logger.logger.error(e.getMessage());
            return null;
        }
    }


    public Stock getByProductId(int id) {
        try (Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT * FROM STOCKS WHERE productid = " + id;
            ResultSet rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                Stock out = new  Stock(rs.getInt("id"),
                        rs.getInt("productID"),
                        rs.getInt("stock")
                );
                return out;
            }

            return null;

        } catch (SQLException e) {
            G5Logger.logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves all stock entries in our database.
     *
     * @return A List of every stock entry in our database.
     */
    @Override
    public List<Stock> getAll() throws SQLException {
        try (Statement statement = connection.createStatement()) {
            List<Stock> prods = new ArrayList<>();
            String sqlQuery = "SELECT * FROM STOCKS";
            ResultSet rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                Stock out = new  Stock(rs.getInt("id"),
                        rs.getInt("productID"),
                        rs.getInt("stock")
                );
                prods.add(out);

            }

            return prods;

        } catch (SQLException e) {
            G5Logger.logger.error(e.getMessage());;
            return null;
        }


    }



    /**
     * Saves a stock entity in our database. Either inserts or updates depending on whether a matching id exists.
     *
     * @param stock stock entity to save.
     * @return Number of rows affected by query.
     */
    @Override
    public Integer save(Stock stock) throws SQLException {

        Stock exists = get(stock.getStockID());

        if (exists == null){
            return insert(stock);
        } else {
            return update(stock);
        }


    }

    /**
     * @param stock stock entity to insert.
     * @return 1 signifying success or 0 otherwise
     * @throws SQLException
     */
    @Override
    public Integer insert(Stock stock) throws SQLException {
        try (Statement statement = connection.createStatement()) {

            String sqlInsert = "INSERT INTO STOCKS(productID, stock) VALUES (" +
                    stock.getProductID() + "," + stock.getStock() +
                    ")";
            statement.executeUpdate(sqlInsert);

            return 1;

        } catch (SQLException e) {
            G5Logger.logger.error(e.getMessage());;
            return 0;
        }
    }

    /**
     * Updates an existing Stock in our database.
     *
     * @param updatedInfo Stock with updated information.
     * @return 1 if update succeeds and 0 otherwise
     */
    public Integer update(Stock updatedInfo){

        //stockID
        //productID
        //stock
        try (Statement statement = connection.createStatement()) {

            String sqlUpdate = "UPDATE STOCKS SET stock = " + updatedInfo.getStock() +
                    ", productID = " + updatedInfo.getProductID() + " WHERE id = " + updatedInfo.getStockID();
            statement.executeUpdate(sqlUpdate);

            return 1;

        } catch (SQLException e) {
            G5Logger.logger.error(e.getMessage());;
            return 0;
        }

    }

    /**
     * Deletes a stock entry for a product in our database.
     *
     * @param stock stock entry to delete.
     * @return 1 if successful and 0 otherwise
     */
    public Integer delete(Stock stock){

        try (Statement statement = connection.createStatement()) {


            String sqlDelete = "DELETE FROM STOCKS WHERE id = " + stock.getStockID();
            statement.execute(sqlDelete);

            return 1;

        } catch (SQLException e) {
            G5Logger.logger.error(e.getMessage());
            return 0;
        }

    }
}
