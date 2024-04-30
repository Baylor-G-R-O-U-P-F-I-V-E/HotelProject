package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.database.DbConnection;
import edu.baylor.GroupFive.models.Product;
import edu.baylor.GroupFive.models.ProductDescription;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.enums.BedType;
import edu.baylor.GroupFive.models.enums.Quality;
import edu.baylor.GroupFive.models.enums.Theme;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;
import edu.baylor.GroupFive.util.logging.G5Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * The ProductDAO class provides methods for interacting with Product data in our database.
 *
 * This class implements the {@code BaseDAO} abstract class.
 *
 * @see edu.baylor.GroupFive.database.daos.BaseDAO
 * @author Cole
 */
public class ProductsDAO extends BaseDAO<Product> {
    public ProductsDAO(){}


    /**
     * Retrieves a product with associated id from our database.
     *
     * @param id The id of the product in our database
     *
     * @return the product with associated id in the databse.
     */
    @Override
    public Product get(int id) throws SQLException {
        try (Connection connection = DbConnection.getConnection(); Statement statement = connection.createStatement()) {

            String sqlQuery = "SELECT * FROM PRODUCTS WHERE id = " + String.valueOf(id);
            ResultSet rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                Product out = new  Product(rs.getInt("id"),
                        rs.getDouble("baseCost"),
                        rs.getString("description"),
                        rs.getString("productName")
                );
                return out;
            }

            return null;

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves all Products in our database.
     *
     * @return A List of every Product in our database.
     */
    @Override
    public List<Product> getAll() throws SQLException {
        try (Connection connection = DbConnection.getConnection(); Statement statement = connection.createStatement()) {
            List<Product> prods = new ArrayList<>();
            String sqlQuery = "SELECT * FROM PRODUCTS";
            ResultSet rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                Product out = new  Product(rs.getInt("id"),
                        rs.getDouble("baseCost"),
                        rs.getString("description"),
                        rs.getString("productName")
                );
                prods.add(out);

            }

            return prods;

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());;

        }
        return null;

    }



    /**
     * Saves a Product in our database. Either inserts or updates depending on whether a matching id exists.
     *
     * @param product product to save.
     * @return Number of rows affected by query.
     */
    @Override
    public Integer save(Product product) throws SQLException {

        Product exists = get(product.getProductID());

        if (exists == null){
            return insert(product);
        } else {
            return update(product);
        }


    }

    /**
     * @param product Object to insert.
     * @return 1 signifying success or 0 otherwise
     * @throws SQLException
     */
    @Override
    public Integer insert(Product product) throws SQLException {
        try (Connection connection = DbConnection.getConnection(); Statement statement = connection.createStatement()) {

            String sqlInsert = "INSERT INTO PRODUCTS(baseCost, Description,productName) VALUES (" +
                    product.getBaseCost() + ",'" + product.getDescription() + "','" + product.getProductName() +
                    "')";
            statement.executeUpdate(sqlInsert);

            return 1;

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());;
            return 0;
        }
    }

    /**
     * Updates an existing Product in our database.
     *
     * @param updatedInfo Product with updated information.
     * @return 1 if update succeeds and 0 otherwise
     */
    public Integer update(Product updatedInfo){

        try (Connection connection = DbConnection.getConnection(); Statement statement = connection.createStatement()) {

            String sqlUpdate = "UPDATE PRODUCTS SET baseCost = " + updatedInfo.getBaseCost() +
                    ", description = '" + updatedInfo.getDescription() + "' productName = '" + updatedInfo.getProductName() + "' WHERE id = " + updatedInfo.getProductID();
            statement.executeUpdate(sqlUpdate);

            return 1;

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());;
            return 0;
        }

    }

    /**
     * Deletes a product in our database.
     *
     * @param product Product to delete.
     * @return 1 if successful and 0 otherwise
     */
    public Integer delete(Product product){

        try (Connection connection = DbConnection.getConnection(); Statement statement = connection.createStatement()) {


            String sqlDelete = "DELETE FROM Products WHERE id = " + product.getProductID();
            statement.execute(sqlDelete);

            return 1;

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
            return 0;
        }

    }
}
