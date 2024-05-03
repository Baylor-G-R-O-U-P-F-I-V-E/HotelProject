package edu.baylor.GroupFive;

import javax.swing.SwingUtilities;

import edu.baylor.GroupFive.util.CoreUtils;
import edu.baylor.GroupFive.database.HotelDatabase;
import edu.baylor.GroupFive.ui.landing.LandingPage;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;
//import edu.baylor.GroupFive.controllers.AccountController;
import edu.baylor.GroupFive.util.logging.G5Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

/**
 * Big bad initializer class for our entire project.
 */
public class Main {
    public static final Marker RESERVATIONS = MarkerManager.getMarker("RESERVATIONS");

    /**
     * THE main.
     *
     * @param args Command line arguments. Used for logger init.
     * @see edu.baylor.GroupFive.util.logging.G5Logger
     */
    public static void main(String[] args) {
        G5Logger.initLogging(args);
        Logger logger = LogManager.getLogger(Main.class);
        logger.info("Logging initiated. Invoking dbSetup...");

        @SuppressWarnings("unused")
        HotelDatabase db = new HotelDatabase(CoreUtils.DB_URL_CREATE);

        logger.info("dbSetup finished. Queuing initial window/page load with swing...");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                logger.info("Loading landing page...");
                @SuppressWarnings("unused")

                // Uncomment the following for a normal log-in page
                InputDelegate landing = new LandingPage();

                // Uncomment the following line to skip login page with Admin
                //InputDelegate landing = new Page(AccountController.getUser("Bongo"));

                // Uncomment this one for clerk
                //InputDelegate landing = new Page(AccountController.getUser("KevDog"));

                // Uncomment this one for guest
                //InputDelegate landing = new Page(AccountController.getUser("Ant"));
            }
        });
    }
}
