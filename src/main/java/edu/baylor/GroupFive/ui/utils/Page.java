package edu.baylor.GroupFive.ui.utils;

import javax.swing.*;

import edu.baylor.GroupFive.models.enums.Privilege;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.landing.LandingPage;
import edu.baylor.GroupFive.ui.modifyReservation.ModifyReservationPanel;
//import edu.baylor.GroupFive.model.Privilege;
import edu.baylor.GroupFive.ui.reservations.ReservationsPanel;
import edu.baylor.GroupFive.ui.reserveRoom.ReserveRoomPanel;
import edu.baylor.GroupFive.ui.utils.dashboard.Dashboard;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Page extends JFrame implements InputDelegate {

    private Dashboard dashboard;
    private List<String> info = new ArrayList<>();
    private GridBagConstraints constraints = new GridBagConstraints();
    private User user;
    //private String acctNum;
    private Privilege privilege;
    
    public JPanel currentPanel;
    
    public Page(User user) {
        super();

        // Set the user and get their privilege
        this.user = user;
        privilege = user.getPrivilege();

        // Create the frame
        createFrame(privilege);

        // Create the appropriate dashboard
        dashboard = new Dashboard(this, privilege);

        addDashboard();

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        currentPanel = new ReservationsPanel(this);
        add(currentPanel, constraints);
    }

    public void createFrame(Privilege privilege) {
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setVisible(true);

        if (privilege == Privilege.ADMIN) {
            getContentPane().setBackground(new Color(0xFEE4F4));
        } else if (privilege == Privilege.CLERK) {
            getContentPane().setBackground(new Color(0xe6f7ff));
        } else {
            getContentPane().setBackground(new Color(0xE6F7FF));
        }
    }

    public void addDashboard() {
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.anchor = GridBagConstraints.WEST;
        add(dashboard, constraints);
    }

    public void onPageSwitch(String option) {
        //todo: add logic for switching pages on dashboard button press
        remove(currentPanel);
        switch (option) {
            case "home":
                currentPanel = new ReservationsPanel(this);
                break;
            case "reservation":
                currentPanel = new ReserveRoomPanel(this);
                break;
            case "modifyReservation":
                currentPanel = new ModifyReservationPanel(this, info.get(0), info.get(1));
                break;
            case "logout":
                dispose();
                @SuppressWarnings("unused")
                LandingPage page = new LandingPage();
                break;
                /*
            case "view":
                currentPanel = new ReserveRoomPanel();
                break;
            case "cancel":
                currentPanel = new ReserveRoomPanel();
                break;
            case "admin":
                currentPanel = new ReserveRoomPanel();
                break;
            default:
                currentPanel = new ReserveRoomPanel();
                break;  */
        }
        add(currentPanel, constraints);
        revalidate();
        repaint();
    }

    public User getUser() {
        return user;
    }

    public void addInfo(String info) {
        this.info.add(info);
    }

    public List<String> getInfo() {
        return info;
    }


}
