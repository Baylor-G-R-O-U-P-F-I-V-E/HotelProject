package edu.baylor.GroupFive.ui.reservations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.*;

import edu.baylor.GroupFive.controllers.ReservationController;
import edu.baylor.GroupFive.controllers.RoomController;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;

public class ReservationsPanel extends JPanel implements PagePanel {
    
    private JTable table;
    private Page page;

    // Define column names
    private String[] columnNames = {
            "Room ID",
            "Start Date",
            "End Date",
            "Guest ID",
            "Price"};

    // Define data types for the columns
    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, String.class, String.class, String.class, String.class
    };

    public ReservationsPanel(Page page) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.page = page;

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
        JButton modifyReservation = new JButton("Modify Selected Reservation");
        JButton viewRoom = new JButton("View Selected Room");
        JButton deleteReservation = new JButton("Delete Selected Reservation");

        // Add buttons to panel
        addButtonListeners(modifyReservation, viewRoom, deleteReservation);
        buttonPanel.add(modifyReservation);
        buttonPanel.add(viewRoom);
        buttonPanel.add(deleteReservation);

        add(buttonPanel);
    }

    private void addButtonListeners(JButton viewReservation, JButton viewRoom, JButton deleteReservation) {
        viewReservation.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Integer roomColumnIndex = table.getColumnModel().getColumnIndex("Room ID");
                String roomID = (String) table.getValueAt(row, roomColumnIndex);
                Integer startDateColumnIndex = table.getColumnModel().getColumnIndex("Start Date");
                String startDate = (String) table.getValueAt(row, startDateColumnIndex);

                page.addInfo(roomID);
                page.addInfo(startDate);

                page.onPageSwitch("modifyReservation");

            } else {
                JOptionPane.showMessageDialog(null, "Please select a reservation to view.");
            }
        });

        viewRoom.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                int roomColumnIndex = table.getColumnModel().getColumnIndex("Room ID");
                Integer roomNumber = Integer.parseInt((String) table.getValueAt(row, roomColumnIndex));
                Room room = RoomController.getRoomInfo(roomNumber);
                JOptionPane.showMessageDialog(null, room.toString());
            } else {
                JOptionPane.showMessageDialog(null, "Please select a reservation to view.");
            }
        });

        deleteReservation.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Integer roomColumnIndex = table.getColumnModel().getColumnIndex("Room ID");
                String roomID = (String) table.getValueAt(row, roomColumnIndex);
                Integer startDateColumnIndex = table.getColumnModel().getColumnIndex("Start Date");
                String startDate = (String) table.getValueAt(row, startDateColumnIndex);

                // Parse the startDate from a string to a Date object
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date parsedDate = null;
                try {
                    parsedDate = dateFormat.parse(startDate);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                if (parsedDate == null) {
                    JOptionPane.showMessageDialog(null, "Error parsing date.");
                    return;
                }

                ReservationController.cancelReservation(Integer.parseInt(roomID), parsedDate);
                ((DefaultTableModel)table.getModel()).removeRow(row);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a reservation to delete.");
            }
        });
    }

    @Override
    public void clear() {
        // Do nothing
    }
}
