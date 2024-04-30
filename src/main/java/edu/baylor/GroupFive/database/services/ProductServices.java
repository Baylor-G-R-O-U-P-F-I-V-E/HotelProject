package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.daos.ProductsDAO;
import edu.baylor.GroupFive.models.Product;
import edu.baylor.GroupFive.util.logging.G5Logger;

import javax.imageio.plugins.tiff.GeoTIFFTagSet;
import java.sql.SQLException;

public class ProductServices {
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



}
