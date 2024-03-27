package edu.baylor.GroupFive.ui.reservations;

import java.awt.*;
import java.io.*;

import javax.swing.*;
import javax.swing.table.*;

import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.NewFormPane;
import edu.baylor.GroupFive.ui.utils.table.StringRenderer;
import edu.baylor.GroupFive.ui.utils.table.BorderRenderer;

public class ReservationsPanel extends JPanel implements PagePanel {
    
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;

    private String[] columnNames = { "Name",
            "Start Date",
            "End Date",
            "Room" };

    private Object[][] data;

    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, String.class, String.class
    };

    public ReservationsPanel() {
        super();

        // Create a model of the data.
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnClass[columnIndex];
            }
        };

        openFile(model);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create a table with a sorter.
        table = setupTable(model);

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

    private void openFile(DefaultTableModel model) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/main/resources/test.csv"))) {
            String line;
            if ((line = br.readLine()) != null) {
                String[] header = line.split(",");
                model.setColumnIdentifiers(header);
            }
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
        // TODO Auto-generated method stub
    }
}
