package edu.baylor.GroupFive.database.controllers;

import java.util.List;

import edu.baylor.GroupFive.database.services.TransactionService;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.Transaction;

/**
 * This class represents a handler between our UI layer and our application
 * layer. Methods from this class are called from our UI layer. {@code BillingController}
 * then determines which Service object in our application layer to continue 
 * operations with. The status of each operation is then returned to the UI layer.
 *
 * @author Brendon
 * */
public class BillingController {

    /**
     * Adds a transaction to our database. This function calls
     * {@link TransactionService#addTransaction(String, String, float)}
     *
     * @param username
     * @param description
     * @param amount
     * @author Brendon
     * @see TransactionService#addTransaction(String, String, float)
     * */
    public static void addTransaction(String username, String description, float amount) {
        TransactionService.addTransaction(username, description, amount);
    }

    /**
     * Updates a transaction in our database. This function calls
     * {@link TransactionService#updateTransaction(Transaction)}
     *
     * @param transaction
     * @author Brendon
     * @see TransactionService#updateTransaction(Transaction)
     * */
    public static void updateTransaction(Transaction transaction) {
        TransactionService.updateTransaction(transaction);
    }

    /**
     * Deletes a transaction from our database. This function calls
     * {@link TransactionService#deleteTransaction(Transaction)}
     *
     * @param transaction
     * @author Brendon
     * @see TransactionService#deleteTransaction(Transaction)
     * */
    public static void deleteTransaction(Transaction transaction) {
        TransactionService.deleteTransaction(transaction);
    }

    /**
     * This function searches for a transaction in a database using
     * an id and returns that transaction. This function calls
     * {@link TransactionService#getTransaction(long)}
     *
     * @param id
     * @return transaction matching {@code id}
     * @author Brendon
     * @see TransactionService#getTransaction(long)
     * */
    public static Transaction getTransaction(long id) {
        return TransactionService.getTransaction(id);
    }

    /**
     * This function returns a list of all transactions in our database.
     * This function calls {@link TransactionService#getTransactions()}
     *
     * @return List of all transactions in database
     * @author Brendon
     * @see TransactionService#getTransactions()
     * */
    public static List<Transaction> getTransactions() {
        return TransactionService.getTransactions();
    }

    /**
     * This function returns a list of all transactions tied
     * to a user with a given {@code username}. This function
     * calls {@link TransactionService#getUserTransactions(String)}
     *
     * @param username
     * @return List of all transactions associated with {@code username}
     * @author Brendon
     * @see TransactionService#getUserTransactions(String)
     * */
    public static List<Transaction> getUserTransactions(String username) {
        return TransactionService.getUserTransactions(username);
    }

    /**
     * This function returns a list of all current reservations
     * associated with the guest currently logged into our system.
     * This function calls {@link TransactionService#getCurrentGuestTransactions()}
     *
     * @return List of reservations tied to logged in guest
     * @author Brendon
     * @see TransactionService#getCurrentGuestTransactions()
     * */
    public static List<Reservation> getCurrentGuestTransactions() {
        return TransactionService.getCurrentGuestTransactions();
    }

}
