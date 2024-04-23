package edu.baylor.GroupFive.ui.generateBill;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;

public class GuestBillPanel extends JPanel implements PagePanel {

    /*
     * What does this need to have?
     * 
     * A list of the items purchased by the guest
     * A total amount for the bill
     * A button to print the bill
     * 
     */

    private JTable table;
    private JPanel buttonPanel;
    private Page page;
    private String username;

    private String[] columnNames = {
            "Item",
            "Price",
            "Purchase Date"
    };

    // Define data types for the columns
    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, String.class
    };

    public GuestBillPanel(Page page, String username) {
        super();

        this.page = page;
        this.username = username;

        // Set the layout of the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create a model of the data.
        DefaultTableModel model = new GuestBillModel(columnNames, columnClass, username);

        // Create a table with a sorter.
        table = new HotelTable(model);

        // Add the table to a scroll pane.
        JScrollPane scrollPane = new JScrollPane(table);

        // Create a title
        JLabel title = new JLabel("Guest Bill");
        title.setFont(new java.awt.Font("Arial", Font.BOLD, 36));
        title.setAlignmentX(CENTER_ALIGNMENT);

        // Add some glue
        add(Box.createVerticalGlue());

        // Add the title
        add(title);

        // Add some glue
        add(Box.createVerticalGlue());

        // Add the scroll pane to this panel.
        add(scrollPane);

        // Add button panel
        buttonPanel = new JPanel();
        addBackButton();
        add(buttonPanel);
    }

    public void addBackButton() {
        // Add back button
        PanelButton backButton = new PanelButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 // Go back to the previous page
                 page.onPageSwitch("generate-bill");
            }
        });
        buttonPanel.add(backButton);
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

}
