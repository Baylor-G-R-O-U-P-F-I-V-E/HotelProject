package edu.baylor.GroupFive.ui.reservations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.*;

import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.database.controllers.RoomController;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;

import java.awt.*;

/**
 *
 */
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

    /**
     *
     * @param page
     */
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

        // Add some glue
        add(Box.createVerticalGlue());

        // Add a title
        JLabel title = new JLabel("Reservations");
        title.setFont(new java.awt.Font("Arial", Font.BOLD, 36));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);

        // Add some glue
        add(Box.createVerticalGlue());

        // Add the scroll pane to this panel.
        add(scrollPane);

        // Add the form pane
        add(new FormPane(table, ((HotelTable)table).getSorter(), columnNames));

        // Add the button panel
        addButtonPanel();
        
        // Set the panel properties
        setVisible(true);

    }

    /**
     *
     */
    private void addButtonPanel() {
        // Create button panel
        JPanel buttonPanel = new JPanel();

        // Create buttons
        PanelButton modifyReservation = new PanelButton("Modify Selected Reservation");
        PanelButton viewRoom = new PanelButton("View Selected Room");
        PanelButton deleteReservation = new PanelButton("Delete Selected Reservation");

        // Add buttons to panel
        addButtonListeners(modifyReservation, viewRoom, deleteReservation);
        buttonPanel.add(modifyReservation);
        buttonPanel.add(viewRoom);
        buttonPanel.add(deleteReservation);

        add(buttonPanel);
    }

    /**
     *
     * @param viewReservation
     * @param viewRoom
     * @param deleteReservation
     */
    private void addButtonListeners(JButton viewReservation, JButton viewRoom, JButton deleteReservation) {
        viewReservation.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Integer roomColumnIndex = table.getColumnModel().getColumnIndex("Room ID");
                String roomID = (String) table.getValueAt(row, roomColumnIndex);
                Integer startDateColumnIndex = table.getColumnModel().getColumnIndex("Start Date");
                Integer endDateColumnIndex = table.getColumnModel().getColumnIndex("End Date");
                String startDate = (String) table.getValueAt(row, startDateColumnIndex);
                String endDate = (String) table.getValueAt(row, endDateColumnIndex);

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
                Integer endDateColumnIndex = table.getColumnModel().getColumnIndex("End Date");
                String startDate = (String) table.getValueAt(row, startDateColumnIndex);
                String endDate = (String) table.getValueAt(row, endDateColumnIndex);

                // Parse the startDate from a string to a Date object
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date parsedStartDate = null;
                Date parsedEndDate = null;
                try {
                    parsedStartDate = dateFormat.parse(startDate);
                    parsedEndDate = dateFormat.parse(startDate);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                if (parsedStartDate == null || parsedEndDate == null) {
                    JOptionPane.showMessageDialog(null, "Error parsing date.");
                    return;
                }

                // FIXME cancelReservation now takes in a Reservation object
                // ReservationController.cancelReservation(Integer.parseInt(roomID), parsedStartDate);
                ReservationController.cancelReservation(new Reservation(-1, parsedStartDate, parsedEndDate, "BigErnesto", 110, 420.69));
                ((DefaultTableModel)table.getModel()).removeRow(row);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a reservation to delete.");
            }
        });
    }

    /**
     *
     */
    @Override
    public void clear() {
        // Do nothing
    }
}
