package edu.baylor.GroupFive.ui.reserveRoom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import java.awt.Dimension;
import java.awt.Font;

import edu.baylor.GroupFive.ui.utils.DatePanel;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.BorderRenderer;
import edu.baylor.GroupFive.ui.utils.table.NewFormPane;
import edu.baylor.GroupFive.ui.utils.table.StringRenderer;

public class ReserveRoomPanel extends JPanel implements PagePanel {
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;

    private String[] columnNames = {
            "Room",
            "Quality",
            "Theme",
            "Smoking",
            "Number of Beds",
            "Bed Type",
            "Nighty Rate"
         };
    
    //Define data types for the columns
    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, String.class, String.class, String.class, String.class, String.class
    };

    public ReserveRoomPanel() {
        super();

        // Create a model of the data.
        DefaultTableModel model = new DefaultTableModel(null, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };

        openFile(model, "src/main/java/edu/baylor/GroupFive/database/roomDAO/Rooms.TXT");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create a table with a sorter.
        table = setupTable(model);
        model.setColumnIdentifiers(columnNames);

        // Set the table properties
        table.setDefaultRenderer(Object.class, new BorderRenderer());

        // Limits the user to single selection
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to this panel.
        add(scrollPane);

        // Add the form pane
        add(new NewFormPane(table, sorter, columnNames));

        // Add the button panel
        addButtonPanel();
        
        // Set the panel properties
        setVisible(true);

    }

    private void openFile(DefaultTableModel model, String path) {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");
                model.addRow(row);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Issue opening file: " + ex.getMessage());
        }

    }

    private JTable setupTable(DefaultTableModel model) {
        
        // Create a table with a sorter.
        sorter = new TableRowSorter<DefaultTableModel>(model);
        table = new JTable(model);

        // Set the sorter
        table.setRowSorter(sorter);

        // Set the table properties
        table.setPreferredScrollableViewportSize(new Dimension(700, 300));
        table.setFillsViewportHeight(true);
        table.setDefaultRenderer(Object.class, new StringRenderer());
        
        // Set the header font
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));

        return table;
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
