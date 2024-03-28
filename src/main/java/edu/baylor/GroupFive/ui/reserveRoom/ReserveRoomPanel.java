package edu.baylor.GroupFive.ui.reserveRoom;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import edu.baylor.GroupFive.ui.utils.DatePanel;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.HotelModel;
import edu.baylor.GroupFive.ui.utils.table.HotelTable;
import edu.baylor.GroupFive.ui.utils.table.ReservationModel;

public class ReserveRoomPanel extends JPanel implements PagePanel {
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;

    private String[] columnNames = {
            "Room",
            "Quality",
            "Theme",
            "Smoking",
            "Number of Beds",
            "Bed Type"
         };
    
    //Define data types for the columns
    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, String.class, String.class, String.class, String.class, String.class
    };

    public ReserveRoomPanel() {
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
        JButton reserveRoom = new JButton("Reserve Selected Room");
        JButton viewRoom = new JButton("View Selected Room");

        // Add buttons to panel
        addButtonListeners(reserveRoom, viewRoom);
        buttonPanel.add(reserveRoom);
        buttonPanel.add(viewRoom);

        add(buttonPanel);
    }

    private void addButtonListeners(JButton reserveRoom, JButton viewRoom) {
        reserveRoom.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                showDates();
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

    public void showDates() {
        DatePanel startDatePanel = new DatePanel("");
        int result = JOptionPane.showConfirmDialog(null, startDatePanel, "Select a start date", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    
        if (result == JOptionPane.OK_OPTION) {
            Date startDate = startDatePanel.getDate(); // Assuming DatePanel has a getDate method that returns the selected date
            
            DatePanel endDatePanel = new DatePanel("", 1);

            result = JOptionPane.showConfirmDialog(null, endDatePanel, "Select an end date", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

            if (result == JOptionPane.OK_OPTION) {
                Date endDate = endDatePanel.getDate();
                if (startDate.after(endDate)) {
                    JOptionPane.showMessageDialog(null, "End date must be after start date.");
                    return;
                } else {
                    SimpleDateFormat formatter = new SimpleDateFormat("EEE MM/dd/yyyy");
                    JOptionPane.showMessageDialog(null, "Start Date: " + formatter.format(startDate) + "\nEnd Date: " + formatter.format(endDate));
                    //ReservationController.reserveRoom(startDate, endDate); // Assuming ReservationController has a reserveRoom method that takes a start and end date and reserves the room
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter both start and end dates.");
            }
        }
    }

    public SimpleDateFormat getFormatter() {
        // Make a formatter for a date that looks like "Day of week MM/dd/yyyy"

        return new SimpleDateFormat("EEEE MM/dd/yyyy");
    }

    @Override
    public void clear() {
        //Do nothing
    }
}
