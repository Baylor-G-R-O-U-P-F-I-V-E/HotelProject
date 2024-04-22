package edu.baylor.GroupFive.database.controllers;

import java.util.List;

import edu.baylor.GroupFive.database.services.TransactionService;
import edu.baylor.GroupFive.models.Transaction;

public class BillingController {

    public static void addTransaction(String username, String description, float amount) {
        TransactionService.addTransaction(username, description, amount);
    }

    public static void updateTransaction(Transaction transaction) {
        TransactionService.updateTransaction(transaction);
    }

    public static void deleteTransaction(Transaction transaction) {
        TransactionService.deleteTransaction(transaction);
    }

    public static Transaction getTransaction(long id) {
        return TransactionService.getTransaction(id);
    }

    public static List<Transaction> getTransactions() {
        return TransactionService.getTransactions();
    }

}
