package edu.baylor.GroupFive.ui.utils;

import javax.swing.*;

import edu.baylor.GroupFive.models.enums.Privilege;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.accountSettings.AccountSettingsPanel;
import edu.baylor.GroupFive.ui.accountSettings.ChangePasswordPanel;
import edu.baylor.GroupFive.ui.createClerk.CreateClerkAccountPanel;
import edu.baylor.GroupFive.ui.createReservation.CreateReservationPanel;
import edu.baylor.GroupFive.ui.generateBill.GenerateBillPanel;
import edu.baylor.GroupFive.ui.homePanel.HomePanel;
import edu.baylor.GroupFive.ui.landing.LandingPage;
import edu.baylor.GroupFive.ui.modifyReservation.ModifyReservationPanel;
import edu.baylor.GroupFive.ui.modifyRoom.RoomsPanel;
import edu.baylor.GroupFive.ui.reservations.ReservationsPanel;
import edu.baylor.GroupFive.ui.reserveRoom.ReserveRoomPanel;
import edu.baylor.GroupFive.ui.resetPassword.ResetPasswordPanel;
import edu.baylor.GroupFive.ui.shop.ShopPanel;
import edu.baylor.GroupFive.ui.utils.dashboard.Dashboard;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Graphical User Interface page with dynamic content switching,
 * extending {@link edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate}.
 *
 * @author Brendon
 */
public class Page extends JFrame implements InputDelegate {

    private Dashboard dashboard;
    private List<String> info = new ArrayList<>();
    private User user;
    //private String acctNum;
    private Privilege privilege;
    
    public JPanel currentPanel;

    /**
     * Constructs a Page object with the specified user.
     *
     * @param user The user associated with the page.
     */
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

    /**
     * Creates the frame with the specified privilege level.
     *
     * @param privilege The privilege level of the user.
     */
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

    /**
     * Adds the dashboard to the page.
     *
     * @param page The page to add the dashboard to.
     */
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

    /**
     * Switches the page content based on the given option.
     *
     * @param option The option indicating the content to switch to.
     */
    public void onPageSwitch(String option) {
        
        if (currentPanel != null) {
            remove(currentPanel);
        }

        switch (option) {
            case "account-settings":
                currentPanel = new AccountSettingsPanel(this, user);
                break;
            case "password":
                currentPanel = new ChangePasswordPanel(this, user);
                break;
            case "home":
                currentPanel = new HomePanel(this, user);
                break;
            case "logout":
                dispose();
                @SuppressWarnings("unused")
                LandingPage page = new LandingPage();
                break;
            case "reset-password":
                currentPanel = new ResetPasswordPanel(this);
                break;
            case "shop":
                currentPanel = new ShopPanel(this, user);
                break;
//            case "inventory":
//                currentPanel = new InventoryPanel(this);
//                break;
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
            case "modify-rooms":
                currentPanel = new RoomsPanel(this);
                break;
            case "create-reservation":
                currentPanel = new CreateReservationPanel(this);
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
        refresh();
    }

    /**
     * Sets the user associated with the page.
     *
     * @param user The user to set.
     * */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Returns the user associated with the page.
     *
     * @return The user associated with the page.
     * */
    public User getUser() {
        return user;
    }

    /**
     * Adds information to the page.
     *
     * @param info The information to add.
     */
    public void addInfo(String info) {
        this.info.add(info);
    }

    /**
     * Returns the information stored in the page.
     *
     * @return The information stored in the page.
     */
    public List<String> getInfo() {
        return info;
    }

    public void clearInfo() {
        info.clear();
    }

    /**
     * Refreshes the page content.
     */
    public void refresh() {
        revalidate();
        repaint();
    }

}
