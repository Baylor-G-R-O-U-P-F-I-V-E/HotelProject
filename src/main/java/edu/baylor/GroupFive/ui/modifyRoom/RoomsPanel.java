package edu.baylor.GroupFive.ui.modifyRoom;

import java.awt.Font;
import java.awt.Panel;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.baylor.GroupFive.database.controllers.RoomController;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.ui.modifyRoom.dialogs.AddRoomDialog;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;
import edu.baylor.GroupFive.util.logging.G5Logger;

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

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();

        PanelButton addRoomButton = new PanelButton("Add Room");
        addRoomButton.addActionListener(e -> {
            new AddRoomDialog(table);
        });

        PanelButton deleteRoomButton = new PanelButton("Delete Room");
        deleteRoomButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Please select a row to delete.");
                return;
            }
            
            int roomNumber = Integer.parseInt((String) table.getValueAt(row, 0));
            Integer choice = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete room " + roomNumber + "?", "Delete Room", JOptionPane.YES_NO_OPTION);
            
            if (choice != JOptionPane.YES_OPTION) {
                return;
            }
            
            Boolean result = RoomController.deleteRoom(roomNumber);

            if (result) {
                model.getData();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete room.");
            }
        });

        PanelButton editRoomButton = new PanelButton("Edit Room");
        editRoomButton.addActionListener(e -> {
            int roomNumber = Integer.parseInt((String) table.getValueAt(table.getSelectedRow(), 0));

            if (roomNumber == -1) {
                G5Logger.logger.error("Room number is -1");
                return;
            } else if (RoomController.getRoomInfo(roomNumber) == null) {
                G5Logger.logger.error("Room number does not exist in database");
                JOptionPane.showMessageDialog(this, "Room number does not exist in database.");
                return;
            }

            page.add(new EditRoomPanel(page, roomNumber));
        });

        buttonPanel.add(addRoomButton);
        buttonPanel.add(deleteRoomButton);
        buttonPanel.add(editRoomButton);

        // Add the button panel to this panel.
        add(buttonPanel);

    }


    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}
