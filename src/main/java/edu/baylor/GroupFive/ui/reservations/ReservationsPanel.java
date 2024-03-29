package edu.baylor.GroupFive.ui.reservations;

import javax.swing.*;
import javax.swing.table.*;

import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;

public class ReservationsPanel extends JPanel implements PagePanel {
    
    private JTable table;

    // Define column names
    private String[] columnNames = { "Reservation ID",
            "Room ID",
            "Start Date",
            "End Date",
            "Guest ID",
            "Price"};

    // Define data types for the columns
    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, String.class, String.class, String.class, String.class
    };

    public ReservationsPanel() {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create a model of the data.
        DefaultTableModel model = new ReservationModel(columnNames, columnClass);

        // Create a table with a sorter.
        table = new HotelTable(model);

        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to this panel.
        add(scrollPane);

        // Add the form pane
        add(new FormPane(table, ((HotelTable)table).getSorter(), columnNames));

        // Add the button panel
        addButtonPanel();
        
        // Set the panel properties
        setVisible(true);

    }

    private void addButtonPanel() {
        // Create button panel
        JPanel buttonPanel = new JPanel();

        // Create buttons
        JButton viewReservation = new JButton("View Selected Reservation");
        JButton viewRoom = new JButton("View Selected Room");

        // Add buttons to panel
        addButtonListeners(viewReservation, viewRoom);
        buttonPanel.add(viewReservation);
        buttonPanel.add(viewRoom);

        add(buttonPanel);
    }

    private void addButtonListeners(JButton viewReservation, JButton viewRoom) {
        viewReservation.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                String name = (String) table.getValueAt(row, 0);
                String startDate = (String) table.getValueAt(row, 1);
                String endDate = (String) table.getValueAt(row, 2);
                String room = (String) table.getValueAt(row, 3);
                JOptionPane.showMessageDialog(null, "Name: " + name + "\nStart Date: " + startDate + "\nEnd Date: " + endDate + "\nRoom: " + room);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a reservation to view.");
            }
        });

        viewRoom.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                String room = (String) table.getValueAt(row, 3);
                JOptionPane.showMessageDialog(null, "Room: " + room);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a reservation to view.");
            }
        });
    }

    @Override
    public void clear() {
        // Do nothing
    }
}
