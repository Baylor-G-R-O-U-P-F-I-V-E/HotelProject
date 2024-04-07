package edu.baylor.GroupFive;

import javax.swing.SwingUtilities;

import edu.baylor.GroupFive.ui.landing.LandingPage;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;
import edu.baylor.GroupFive.controllers.AccountController;
import edu.baylor.GroupFive.database.dbSetup;
public class Main {
    public static void main(String[] args) {
        @SuppressWarnings("unused")
        dbSetup db = new dbSetup();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                @SuppressWarnings("unused")
                //InputDelegate landing = new LandingPage();
                // Uncomment the following line to skip login page with Admin
                InputDelegate landing = new Page(AccountController.getUser("Bongo"));

                // Uncomment this one for clerk
                //InputDelegate landing = new Page(AccountController.getUser("KevDog"));

                // Uncomment this one for guest
                //InputDelegate landing = new Page(AccountController.getUser("Ant"));
            }
        });
    }
}
