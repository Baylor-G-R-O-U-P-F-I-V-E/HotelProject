 /**
  * Author: Icko
  * */
package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.models.Reservation;

import java.sql.SQLException;

/**
 * The ReservationDao interface declares methods for interacting
 * with reservation data in a database.
 *
 * This class extends the Dao interface.
 *
 * @see edu.baylor.GroupFive.database.daos.Dao
 * @author Icko
 * */
public interface ReservationDao extends Dao<Reservation> {
    /**
     * Checks if a new reservation has any booking conflicts.
     *
     * @param reservation New reservation.
     * @return {@code true} if available. {@code false} otherwise.
     * @throws SQLException If error occurs during database communication.
     * @author Icko
     * */
    public Boolean checkIfAvailable(Reservation reservation) throws SQLException;
}
