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

import edu.baylor.GroupFive.ui.generateBill.dialogs.PayBillDialog;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;

/**
 * Panel for displaying a bill for a specific guest.
 * This panel lists the items purchased by the guest,
 * the total amount for the bill, and provides a button to print the bill.
 *
 * What does this need to have?
 * - A list of the items purchased by the guest
 * - A total amount for the bill
 * - A button to print the bill
 *
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @author Brendon
 */
public class GuestBillPanel extends JPanel implements PagePanel {

    private JTable table;
    private JPanel buttonPanel;
    private Page page;

    private String[] columnNames = {
            "TransactionID",
            "Item",
            "Price",
            "Purchase Date"
    };

    // Define data types for the columns
    final Class<?>[] columnClass = new Class[] {
            Integer.class, String.class, String.class, String.class
    };

    /**
     * Constructs a GuestBillPanel with the specified page and username.
     *
     * @param page The page associated with this panel.
     * @param username The username of the guest.
     */
    public GuestBillPanel(Page page, String username) {
        super();

        this.page = page;

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
        addButtons();
        add(buttonPanel);
    }

    public double getSubtotal(){
        double subTotal = 0;
        for(int row = 0; row < table.getRowCount(); row++){
            double unitPrice = Double.parseDouble((String) table.getValueAt(row, 2));
            subTotal += unitPrice;
        }
        return subTotal;
    }
    /**
     * Adds a back button to the panel.
     */
    public void addButtons() {
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

        PanelButton payBillButton = new PanelButton("Pay Bill");
        payBillButton.addActionListener(e -> {
            PayBillDialog dialog = new PayBillDialog(this, table);
            dialog.setVisible(true);
        });
        buttonPanel.add(payBillButton);
    }

    /**
     * TODO Auto-generated method stub
     */
    @Override
    public void clear() {}

}
