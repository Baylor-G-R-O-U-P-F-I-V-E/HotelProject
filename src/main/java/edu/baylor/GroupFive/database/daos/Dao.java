 /**
  * Author: Icko
  * Date Created: 4/9/2024
  * Date Last Modified: 4/9/2024
  * */

package edu.baylor.GroupFive.database.daos;

import java.util.List;
import java.sql.SQLException;

 /**
  * {@code Dao<T>} is an interface that represents the base functionalities
  * for all *DAO objects in our project. We allow the user to retrieve
  * a connection to our database along with the following functionality:
  *
  * DriverManager.getConnection() throws either an SQLException or
  * SQLTimeoutException upon fail. Note that a SQLTimeExceptions are
  * swallowed by SQLExceptions.
  *
  * https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html#getConnection-java.lang.String-
  *
  * {@link Dao#get(int)} 
  * {@link Dao#getAll()} 
  * {@link Dao#save(Object)} 
  * {@link Dao#insert(Object)} 
  * {@link Dao#update(Object)} 
  * {@link Dao#delete(Object)} 
  *
  * @param <T> Object this DAO is meant to interact with
  * @author Icko
  * */
public interface Dao<T> {

    /**
     * Retrieves an object from our database matching the specified id.
     *
     * @param id Id of object.
     * @return Object if found. {@code null} if not.
     * @throws SQLException If error occurs during database communication.
     * */
    public T get(int id) throws SQLException;

    /**
     * Retrieves all objects in our database.
     *
     * @return A List of objects.
     * @throws SQLException If error occurs during database communication.
     * */
    public List<T> getAll() throws SQLException;

     /**
      * Either inserts or updates based on our database.
      *
      * @param t Object to save.
      * @return Number of rows affected by query.
      * @throws SQLException If error occurs during database communication.
      * */
    public Integer save(T t) throws SQLException;

     /**
      * Inserts an object into our database.
      *
      * @param t Object to insert.
      * @return Number of rows affected by query.
      * @throws SQLException If error occurs during database communication.
      * */
    public Integer insert(T t) throws SQLException;

     /**
      * Updates an existing object in our database.
      *
      * @param t Object with new changes.
      * @return Number of rows affected by query.
      * @throws SQLException If error occurs during database communication.
      * */
    public Integer update(T t) throws SQLException;

     /**
      * "Deletes" an object in our database. Does this by setting an {@code active}
      * flag from {@code true} to {@code false}.
      * We should not actually be deleting anything from the database except for drastic reasons
      *
      * @param t Object to delete.
      * @return Number of rows affected by query.
      * @throws SQLException If error occurs during database communication.
      * */
    public Integer delete(T t) throws SQLException;

}
