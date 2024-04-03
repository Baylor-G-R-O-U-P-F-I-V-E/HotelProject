package edu.baylor.GroupFive;

import javax.swing.SwingUtilities;

import edu.baylor.GroupFive.ui.login.LoginPage;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;
import edu.baylor.GroupFive.database.dbSetup;
public class Main {
    public static void main(String[] args) {
        dbSetup db = new dbSetup();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                @SuppressWarnings("unused")
                //InputDelegate landing = new LoginPage();
                InputDelegate landing = new Page(null); // Will be changed to LoginPage
            }
        });
    }
}
