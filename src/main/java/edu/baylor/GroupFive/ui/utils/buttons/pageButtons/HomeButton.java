package edu.baylor.GroupFive.ui.utils.buttons.pageButtons;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import edu.baylor.GroupFive.ui.utils.buttons.DashboardButton;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

public class HomeButton extends DashboardButton {
    public HomeButton(InputDelegate page, String path) {
        super(path);
        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add the dashboard
                page.onPageSwitch("home");
            }
        });
    }
}
