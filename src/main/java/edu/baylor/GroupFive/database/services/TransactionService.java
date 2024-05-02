package edu.baylor.GroupFive.database.services;

import java.util.ArrayList;
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
     */
    public static Transaction getTransaction(long id) {
        TransactionDAO transactionDAO = new TransactionDAO();
        return transactionDAO.get(Integer.parseInt(String.valueOf(id)));
    }

    /**
     * Gets all transactions in our database.
     *
     * @return A List containing all transactions in our database.
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
     */
    public static List<Transaction> getUserTransactions(String username) {
        TransactionDAO transactionDAO = new TransactionDAO();
        return transactionDAO.getByUsername(username);
    }

    /**
     * Gets all transactions tied to the currently logged in user.
     *
     * @return A List containing all transactions.
     */
    public static List<Reservation> getCurrentGuestTransactions() {
        List<Reservation> reservations = ReservationServices.getCurrentGuestTransactions();

        // Filter out duplicate usernames
        for (int i = 0; i < reservations.size(); i++) {
            for (int j = i + 1; j < reservations.size(); j++) {
                if (reservations.get(i).getGuestUsername().equals(reservations.get(j).getGuestUsername())) {
                    reservations.remove(j);
                    j--;
                }
            }
        }

        return reservations;
    }

    /**
     * Gets all active reservations as transactions tied to the currently logged in user.
     *
     * @param username Username of user.
     * @return A List containing all active reservations tied to {@code username}.
     */
    public static List<Transaction> getActiveReservationsAsTransactions(String username) {
        List<Reservation> reservations = ReservationServices.getReservationsByGuest(username);

        List<Transaction> transactions = new ArrayList<>();

        for (Reservation reservation : reservations) {
            Transaction transaction = new Transaction(username, "Reservation", new Date(), reservation.getPrice().floatValue());
            transactions.add(transaction);
        }

        return transactions;
    }

    /**
     * Links a transaction to a user and adds it to our database.
     *
     * @param username Username of the User
     * @param description Transaction description
     * @param amount Value of transaction
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
     */
    public static void updateTransaction(Transaction transaction) {
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.save(transaction);
    }

    /**
     * Deletes a transaction in our database.
     *
     * @param transaction Transaction to delete.
     */
    public static void deleteTransaction(Transaction transaction) {
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.delete(transaction);
    }

    public static double getBill(String username) {
        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transaction> userTransactions = transactionDAO.getByUsername(username);
        double bill = 0;
        for(Transaction singleTransaction : userTransactions) {
            bill += singleTransaction.getAmount();
        }

        return bill;
    }

    public static void paid(String username) {
        TransactionDAO transactionDAO = new TransactionDAO();
        List<Transaction> userTranscations = transactionDAO.getByUsername(username);
        for(Transaction singleTransaction : userTranscations) {
            transactionDAO.delete(singleTransaction);
        }
    }
    
}
