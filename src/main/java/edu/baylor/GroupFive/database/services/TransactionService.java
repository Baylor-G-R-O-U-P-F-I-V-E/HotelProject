package edu.baylor.GroupFive.database.services;

import java.util.Date;
import java.util.List;

import edu.baylor.GroupFive.database.daos.TransactionDAO;
import edu.baylor.GroupFive.models.Transaction;

public class TransactionService {

    public static Transaction getTransaction(long id) {
        TransactionDAO transactionDAO = new TransactionDAO();
        return transactionDAO.get(Integer.parseInt(String.valueOf(id)));
    }

    public static List<Transaction> getTransactions() {
        TransactionDAO transactionDAO = new TransactionDAO();
        return transactionDAO.getAll();
    }

    public static void addTransaction(String username, String description, float amount) {
        Transaction transaction = new Transaction(username, description, new Date(), amount);
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.save(transaction);
    }

    public static void updateTransaction(Transaction transaction) {
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.save(transaction);
    }

    public static void deleteTransaction(Transaction transaction) {
        TransactionDAO transactionDAO = new TransactionDAO();
        transactionDAO.delete(transaction);
    }

}
