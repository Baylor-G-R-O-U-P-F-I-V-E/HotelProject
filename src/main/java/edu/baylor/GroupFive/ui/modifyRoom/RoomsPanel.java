package edu.baylor.GroupFive.ui.modifyRoom;

import java.awt.Font;
import java.awt.Panel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.database.controllers.RoomController;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.ui.modifyRoom.dialogs.AddRoomDialog;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;
import edu.baylor.GroupFive.util.logging.G5Logger;

/**
 * Panel for displaying the rooms in the hotel.
 * This panel includes a table of rooms and buttons to add, delete, and edit rooms.
 * 
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 * 
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @see edu.baylor.GroupFive.ui.utils.Page
 * @see edu.baylor.GroupFive.ui.utils.table.HotelTable
 * @see edu.baylor.GroupFive.ui.utils.table.FormPane
 * @see edu.baylor.GroupFive.ui.modifyRoom.dialogs.AddRoomDialog
 * @see edu.baylor.GroupFive.ui.modifyRoom.EditRoomPanel
 * 
 * @author Brendon
 */
public class RoomsPanel extends JPanel implements PagePanel {

    private Page page;
    private HotelTable table;
    private RoomsModel model;

    private String[] columnNames = {
            "Room Number",
            "Room Type",
            "Quality",
            "Bed Type",
            "Bed Count",
            "Smoking",
            "Price per Night",
    };

    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, String.class, String.class, String.class, String.class, String.class
    };

    /**
     * Constructs a RoomsPanel with the specified page.
     *
     * @param page The page containing this panel.
     */
    public RoomsPanel(Page page) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.page = page;

        // Create a model of the data.
        model = new RoomsModel(columnNames, columnClass);

        // Create a table with a sorter.
        table = new HotelTable(model);

        // Add the table to a scroll pane.
        JScrollPane scrollPane = new JScrollPane(table);

        // Add some glue
        add(Box.createVerticalGlue());

        // Add a title
        JLabel title = new JLabel("Rooms");
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

    /**
     * Adds a panel of buttons to the page.
     */
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();

        PanelButton addRoomButton = new PanelButton("Add Room");
        addRoomButton.addActionListener(e -> {
            // Show the dialog to add a room.
            AddRoomDialog dialog = new AddRoomDialog(table);
            dialog.setVisible(true);
        });

        PanelButton deleteRoomButton = new PanelButton("Delete Room");
        deleteRoomButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
                return;
            }
            
            int roomNumber = (Integer) table.getValueAt(row, 0);
            Integer choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete room " + roomNumber + "?", "Delete Room", JOptionPane.YES_NO_OPTION);
            
            if (choice != JOptionPane.YES_OPTION) {
                return;
            }

            // Check if the room is in use
            if (ReservationController.getRoomReservations(roomNumber).size() > 0) {
                JOptionPane.showMessageDialog(this, "Room is in use and cannot be deleted.");
                return;
            }
            
            Boolean result = RoomController.deleteRoom(roomNumber);

            if (result) {
                JOptionPane.showMessageDialog(this, "Room deleted successfully.");
                model.getData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete room.");
            }
        });

        PanelButton editRoomButton = new PanelButton("Edit Room");
        editRoomButton.addActionListener(e -> {
            
            // Ensure row is selected
            if (table.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to edit.");
                return;
            }  

            // Get the room number from the selected row
            Integer roomColumnIndex = table.getColumnModel().getColumnIndex("Room Number");
            int roomNumber = ((Integer) table.getValueAt(table.getSelectedRow(), roomColumnIndex)).intValue();
            
            Room room = RoomController.getRoomInfo(roomNumber);

            // Check if room number exists in database
            if (room == null) {
                G5Logger.logger.error("Room number does not exist in database");
                JOptionPane.showMessageDialog(this, "Room number does not exist in database.");
                return;
            }

            // Add the edit room panel to the page
            page.remove(page.currentPanel);
            page.currentPanel = new EditRoomPanel(page, room);
            page.add(page.currentPanel);
            page.refresh();
        });

        // Add the buttons to the button panel
        buttonPanel.add(addRoomButton);
        buttonPanel.add(deleteRoomButton);
        buttonPanel.add(editRoomButton);

        // Add the button panel to this panel.
        add(buttonPanel);

    }

    /**
     * Refreshes the data in the table.
     */
    @Override
    public void clear() {
        model.getData();
    }
}
