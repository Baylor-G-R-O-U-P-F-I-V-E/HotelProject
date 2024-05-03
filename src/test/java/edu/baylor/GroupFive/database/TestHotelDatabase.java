package edu.baylor.GroupFive.database;

import edu.baylor.GroupFive.util.CoreUtils;

import org.apache.logging.log4j.LogManager;

/**
 * This class generates a test database for our testing purposes.
 *
 * @author Icko
 */
public class TestHotelDatabase extends HotelDatabase {

    /**
     * Construct a test database.
     * */
    public TestHotelDatabase() {
        super(CoreUtils.TEST_DB_URL_CREATE);
        logger = LogManager.getLogger(TestHotelDatabase.class.getName());
    }

}
