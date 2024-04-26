 /**
  * Author: Icko
  * */
package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.database.daos.Dao;
import edu.baylor.GroupFive.models.Reservation;

import java.sql.SQLException;

 public interface ReservationDao extends Dao<Reservation> {
    public Boolean checkIfAvailable(Reservation reservation) throws SQLException;
}
