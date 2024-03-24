package edu.baylor.GroupFive.ui.utils.dashboard;

import edu.baylor.GroupFive.ui.utils.buttons.DashboardButton;
import edu.baylor.GroupFive.ui.utils.buttons.pageButtons.AddReservationButton;
import edu.baylor.GroupFive.ui.utils.buttons.pageButtons.HomeButton;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

import javax.swing.*;

public class Dashboard extends JPanel {

    public Dashboard(InputDelegate page, String privilige) {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Add buttons to the dashboard
        add(new HomeButton(page, "src/main/resources/button-icons/home-icon.png"));
        add(new DashboardButton("src/main/resources/button-icons/reservations-icon.png"));
        add(new DashboardButton("src/main/resources/button-icons/checkout-icon.png"));
        add(new AddReservationButton(page, "src/main/resources/button-icons/add-reservation-icon.png"));


    }
}
