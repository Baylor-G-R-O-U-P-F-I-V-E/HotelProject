package edu.baylor.GroupFive.ui.reservations;

import java.awt.*;
import java.io.*;

import javax.swing.*;
import javax.swing.table.*;

import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.FormPane;
import edu.baylor.GroupFive.ui.utils.table.StringRenderer;

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

        // Limits the user to single selection
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to this panel.
        add(scrollPane);

        add(new FormPane(table, sorter));

        /*
        JButton viewReservation = new JButton("View Selected Reservation");
        JButton viewRoom = new JButton("View Selected Room");
        add(viewReservation);
        add(viewRoom);
        */

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

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}
