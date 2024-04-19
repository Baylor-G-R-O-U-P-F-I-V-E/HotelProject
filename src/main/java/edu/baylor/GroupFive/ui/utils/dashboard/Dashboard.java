package edu.baylor.GroupFive.ui.utils.dashboard;

import edu.baylor.GroupFive.models.enums.Privilege;
import edu.baylor.GroupFive.ui.utils.buttons.DashboardButton;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

import javax.swing.*;

public class Dashboard extends JPanel {

    public Dashboard(InputDelegate page, Privilege privilige) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add buttons to the dashboard
        add(new DashboardButton(page, "src/main/resources/button-icons/logout-icon.png", "logout"));
        add(new DashboardButton(page, "src/main/resources/button-icons/account-settings-icon.png", "account-settings"));
        add(new DashboardButton(page, "src/main/resources/button-icons/home-icon.png", "home"));
        add(new DashboardButton(page, "src/main/resources/button-icons/find-rooms-icon.png", "find-rooms"));
        if (privilige == Privilege.ADMIN) {
            add(new DashboardButton(page, "src/main/resources/button-icons/create-clerk-icon.png", "create-clerk"));
        }
        if (privilige != Privilege.GUEST) {
            add(new DashboardButton(page, "src/main/resources/button-icons/reservations-icon.png", "reservations"));
        }
        
        add(new DashboardButton(page, "src/main/resources/button-icons/checkout-icon.png", "checkout"));
    }
}
