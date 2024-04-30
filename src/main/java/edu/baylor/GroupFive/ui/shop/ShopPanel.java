package edu.baylor.GroupFive.ui.shop;

import javax.swing.*;

import edu.baylor.GroupFive.database.controllers.StockController;
import edu.baylor.GroupFive.models.Stock;
import edu.baylor.GroupFive.ui.shop.dialogs.AddToCartDialog;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;
import edu.baylor.GroupFive.util.logging.G5Logger;

import java.awt.*;

/**
 * Panel for the shop.
 * 
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @author Brendon
 * @author Siri
 */
public class ShopPanel extends JPanel implements PagePanel {


    private Page page;
    private HotelTable table;
    private AddShopModel model;
    //private ProductTable table;

    private String[] columnNames = {
            "Product ID",
            "Description",
            "Cost",
            "# In Stock"
    };

    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, String.class, Integer.class
    };


    /**
     * Constructs a new ShopPanel with the specified delegate
     *
     * // @param delegate Page
     */
    public ShopPanel(Page page) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.page = page;

        // Create a model of the data.
        model = new AddShopModel(columnNames, columnClass);

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
            AddToCartDialog dialog = new AddToCartDialog(table);
            dialog.setVisible(true);
        });
        add(addToCartButton);
//
//        PanelButton deleteRoomButton = new PanelButton("Delete Room");
//        deleteRoomButton.addActionListener(e -> {
//            int row = table.getSelectedRow();
//            if (row == -1) {
//                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
//                return;
//            }
//
//            int roomNumber = (Integer) table.getValueAt(row, 0);
//            Integer choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete room " + roomNumber + "?", "Delete Room", JOptionPane.YES_NO_OPTION);
//
//            if (choice != JOptionPane.YES_OPTION) {
//                return;
//            }
//
//            Boolean result = RoomController.deleteRoom(roomNumber);
//
//            if (result) {
//                model.getData();
//            } else {
//                JOptionPane.showMessageDialog(this, "Failed to delete room.");
//            }
//        });
//
//        PanelButton editRoomButton = new PanelButton("Edit Room");
//        editRoomButton.addActionListener(e -> {
//
//            // Ensure row is selected
//            if (table.getSelectedRow() == -1) {
//                JOptionPane.showMessageDialog(this, "Please select a row to edit.");
//                return;
//            }
//
//            // Get the room number from the selected row
//            Integer roomColumnIndex = table.getColumnModel().getColumnIndex("Room Number");
//            int roomNumber = (Integer) table.getValueAt(table.getSelectedRow(), roomColumnIndex);
//
//            Room room = RoomController.getRoomInfo(roomNumber);
//
//            // Check if room number exists in database
//            if (room == null) {
//                G5Logger.logger.error("Room number does not exist in database");
//                JOptionPane.showMessageDialog(this, "Room number does not exist in database.");
//                return;
//            }
//
//            // Add the edit room panel to the page
//            page.remove(page.currentPanel);
//            page.currentPanel = new EditRoomPanel(page, room);
//            page.add(page.currentPanel);
//            page.refresh();
//        });
//
//        // Add the buttons to the button panel
//        buttonPanel.add(addRoomButton);
//        buttonPanel.add(deleteRoomButton);
//        buttonPanel.add(editRoomButton);
//
//        // Add the button panel to this panel.
//        add(buttonPanel);

    }

    /**
     * Clears the panel (but does nothing atm).
     */
    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

}
