package edu.baylor.GroupFive.ui.utils;

import edu.baylor.GroupFive.ui.utils.buttons.DashboardButton;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

import javax.swing.*;

public class Dashboard extends JPanel {

    Dashboard(InputDelegate page, String privilige) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add buttons to the dashboard
        add(new DashboardButton("src/main/resources/button-icons/home-icon.png"));
        add(new DashboardButton("src/main/resources/button-icons/reservations-icon.png"));
        add(new DashboardButton("src/main/resources/button-icons/checkout-icon.png"));

        
    }
}
