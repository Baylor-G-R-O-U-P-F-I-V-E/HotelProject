package edu.baylor.GroupFive.ui.utils;

import javax.swing.*;

import edu.baylor.GroupFive.models.Privilege;
import edu.baylor.GroupFive.models.User;
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
        //Init new frame
        createFrame();
        this.user = user;

        if (user == null) {
            dashboard = new Dashboard(this, Privilege.ADMIN);
        } else {
            privilege = user.getPrivilege();
            dashboard = new Dashboard(this, privilege);
        }
        //privilege = AccountController.getAccountPrivilege(user);

        addDashboard();

        constraints.gridx = 1;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        currentPanel = new ReservationsPanel(this);
        add(currentPanel, constraints);
    }

    public void createFrame() {
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(0xe6f7ff));
        setLayout(new GridBagLayout());
        setVisible(true);
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
