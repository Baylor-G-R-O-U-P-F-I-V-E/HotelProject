package edu.baylor.GroupFive;

import javax.swing.SwingUtilities;

import edu.baylor.GroupFive.ui.landing.LandingPage;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;
//import edu.baylor.GroupFive.controllers.AccountController;
import edu.baylor.GroupFive.database.DbSetup;
import edu.baylor.GroupFive.database.controllers.AccountController;
import edu.baylor.GroupFive.util.logging.G5Logger;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Main {
    public static final Marker RESERVATIONS = MarkerManager.getMarker("RESERVATIONS");

    public static void main(String[] args) {
        G5Logger.initLogging(args);
        Logger logger = LogManager.getLogger(Main.class);
        logger.info("Logging initiated. Invoking dbSetup...");
        @SuppressWarnings("unused")
        DbSetup db = new DbSetup();
        DbSetup.dbInit();

        logger.info("dbSetup finished. Queuing initial window/page load with swing...");
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                logger.info("Loading landing page...");
                @SuppressWarnings("unused")
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
