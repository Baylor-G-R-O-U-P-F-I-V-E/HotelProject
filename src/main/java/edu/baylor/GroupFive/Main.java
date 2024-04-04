package edu.baylor.GroupFive;

import javax.swing.SwingUtilities;

import edu.baylor.GroupFive.ui.landing.LandingPage;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;
import edu.baylor.GroupFive.database.dbSetup;
import edu.baylor.GroupFive.util.logging.G5Logger;
import org.apache.logging.log4j.Logger;

public class Main {
    public static void main(String[] args) {
        Logger logger = G5Logger.logger;
        logger.debug("Debug message"); // This will be logged because the logger level is DEBUG
        logger.info("Info message");   // This will also be logged
        logger.warn("Warn message");
        logger.error("Error message");
        logger.fatal("Fatal message");
//        dbSetup db = new dbSetup();
//        SwingUtilities.invokeLater(new Runnable() {
//            public void run() {
//                @SuppressWarnings("unused")
//                InputDelegate landing = new LandingPage();
//                //InputDelegate landing = new Page(null); // Will be changed to LoginPage
//            }
//        });
    }
}
