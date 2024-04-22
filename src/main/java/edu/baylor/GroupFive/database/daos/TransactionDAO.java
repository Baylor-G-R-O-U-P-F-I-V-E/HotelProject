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

public class TransactionDAO extends BaseDAO<Transaction> {

    public TransactionDAO() {}

    public Integer save(Transaction transaction) {
        if (transaction.getId() == 0) {
            return insert(transaction);
        } else {
            return update(transaction);
        }
    }

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
                    result.getFloat("amount")
                );
            } else {
                return null;
            }
            
        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
            return null;
        }
    }

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

    protected Transaction parseResultSet(ResultSet result) throws SQLException {
        return new Transaction(
            result.getString("username"),
            result.getString("description"),
            result.getDate("purchaseDate"),
            result.getFloat("amount")
        );
    }

}
