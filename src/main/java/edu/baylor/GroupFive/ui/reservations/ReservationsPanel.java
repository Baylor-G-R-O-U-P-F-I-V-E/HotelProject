package edu.baylor.GroupFive.ui.reservations;

import java.awt.*;

import java.io.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.SpringUtilities;
import edu.baylor.GroupFive.ui.utils.table.StringRenderer;

public class ReservationsPanel extends JPanel implements PagePanel {
    private JTable table;
    private JTextField filterText;
    private TableRowSorter<DefaultTableModel> sorter;

    private String[] columnNames = { "Name",
            "Start Date",
            "End Date",
            "Room" };

    private Object[][] data;

    final Class<?>[] columnClass = new Class[] {
            String.class, String.class, String.class, Integer.class, Boolean.class
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

        // Create a separate form for filterText and statusText
        JPanel form = new JPanel(new SpringLayout());
        JLabel l1 = new JLabel("Customer name:", SwingConstants.TRAILING);
        form.add(l1);
        filterText = new JTextField();

        // Whenever filterText changes, invoke newFilter.
        filterText.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }

                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }

                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });

        l1.setLabelFor(filterText);
        form.add(filterText);
        SpringUtilities.makeCompactGrid(form, 1, 2, 6, 6, 6, 6);
        add(form);

        setVisible(true);

    }

    private void openFile(DefaultTableModel model) {
        System.out.println("Opening file");
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
        sorter = new TableRowSorter<DefaultTableModel>(model);
        table = new JTable(model);
        table.setRowSorter(sorter);
        table.setPreferredScrollableViewportSize(new Dimension(700, 300));
        table.setFillsViewportHeight(true);
        table.setDefaultRenderer(Object.class, new StringRenderer());
        return table;
    }

    /**
     * Update the row filter regular expression from the expression in
     * the text box.
     */
    private void newFilter() {
        RowFilter<DefaultTableModel, Object> rf = null;
        // If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(filterText.getText(), 0, 1, 2);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}
