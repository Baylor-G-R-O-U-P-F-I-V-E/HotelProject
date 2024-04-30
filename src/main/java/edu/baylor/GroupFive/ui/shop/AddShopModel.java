package edu.baylor.GroupFive.ui.shop;

import edu.baylor.GroupFive.database.controllers.ShopController;
import edu.baylor.GroupFive.database.controllers.StockController;
import edu.baylor.GroupFive.models.Product;
import edu.baylor.GroupFive.models.Stock;
import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;

import java.util.Date;
import java.util.List;



import java.util.Date;
import java.util.List;

import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.database.controllers.RoomController;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.enums.BedType;
//import edu.baylor.GroupFive.models.enums.Quality;
import edu.baylor.GroupFive.models.enums.Theme;
import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;

/**
 * Model for adding reservations.
 *
 * Extends {@link edu.baylor.GroupFive.ui.utils.table.HotelModel} and
 * implements {@link edu.baylor.GroupFive.ui.utils.interfaces.DataModel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.table.HotelModel
 * @see edu.baylor.GroupFive.ui.utils.interfaces.DataModel
 * @author Siri
 */
public class AddShopModel extends HotelModel implements DataModel {

    /**
     * Constructs an AddReservationModel with the specified column names and classes.
     *
     * @param columnNames An array of column names.
     * @param columnClasses An array of column classes.
     */
    public AddShopModel(String[] columnNames, Class<?>[] columnClasses) {
        super(columnNames, columnClasses);

        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    /**
     * Retrieves room data from the database and populates the table with it.
     *
     * @throws RuntimeException If there is an error fetching data from the database.
     */
    public void getData() throws RuntimeException {
        // Fetch stock data from the database
        // TODO: create ShopController instead to retrieve unique products with stock > 0
        List<Product> products = ShopController.getAllProducts();

        // Check if data was fetched successfully
        if (products == null) {
            throw new RuntimeException("Error fetching data from the database");
        }

        // Add the data to the table
        for (Product product : products) {
            try {
                // Add the row to the table
                addRow(new Object[] {String.valueOf(product.getProductID()), String.valueOf(product.getDescription()), String.valueOf(product.getBaseCost())});
                // Print the row to the console
                System.out.println("Added row to table: " + product.getProductID() + ", " + product.getDescription() + ", " + product.getBaseCost() );
            } catch (Exception e) {
                // Log any errors
                System.out.println("Error adding row to table");
            }
        }
    }


    /**
     * Clears the reservation table.
     */
    private void clearTable() {
        // Clear the table
        for (int i = getRowCount() - 1; i >= 0; i--) {
            removeRow(i);
        }
    }


}
