package edu.baylor.GroupFive.ui.reservations;

import java.awt.*;

import java.io.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.ui.utils.table.StringRenderer;

public class ReservationsPanel extends JPanel implements PagePanel {
    private JTable table;
    private JTextField nameText;
    private JTextField roomText;
    GridBagConstraints gbc;
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

        addForm();


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
    private void newNameFilter() {
        RowFilter<DefaultTableModel, Object> rf = null;
        // If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(nameText.getText(), table.getColumnModel().getColumnIndex("Name"));
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    private void newRoomFilter() {
        RowFilter<DefaultTableModel, Object> rf = null;
        // If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(roomText.getText(), table.getColumnModel().getColumnIndex("Room"));
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    private void addForm() {
        // Create a separate form for filterText and statusText
        JPanel form = new JPanel(new GridBagLayout());
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add the first label
        addFormLabel(form, "Customer name:", 0);

        // Add the first text field
        nameText = new JTextField();
        addFormTextfield(form, nameText, 0);

        // Add the second label
        addFormLabel(form, "Room number:", 1);

        // Add the second text field
        roomText = new JTextField();
        addFormTextfield(form, roomText, 1);

        addDocumentListeners();

        // Add the form to the panel
        add(form);
    }

    private void addFormLabel(JPanel panel, String text, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        JLabel label = new JLabel(text);
        panel.add(label, gbc);
    }

    private void addFormTextfield(JPanel panel, JTextField textfield, int row) {
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1.0;
        panel.add(textfield, gbc);
    }

    private void addDocumentListeners() {
        nameText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                newNameFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newNameFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newNameFilter();
            }
        });

        roomText.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                newRoomFilter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                newRoomFilter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                newRoomFilter();
            }
        });
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}
