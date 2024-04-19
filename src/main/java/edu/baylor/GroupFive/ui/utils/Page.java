package edu.baylor.GroupFive.ui.utils;

import javax.swing.*;

import edu.baylor.GroupFive.models.enums.Privilege;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.accountSettings.AccountSettingsPanel;
import edu.baylor.GroupFive.ui.createClerk.CreateClerkAccountPanel;
import edu.baylor.GroupFive.ui.generateBill.GenerateBillPanel;
import edu.baylor.GroupFive.ui.homePanel.HomePanel;
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

        addDashboard(this);

        onPageSwitch("home");

        add(currentPanel, BorderLayout.CENTER);
    }

    public void createFrame(Privilege privilege) {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setVisible(true);

        if (privilege == Privilege.ADMIN) {
            getContentPane().setBackground(new Color(0xFEE4F4));
        } else if (privilege == Privilege.CLERK) {
            getContentPane().setBackground(new Color(0xe6f7ff));
        } else {
            getContentPane().setBackground(new Color(0xE6F7FF));
        }
    }

    public void addDashboard(Page page) {
    
        // Create a scroll pane for the dashboard
        JScrollPane pane = new JScrollPane(dashboard);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
    
        // Increase the sensitivity of the scrollbar
        JScrollBar verticalScrollBar = pane.getVerticalScrollBar();
        verticalScrollBar.setUnitIncrement(16); // Decrease this value to increase sensitivity
    
        add(pane, BorderLayout.WEST);
    }

    public void onPageSwitch(String option) {
        
        if (currentPanel != null) {
            remove(currentPanel);
        }

        switch (option) {
            case "account-settings":
                currentPanel = new AccountSettingsPanel(this, user);
                break;
            case "home":
                currentPanel = new HomePanel(this, user);
                break;
            case "logout":
                dispose();
                @SuppressWarnings("unused")
                LandingPage page = new LandingPage();
                break;
            case "reservations":
                currentPanel = new ReservationsPanel(this);
                break;
            case "modifyReservation":
                currentPanel = new ModifyReservationPanel(this, info.get(0), info.get(1));
                break;
            case "create-clerk":
                currentPanel = new CreateClerkAccountPanel(this);
                break;
            case "find-rooms":
                currentPanel = new ReserveRoomPanel(this);
                break;
            case "generate-bill":
                currentPanel = new GenerateBillPanel(this);
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
        add(currentPanel, BorderLayout.CENTER);
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
