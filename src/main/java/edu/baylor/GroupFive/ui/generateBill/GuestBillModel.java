package edu.baylor.GroupFive.ui.generateBill;

import java.util.List;

import edu.baylor.GroupFive.database.controllers.BillingController;
import edu.baylor.GroupFive.models.Transaction;
import edu.baylor.GroupFive.ui.utils.interfaces.DataModel;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;

public class GuestBillModel extends HotelModel implements DataModel {

    private Double totalAmount;
    private List<Transaction> transactions;
    private String username;

    public GuestBillModel(String[] columnNames, Class<?>[] columnClass, String username) {
        super(columnNames, columnClass);
        this.username = username;

        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

    public void getData() throws RuntimeException {
        // Fetch users transactions from the database
        List<Transaction> transactions = BillingController.getUserTransactions(username);

        // Check if data was fetched successfully
        if (transactions == null) {
            throw new RuntimeException("Error fetching data from the database");
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

    public Double getTotalAmount() {
        return totalAmount;
    }

}
