package edu.baylor.GroupFive.ui.modifyRoom;

import java.awt.Panel;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;

public class RoomsPanel extends JPanel implements PagePanel {

    private Page page;
    private JTable table;
    private HotelModel model;

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
        table = new JTable(model);

        // Add the table to a scroll pane.
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to this panel.
        add(scrollPane);

        // Add the form pane
        add(new FormPane(table, ((HotelTable)table).getSorter(), columnNames));

        // Add the button panel.
        addButtonPanel();

    }

    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        PanelButton addRoomButton = new PanelButton("Add Room");
        addRoomButton.addActionListener(e -> {
            // Create a Jdialog that prompts the user for the room information.


            
        });

        

        PanelButton deleteRoomButton = new PanelButton("Delete Room");
        deleteRoomButton.addActionListener(e -> {
            page.addPanel(new DeleteRoomPanel(page));
        });

        PanelButton editRoomButton = new PanelButton("Edit Room");
        editRoomButton.addActionListener(e -> {
            page.addPanel(new EditRoomPanel(page));
        });

        // Add the button panel to this panel.
        add(buttonPanel);

    }


    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}
