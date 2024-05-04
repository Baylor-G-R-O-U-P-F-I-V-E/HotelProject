package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.database.TestDbConnection;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;
import edu.baylor.GroupFive.util.logging.G5Logger;

/**
 * Extension of UserDAO for testing purposes. This class makes a connection
 * to the test database instead of production database.
 *
 * @author Icko
 * */
public class UserDAOExt extends UserDAO {

    public UserDAOExt() {
        try {
            connection = TestDbConnection.getConnection();
        } catch (BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
        }
    }

}
