package edu.baylor.GroupFive.database.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import edu.baylor.GroupFive.database.DbConnection;
import edu.baylor.GroupFive.models.Transaction;
import edu.baylor.GroupFive.util.CoreUtils;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;
import edu.baylor.GroupFive.util.logging.G5Logger;

/**
 * The TransactionDAO class provides methods for interacting with transaction data
 * in a database.
 *
 * This class implements the BaseDAO abstract class.
 *
 * @see edu.baylor.GroupFive.database.daos.BaseDAO
 * @author Brendon
 */
public class TransactionDAO extends BaseDAO<Transaction> {

    public TransactionDAO() {}

    /**
     * Saves a transaction into the database. Either inserts or updates behind
     * the scenes.
     *
     * @param transaction Transaction to save.
     * @return Number of rows affected by query.
     */
    public Integer save(Transaction transaction) {
        if (transaction.getId() == 0) {
            return insert(transaction);
        } else {
            return update(transaction);
        }
    }

    /**
     * Inserts a transaction into the database.
     * 
     * @param transaction Transaction to insert.
     * @return Number of rows affected by query.
     */
    public Integer insert(Transaction transaction) {
        String sql = "INSERT INTO transactions (username, description, purchaseDate, amount) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = DbConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, transaction.getUsername());
            statement.setString(2, transaction.getDescription());
            statement.setDate(3, CoreUtils.getSqlDate(transaction.getPurchaseDate()));
            statement.setFloat(4, transaction.getAmount());
            statement.executeUpdate();
            return 1;
        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
            return 0;
        }
    }

    /**
     * Updates an existing transaction in the database.
     *
     * @param transaction Transaction with updated information.
     * @return Number of rows affected by query.
     */
    public Integer update(Transaction transaction) {
        String sql = "UPDATE transactions SET username = ?, description = ?, purchaseDate = ?, amount = ? WHERE id = ?";
        
        try (Connection connection = DbConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, transaction.getUsername());
            statement.setString(2, transaction.getDescription());
            statement.setDate(3, CoreUtils.getSqlDate(transaction.getPurchaseDate()));
            statement.setFloat(4, transaction.getAmount());
            statement.setLong(5, transaction.getId());
            statement.executeUpdate();
            return 1;
        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
            return 0;
        }
    }

    /**
     * Deletes a transaction in our database.
     *
     * @param transaction Transaction to delete.
     * @return Number of rows affected by query.
     */
    public Integer delete(Transaction transaction) {
        String sql = "DELETE FROM transactions WHERE id = ?";
        
        try (Connection connection = DbConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, transaction.getId());
            statement.executeUpdate();
            return 1;
        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
            return 0;
        }
    }

    /**
     * Retrieves a transaction from our database given an id.
     *
     * @param id Transaction id.
     * @return Transaction if found. {@code null} otherwise.
     * */
    public Transaction get(int id) {
        String sql = "SELECT * FROM transactions WHERE id = ?";
        
        try (Connection connection = DbConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                return new Transaction(
                    result.getString("username"),
                    result.getString("description"),
                    result.getDate("purchaseDate"),
                    result.getFloat("amount"),
                    result.getInt("id")
                );
            } else {
                return null;
            }
            
        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
            return null;
        }
    }

    /**
     * Retreives all transactions in our database.
     *
     * @return A List of transactions.
     */
    public List<Transaction> getAll() {
        String sql = "SELECT * FROM transactions";
        List<Transaction> transactions = null;
        
        try (Connection connection = DbConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            
            ResultSet result = statement.executeQuery();

            transactions = new ArrayList<>();

            while (result.next()) {
                transactions.add(parseResultSet(result));
            }

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
            return null;
        }

        return transactions;
    }

    /**
     * Retrieves all transactions tied to a given User.
     *
     * @param username username of User.
     * @return A List of transactions tied to User.
     */
    public List<Transaction> getByUsername(String username) {
        String sql = "SELECT * FROM transactions WHERE username = ?";
        List<Transaction> transactions = null;
        
        try (Connection connection = DbConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet result = statement.executeQuery();

            transactions = new ArrayList<>();

            while (result.next()) {
                transactions.add(parseResultSet(result));
            }

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
            return null;
        }

        return transactions;
    }

    /**
     * Helper function to parse Transaction data from a ResultSet.
     *
     * @param result ResultSet object returned by query.
     * @return Transaction object.
     * @throws SQLException If error occurs during ResultSet parsing.
     */
    protected Transaction parseResultSet(ResultSet result) throws SQLException {
        return new Transaction(
            result.getString("username"),
            result.getString("description"),
            result.getDate("purchaseDate"),
            result.getFloat("amount"),
            result.getInt("id")
        );
    }

}
