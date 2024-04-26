package edu.baylor.GroupFive.database.services;

import java.util.Date;
import java.util.List;

import edu.baylor.GroupFive.database.daos.TransactionDAO;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.Transaction;

/**
 * The TransactionService class contains static methods related to managing
 * transactions in our database.
 *
 * @author Brendon
 */
public class TransactionService {

    private TransactionService() {};

    /**
     * Searches our database for a transaction with a given {@code id}. If the
     * transaction exists in our database, it is returned. Otherwise a null
     * object is returned.
     *
     * @param id Id of transaction.
     * @return Transaction if it exists. {@code null} otherwise.
     * @author Brendon
     */
    public static Transaction getTransaction(long id) {
        TransactionDAO transactionDAO = new TransactionDAO();
        return transactionDAO.get(Integer.parseInt(String.valueOf(id)));
    }

    /**
     * Gets all transactions in our database.
     *
     * @return A List containing all transactions in our database.
     * @author Brendon
     */
    public static List<Transaction> getTransactions() {
        TransactionDAO transactionDAO = new TransactionDAO();
        return transactionDAO.getAll();
    }

    /**
     * Gets all transactions tied to a User with username {@code username}.
     *
     * @param username Username of user.
     * @return A List containing all transactions tied to {@code username}.
     * @author Brendon
     */
    public static List<Transaction> getUserTransactions(String username) {
        TransactionDAO transactionDAO = new TransactionDAO();
        return transactionDAO.getByUsername(username);
    }

    /**
     * Gets all transactions tied to the currently logged in user.
     *
     * @return A List containing all transactions.
     * @author Brendon
     */
    public static List<Reservation> getCurrentGuestTransactions() {
        // FIXME return type, function call. -Icko
        return ReservationServices.getCurrentGuestTransactions();
    }

    /**
     * Links a transaction to a user and adds it to our database.
     *
     * @param username Username of the User
     * @param description Transaction description
     * @param amount Value of transaction
     * @author Brendon
     */
    public static void addTransaction(String username, String description, float amount) {
        Transaction transaction = new Transaction(username, description, new Date(), amount);
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.save(transaction);
    }

    /**
     * Updates a transaction in our database.
     *
     * @param transaction Transaction with updated information.
     * @author Brendon
     */
    public static void updateTransaction(Transaction transaction) {
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.save(transaction);
    }

    /**
     * Deletes a transaction in our database.
     *
     * @param transaction Transaction to delete.
     * @author Brendon
     */
    public static void deleteTransaction(Transaction transaction) {
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.delete(transaction);
    }

}
