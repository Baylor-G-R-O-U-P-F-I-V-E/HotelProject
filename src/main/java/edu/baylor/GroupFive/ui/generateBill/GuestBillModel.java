package edu.baylor.GroupFive.ui.generateBill;

import java.util.List;

import edu.baylor.GroupFive.database.controllers.BillingController;
import edu.baylor.GroupFive.models.Transaction;
import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;

/**
 * Model for generating bills for a specific guest.
 * This model extends {@link edu.baylor.GroupFive.ui.utils.table.HotelModel}
 * and implements {@link edu.baylor.GroupFive.ui.utils.interfaces.DataModel},
 * providing functionality to display a guest's transactions and calculate the total.
 *
 * @see edu.baylor.GroupFive.ui.utils.table.HotelModel
 * @see edu.baylor.GroupFive.ui.utils.interfaces.DataModel
 * @author Brendon
 */
public class GuestBillModel extends HotelModel implements DataModel {

    private Double totalAmount;
    private List<Transaction> transactions;
    private String username;

    /**
     * Constructs a GuestBillModel with the specified column names, column classes, and username.
     *
     * @param columnNames An array of column names.
     * @param columnClass An array of column classes.
     * @param username The username of the guest.
     */
    public GuestBillModel(String[] columnNames, Class<?>[] columnClass, String username) {
        super(columnNames, columnClass);
        this.username = username;

        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    /**
     * Retrieves transaction data for the guest from the database.
     *
     * @throws RuntimeException If an error occurs while fetching data from the database.
     */
    public void getData() throws RuntimeException {
        // Fetch users transactions from the database
        List<Transaction> transactions = BillingController.getUserTransactions(username);

        // Get any active reservations for the user
        List<Transaction> activeReservations = BillingController.getActiveReservations(username);

        // Check if data was fetched successfully
        if (transactions == null) {
            throw new RuntimeException("Error fetching data from the database");
        }

        for (Transaction transaction : activeReservations) {
            transactions.add(transaction);
        }

        // Add the data to the table
        for (Transaction transaction : transactions) {
            try {
                // Add the row to the table
                addRow(new Object[] {transaction.getDescription(), String.valueOf(transaction.getAmount()), transaction.getPurchaseDate()});
                // Print the row to the console
                System.out.println("Added row to table: " + transaction.getDescription() + ", " + transaction.getAmount() + ", " + transaction.getPurchaseDate());
            } catch (Exception e) {
                // Log any errors
                System.out.println("Error adding row to table");
            }
        }

        // Calculate the total amount
        totalAmount = transactions.stream().mapToDouble(Transaction::getAmount).sum();

    }

    /**
     * Retrieves the total amount of all transactions for the guest.
     *
     * @return The total amount of all transactions.
     */
    public Double getTotalAmount() {
        return totalAmount;
    }

}
