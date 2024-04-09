 /**
  * Author: Icko
  * Date Created: 4/9/2024
  * Date Last Modified: 4/9/2024
  * */

package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.util.exceptions.BadConnectionException;

import java.util.List;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface IDao<T> {

    public Connection getConnection() throws BadConnectionException;

    public T get(int id) throws SQLException;

    public List<T> getAll() throws SQLException;

     /**
      * Either inserts or updates based on our database
      * */
    public int save(T t) throws SQLException;

    public Boolean insert(T t) throws SQLException;

    public Boolean update(T t2) throws SQLException;

    public Boolean delete(T t);

}
