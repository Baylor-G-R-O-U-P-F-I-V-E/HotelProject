package edu.baylor.GroupFive.ui.inventory;

import edu.baylor.GroupFive.database.services.ProductServices;
import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;
import edu.baylor.GroupFive.database.controllers.ShopController;
import edu.baylor.GroupFive.database.controllers.StockController;
import edu.baylor.GroupFive.models.Product;
import edu.baylor.GroupFive.models.Stock;

import java.util.List;

public class AddInventoryModel extends HotelModel implements DataModel {

    /**
     * Constructs an AddInventoryModel with the specified column names and classes.
     *
     * @param columnNames An array of column names.
     * @param columnClasses An array of column classes.
     */
    public AddInventoryModel(String[] columnNames, Class<?>[] columnClasses) {
        super(columnNames, columnClasses);

        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    /**
     * Retrieves product data from the database and populates the table with it.
     *
     * @throws RuntimeException If there is an error fetching data from the database.
     */
    public void getData() throws RuntimeException {
        // Fetch stock data from the database
        List<Stock> stock = StockController.getAllStock();

        // Check if data was fetched successfully
        if (stock == null) {
            throw new RuntimeException("Error fetching data from the database");
        }

        // Add the data to the table
        for (Stock currStock : stock) {
            try {
                // Add the row to the table
                addRow(new Object[] {String.valueOf(currStock.getProductID()), String.valueOf(StockController.getName(currStock)), String.valueOf(StockController.getCost(currStock)), String.valueOf(currStock.getStock())});
                // Print the row to the console
                System.out.println("Added row to table: " + currStock.getProductID() + ", " + StockController.getName(currStock) + ", " + StockController.getCost(currStock) + ", " + currStock.getStock() );
            } catch (Exception e) {
                // Log any errors
                System.out.println("Error adding row to table");
            }
        }
    }


    /**
     * Clears the product table.
     */
    private void clearTable() {
        // Clear the table
        for (int i = getRowCount() - 1; i >= 0; i--) {
            removeRow(i);
        }
    }

}
