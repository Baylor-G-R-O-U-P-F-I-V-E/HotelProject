package edu.baylor.GroupFive.ui.homePanel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.util.Date;

import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;

/**
 * Panel for displaying the home screen.
 *
 * This panel includes a title label and list of reservations for the user.
 *
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @author Brendon
 */
public class HomePanel extends JPanel implements PagePanel {

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
        addModifyReservationButton(buttonPanel);
    
        add(buttonPanel);
        add(Box.createVerticalGlue());
    }

    /**
     * Adds a "Shop" button to the specified button panel.
     *
     * @param buttonPanel The panel to which the button will be added.
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
     */
    public void addFindRoomsButton(JPanel buttonPanel) {
        
        // Add a button to the panel
        PanelButton findRoomsButton = new PanelButton("Make a Reservation");

        findRoomsButton.addActionListener(e -> page.onPageSwitch("find-rooms"));

        buttonPanel.add(findRoomsButton);
    }

    /**
     * Adds a "Modify Reservation" button to the specified button panel.
     *
     * @param buttonPanel The panel to which the button will be added.
     */
    public void addModifyReservationButton(JPanel buttonPanel) {
        
        // Add a button to the panel
        PanelButton modifyReservationButton = new PanelButton("Modify Reservation");

        modifyReservationButton.addActionListener(e -> {
            if (reservationsTable.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Please select a reservation to modify.");
                return;
            }

            // Get the selected rows reservation
            int selectedRow = reservationsTable.getSelectedRow();

            Reservation reservation;

            try {
                reservation = ((HomeModel) reservationsTable.getModel()).getReservation(selectedRow);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error fetching reservation data.");
                return;
            }

            if (reservation == null) {
                JOptionPane.showMessageDialog(this, "Error fetching reservation data.");
                return;
            }

            Date start = reservation.getStartDate();

            // Check if the reservation has already started
            if (start.before(new Date())) {
                JOptionPane.showMessageDialog(this, "This reservation has already started and cannot be modified.");
                return;
            }

            // If reservation is within 48 hours, do not allow modification
            if (start.getTime() - new Date().getTime() < 48 * 60 * 60 * 1000) {
                JOptionPane.showMessageDialog(this, "This reservation is within 48 hours and cannot be modified. Please see a clerk for assistance.");
                return;
            }

            new ModifyReservationListener(page, user, reservation);
            ((HomeModel) reservationsTable.getModel()).refreshData();
        });

        buttonPanel.add(modifyReservationButton);
    }

    /**
     * Clears the panel.
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

}
