 /**
  * Author: Icko
  * Date Created: 4/9/2024
  * Date Last Modified: 4/9/2024
  * */

package edu.baylor.GroupFive.database.daos;

import java.util.List;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;

 /**
  * DriverManager.getConnection() throws either an SQLException or
  * SQLTimeoutException upon fail. Note that a SQLTimeExceptions are
  * swallowed by SQLExceptions.
  *
  * https://docs.oracle.com/javase/8/docs/api/java/sql/DriverManager.html#getConnection-java.lang.String-
  * */
public interface Dao<T> {

    public T get(int id) throws SQLException;

    public List<T> getAll() throws SQLException;

     /**
      * Either inserts or updates based on our database
      * */
    public Integer save(T t) throws SQLException;

    public Integer insert(T t) throws SQLException;

    public Integer update(T t) throws SQLException;

     /**
      * We should not actually be deleting anything from the database except for drastic reasons
      * */
    public Integer delete(T t) throws SQLException;

}
