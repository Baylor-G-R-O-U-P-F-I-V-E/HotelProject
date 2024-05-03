package edu.baylor.GroupFive.ui.generateBill;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;

import javax.swing.table.DefaultTableModel;

import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

/**
 * Panel for generating bills for hotel guests.
 * This panel displays a table of current guests and provides a button
 * to generate a bill for a selected guest.
 *
 * What does this need to have?
 * - A table that shows a list of current guests
 * - A button to generate a bill for a guest
 *
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @author Brendon
 */
public class GenerateBillPanel extends JPanel implements PagePanel {

    private JTable table;
    private Page page;
    private JPanel buttonPanel;

    /** Define column names */
    private String[] columnNames = {
            "First Name",
            "Last Name",
            "Start Date",
            "End Date",
            "Guest ID",
            "Room Number"};

    /**
     * Define data types for the columns
     */
    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, String.class, String.class, String.class, String.class
    };

    /**
     * Constructs a GeneratedBillPanel with the specified page.
     *
     * @param page The page associated with this panel.
     */
    public GenerateBillPanel(Page page) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.page = page;

        // Create a model of the data.
        DefaultTableModel model = new GenerateBillModel(columnNames, columnClass);

        // Create a table with a sorter.
        table = new HotelTable(model);

        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        // Add some glue
        add(Box.createVerticalGlue());

        // Add the title
        JLabel titleLabel = new JLabel("Guests With Outstanding Bills");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        add(titleLabel);

        // Add some glue
        add(Box.createVerticalGlue());

        // Add the scroll pane to this panel.
        add(scrollPane);

        // Add the form pane
        FormPane formPane = new FormPane(table, ((HotelTable) table).getSorter(), columnNames);
        add(formPane);

        // Add the button panel
        buttonPanel = new JPanel();
        addGenerateSelectedGuestBillButton(buttonPanel);
        add(buttonPanel);

        // Add some more glue
        add(Box.createVerticalGlue());

    }

    /**
     * Adds a button to generate a bill for the selected guest to the specified panel.
     *
     * @param panel The panel to which the button will be added.
     */
    public void addGenerateSelectedGuestBillButton(JPanel panel) {
        PanelButton generateButton = new PanelButton("Generate Bill for Selected Guest");
        
        // Needs to be a little bigger to accoomodate the text
        generateButton.setPreferredSize(new Dimension(400, 50));

        generateButton.addActionListener(e -> {

            if (getSelectedGuest() == null) {
                JOptionPane.showMessageDialog(null, "Please select a guest to generate a bill for.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Destroy the current panel
            page.remove(page.currentPanel);
            page.currentPanel = new GuestBillPanel(page, getSelectedGuest());
            page.add(page.currentPanel, BorderLayout.CENTER);
            page.refresh();
        });

        panel.add(generateButton);
    }

    /**
     * Retrieves the guest ID of the selected guest from the table.
     *
     * @return The guest ID of the selected guest, or {@code null} if no guest is selected.
     */
    public String getSelectedGuest() {
        // Get the selected row
        int row = table.getSelectedRow();
        if (row == -1) {
            return null;
        }

        // Get the guest ID
        int columnIndex = ((DefaultTableModel) table.getModel()).findColumn("Guest ID");

        System.out.println("User ID: " + table.getValueAt(row, columnIndex));
        
        return (String) table.getValueAt(row, columnIndex);
    }

    /**
     * TODO Auto-generated method stub
     * */
    @Override
    public void clear() {}

}

