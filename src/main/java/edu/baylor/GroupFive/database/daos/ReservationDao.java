 /**
  * Author: Icko
  * */
package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;

import java.sql.SQLException;
import java.util.Date;

public interface ReservationDao extends Dao<Reservation> {
    public Boolean checkIfAvailable(Reservation reservation) throws SQLException;
}
