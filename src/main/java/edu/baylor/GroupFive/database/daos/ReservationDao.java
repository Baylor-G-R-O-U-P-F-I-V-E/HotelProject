 /**
  * Author: Icko
  * */
package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;

import java.sql.SQLException;
import java.sql.Connection;
import java.util.List;
import java.util.Date;

public interface ReservationDao extends Dao<Reservation> {
    public Reservation getInfo(Integer roomNumber, Date startDate) throws SQLException;
    public Boolean checkIfAvailable(String roomNumber, Date startDate, Date endDate) throws SQLException;
}
