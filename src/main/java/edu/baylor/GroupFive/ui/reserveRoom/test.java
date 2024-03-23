package edu.baylor.GroupFive.ui.reserveRoom;

import javax.swing.SwingUtilities;

import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

public class test {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                InputDelegate page = new Page("admin");
            }
        });
    }
}
