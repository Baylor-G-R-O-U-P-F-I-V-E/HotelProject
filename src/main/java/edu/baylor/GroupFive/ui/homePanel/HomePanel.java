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

import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;
import edu.baylor.GroupFive.ui.reservations.ReservationModel;

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
        "Guest ID",
        "Price"};

    // Define data types for the columns
    private final Class<?>[] columnClass = new Class[] {
        String.class, String.class, String.class, String.class, String.class, String.class
    };

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
        DefaultTableModel model = new ReservationModel(columnNames, columnClass);

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

    public void addShopButton(JPanel buttonPanel) {
        
        // Add a button to the panel
        JButton shopButton = new JButton("Shop");

        // Style the button
        shopButton.setPreferredSize(new Dimension(200, 50));
        shopButton.setFont(new Font("Arial", Font.PLAIN, 22));
        shopButton.setOpaque(true);
        shopButton.setBorderPainted(false);
        shopButton.setBackground(new Color(0, 0, 153));
        shopButton.setForeground(new Color(255, 255, 255));

        shopButton.addActionListener(e -> page.onPageSwitch("shop"));

        buttonPanel.add(shopButton);
    }

    public void addFindRoomsButton(JPanel buttonPanel) {
        
        // Add a button to the panel
        JButton findRoomsButton = new JButton("Find Rooms");

        // Style the button
        findRoomsButton.setPreferredSize(new Dimension(200, 50));
        findRoomsButton.setFont(new Font("Arial", Font.PLAIN, 22));
        findRoomsButton.setOpaque(true);
        findRoomsButton.setBorderPainted(false);
        findRoomsButton.setBackground(new Color(0, 0, 153));
        findRoomsButton.setForeground(new Color(255, 255, 255));

        findRoomsButton.addActionListener(e -> page.onPageSwitch("find-rooms"));

        buttonPanel.add(findRoomsButton);
    }

    public void filterTable(JTable table) {
        // Filter the table for reservations that include the users username

    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

}
