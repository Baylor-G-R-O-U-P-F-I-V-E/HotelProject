package edu.baylor.GroupFive.ui.homePanel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;

import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;
import edu.baylor.GroupFive.ui.reservations.ReservationModel;

/**
 * Panel for displaying the home screen.
 *
 * This panel includes a title label and list of reservations for the user.
 *
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 *
 * @param page the page containing this panel.
 * @param user the user for whome the panel is displayed.
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @author Brendon
 */
public class HomePanel extends JPanel implements PagePanel {

    /*
     * What it needs:
     * 
     * - A title label
     * - A list of reservations
     */

    private Page page;
    private User user;
    private JTable reservationsTable;

    // Define column names
    private String[] columnNames = {
        "Room ID",
        "Start Date",
        "End Date",
        "Price"};

    // Define data types for the columns
    private final Class<?>[] columnClass = new Class[] {
        String.class, String.class, String.class, String.class, String.class
    };

    /**
     * Constructs a HomePanel with the specified page and user.
     *
     * @param page_ the page containing this panel.
     * @param user_ the user for whom the panel is displayed.
     * @author Brendon
     */
    public HomePanel(Page page_, User user_) {
        super();

        this.page = page_;
        this.user = user_;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setVisible(true);
    
        setOpaque(true);
    
        setBackground(page.getContentPane().getBackground());
    
        JLabel titleLabel = new JLabel("Welcome " + user.getFirstName() + "!");
        titleLabel.setAlignmentX(Box.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 52));
    
        add(Box.createVerticalGlue());
        add(titleLabel);
        add(Box.createVerticalGlue());

        // Create a model of the data.
        DefaultTableModel model = new HomeModel(columnNames, columnClass, page.getUser());

        // Create a table with a sorter.
        reservationsTable = new HotelTable(model);

        // Add label above table to show what the table is
        JLabel tableLabel = new JLabel("Your Reservations");
        tableLabel.setAlignmentX(Box.CENTER_ALIGNMENT);
        tableLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(tableLabel);

        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(reservationsTable);

        // Add the scroll pane to this panel.
        add(scrollPane);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setVisible(true);
        buttonPanel.setOpaque(false);

        addShopButton(buttonPanel);
        addFindRoomsButton(buttonPanel);
    
        add(buttonPanel);
        add(Box.createVerticalGlue());
    }

    /**
     * Adds a "Shop" button to the specified button panel.
     *
     * @param buttonPanel The panel to which the button will be added.
     * @author Brendon
     */
    public void addShopButton(JPanel buttonPanel) {
        
        // Add a button to the panel
        PanelButton shopButton = new PanelButton("Shop");

        shopButton.addActionListener(e -> page.onPageSwitch("shop"));

        buttonPanel.add(shopButton);
    }

    /**
     * Adds a "Find Rooms button to the specified button panel.
     *
     * @param buttonPanel The panel to which the button will be added.
     * @author Brendon
     */
    public void addFindRoomsButton(JPanel buttonPanel) {
        
        // Add a button to the panel
        PanelButton findRoomsButton = new PanelButton("Find Rooms");

        findRoomsButton.addActionListener(e -> page.onPageSwitch("find-rooms"));

        buttonPanel.add(findRoomsButton);
    }

    /**
     * Filters the table for reservations that include the users username
     *
     * @param table
     * @author Brendon
     */
    public void filterTable(JTable table) {
        // TODO Filter the table for reservations that include the users username

    }

    /**
     * Clears the panel.
     *
     * @author Intellij
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

}
