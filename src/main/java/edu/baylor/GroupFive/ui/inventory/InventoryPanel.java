package edu.baylor.GroupFive.ui.inventory;

import javax.swing.*;

import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;

import java.awt.*;

/**
 * Panel for the inventory.
 *
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @author Siri
 */
public class InventoryPanel extends JPanel implements PagePanel {

    private Page page;
    private HotelTable table;
    private AddInventoryModel model;

    // ProductID, Name, Cost, Stock

    private String[] columnNames = {
            "Product ID",
            "Name",
            "Cost",
            "Stock",
    };

    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, String.class, String.class
    };


    /**
     * Constructs a new ShopPanel with the specified delegate
     *
     * // @param delegate Page
     */
    public InventoryPanel(Page page) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.page = page;

        // Create a model of the data.
        model = new AddInventoryModel(columnNames, columnClass);

        // Create a table with a sorter.
        table = new HotelTable(model);

        // Add the table to a scroll pane.
        JScrollPane scrollPane = new JScrollPane(table);

        // Add some glue
        add(Box.createVerticalGlue());

        // Add a title
        JLabel title = new JLabel("Products");
        title.setFont(new java.awt.Font("Arial", Font.BOLD, 36));
        title.setAlignmentX(Panel.CENTER_ALIGNMENT);
        add(title);

        // Add some glue
        add(Box.createVerticalGlue());

        // Add the scroll pane to this panel.
        add(scrollPane);

        // Add the form pane
        add(new FormPane(table, table.getSorter(), columnNames));

        // Add the button panel.
        addButtonPanel();

        // Add some glue
        add(Box.createVerticalGlue());

    }

    // TODO: Implement AddToCartDialog first - Siri
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();

        PanelButton addToCartButton = new PanelButton("Add To Cart");
        addToCartButton.addActionListener(e -> {
            // Show the dialog to add a room.
//            AddRoomDialog dialog = new AddRoomDialog(table);
//            dialog.setVisible(true);
        });


    }

    /**
     * Clears the panel (but does nothing atm).
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

}
