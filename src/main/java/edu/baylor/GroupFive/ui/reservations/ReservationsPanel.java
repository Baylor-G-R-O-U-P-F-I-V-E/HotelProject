package edu.baylor.GroupFive.ui.reservations;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.*;

import edu.baylor.GroupFive.database.controllers.BillingController;
import edu.baylor.GroupFive.database.controllers.PaymentController;
import edu.baylor.GroupFive.database.controllers.ReservationController;
import edu.baylor.GroupFive.database.controllers.RoomController;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.Transaction;
import edu.baylor.GroupFive.models.Reservation;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;
import edu.baylor.GroupFive.ui.utils.BadInputDialog;
import edu.baylor.GroupFive.util.CoreUtils;

import java.awt.*;

/**
 * Panel for displaying and managin reservations.
 *
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @author Brendon
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
     * Constructs a ReservationsPanel with the specified page.
     *
     * @param page The page associated with this panel
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
     * Adds the button panel to this panel.
     */
    private void addButtonPanel() {
        // Create button panel
        JPanel buttonPanel = new JPanel();

        // Create buttons
        PanelButton modifyReservation = new PanelButton("Modify", 300, 50);
        PanelButton status = new PanelButton("Check-in/out", 300, 50);
        PanelButton viewRoom = new PanelButton("View Room", 300, 50);
        PanelButton deleteReservation = new PanelButton("Delete", 300, 50);

        // Add buttons to panel
        addButtonListeners(modifyReservation, status, viewRoom, deleteReservation);
        buttonPanel.add(modifyReservation);
        buttonPanel.add(status);
        buttonPanel.add(viewRoom);
        buttonPanel.add(deleteReservation);

        add(buttonPanel);
    }

    /**
     * Adds actions listeners to the buttons.
     *
     * @param viewReservation The view reservation button.
     * @param viewRoom The view room button.
     * @param deleteReservation The delete reservation button.
     */
    private void addButtonListeners(JButton viewReservation, JButton status,  JButton viewRoom, JButton deleteReservation) {
        
        // Add action listener to modify reservation button
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

        // Add action listener to change status button
        status.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                Integer roomColumnIndex = table.getColumnModel().getColumnIndex("Room ID");
                String roomID = (String) table.getValueAt(row, roomColumnIndex);
                Integer startDateColumnIndex = table.getColumnModel().getColumnIndex("Start Date");
                String startDate = (String) table.getValueAt(row, startDateColumnIndex);

                // Get Date object from startDate string
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
                Date startDateObj = null;
                try {
                    startDateObj = dateFormat.parse(startDate);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }

                // Ensure date was parsed successfully
                if (startDateObj == null) {
                    JOptionPane.showMessageDialog(null, "Error getting start date.");
                    return;
                }

                Reservation reservation = ReservationController.getReservation(Integer.parseInt(roomID), startDateObj);

                if (reservation == null) {
                    JOptionPane.showMessageDialog(null, "Error getting reservation.");
                    return;
                }

                Boolean checkingIn = null;

                // Guest is already checked in
                if (reservation.getCheckedInStatus()) {
                    reservation.setCheckedInStatus(false);
                    checkingIn = false;
                } 
                // Guest is not checked in yet
                else {
                    // Guest can only check in between start and end date
                    Date currDate = new Date();
                    if (currDate.after(reservation.getStartDate()) && currDate.before(reservation.getEndDate())) {
                        reservation.setCheckedInStatus(true);
                        checkingIn = true;
                    } else {
                        try {
                            new BadInputDialog("Guest cannot be checked in unless within reservation dates.", "Time Locked Operation");
                        } catch (IOException ex) {
                            System.err.println(ex.getMessage());
                        }
                    }
                }

                Boolean result = ReservationController.modifyReservation(reservation);

                if (result) {
                    if (checkingIn == true) {
                        JOptionPane.showMessageDialog(null, "Guest has been checked in.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Guest has been checked out.");
                    }
                    ((DefaultTableModel)table.getModel()).setValueAt(checkingIn, row, table.getColumnModel().getColumnIndex("Checked In"));
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to change reservation status.");
                    return;
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please select a reservation to view.");
            }
        });

        // Add action listener to view room button
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

        // Add action listener to delete reservation button
        deleteReservation.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                // Get the room ID and start date from the selected row
                Integer roomColumnIndex = table.getColumnModel().getColumnIndex("Room ID");
                String roomID = (String) table.getValueAt(row, roomColumnIndex);
                Integer startDateColumnIndex = table.getColumnModel().getColumnIndex("Start Date");
                Integer endDateColumnIndex = table.getColumnModel().getColumnIndex("End Date");
                String startDate = (String) table.getValueAt(row, startDateColumnIndex);
                String endDate = (String) table.getValueAt(row, endDateColumnIndex);

                // Add a confirmation dialog
                int dialogResult = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this reservation?", "Warning", JOptionPane.YES_NO_OPTION);

                // Check if the user clicked yes
                if (dialogResult != JOptionPane.YES_OPTION) {
                    return;
                }

                // Parse the startDate from a string to a Date object
                SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
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

                // Get the reservation from the database
                Reservation reservation = ReservationController.getReservation(Integer.parseInt(roomID), parsedStartDate);

                // Ensure reservation was fetched successfully
                if (reservation == null) {
                    JOptionPane.showMessageDialog(null, "Error getting reservation.");
                    return;
                }

                Float fee = 0.0f;

/*
 * TODO when guest is checkout out. mark active as false
 * */

                // If within 48 hours, ask user if they are willing to accept the cancellation fee of 80% one nights stay
                if (parsedStartDate.getTime() - new Date().getTime() < 48 * 60 * 60 * 1000) {
                    int feeDialogResult = JOptionPane.showConfirmDialog(null, "This reservation is within 48 hours of the start date. \nAre you sure you want to cancel this reservation? \nGuest will be charged 80% of one night's stay.", "Warning", JOptionPane.YES_NO_OPTION);
                    if (feeDialogResult != JOptionPane.YES_OPTION) {
                        return;
                    };
                    fee = (float) (reservation.getPrice() * 0.8);
                }

                // Cancel the reservation
                Boolean result = ReservationController.cancelReservation(reservation);

                if (result) {
                    JOptionPane.showMessageDialog(null, "Reservation deleted successfully.");
                    if (fee > 0) {
                        // Create a transaction for the fee
                        BillingController.addTransaction(reservation.getGuestUsername(), "Cancellation Fee", fee);

                        JOptionPane.showMessageDialog(null, "Guest has been charged $" + String.format("%.2f", fee) + " for cancelling within 48 hours.");
                    }
                    ((DefaultTableModel)table.getModel()).removeRow(row);
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to delete reservation.");
                    return;
                }

            } else {
                JOptionPane.showMessageDialog(null, "Please select a reservation to delete.");
            }
        });
    }

    /**
     * Clears the panel (does nothing).
     */
    @Override
    public void clear() {
        // Do nothing
    }
}
