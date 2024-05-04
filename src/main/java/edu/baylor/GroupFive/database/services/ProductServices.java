package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.daos.ProductsDAO;
import edu.baylor.GroupFive.models.Product;
import edu.baylor.GroupFive.util.logging.G5Logger;

import java.sql.SQLException;
import java.util.List;

public class ProductServices {

    /**
     * Searches for a product in our database given a product ID. If it exists,
     * the Product is returned. Otherwise {@code null} is returned.
     *
     * @param productID product number.
     * @return {@code product} object if it exists. {@code null} otherwise.
     */
    public static Product getProduct(Integer productID) throws SQLException {
        ProductsDAO conn = new ProductsDAO();
        return conn.get(productID);
    }

    /**
     * @param newProduct id will be negative if a new entry
     * @return true if successful and false otherwise
     */
    public static Boolean addOrUpdateProduct(Product newProduct){
        ProductsDAO productsDAO = new ProductsDAO();
        try {
            productsDAO.save(newProduct);
        } catch (SQLException e) {
            G5Logger.logger.error(e.getMessage());
            return false;
        }
        return true;
    }

    /**
     * Returns list of products.
     *
     * @return List of Products
     * @throws SQLException
     */
    public static List<Product> getListOfProducts() throws SQLException {
        ProductsDAO conn = new ProductsDAO();
        return conn.getAll();
    }

}
