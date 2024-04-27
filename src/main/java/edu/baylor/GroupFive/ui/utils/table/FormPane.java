package edu.baylor.GroupFive.ui.utils.table;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

/**
 * A panel for creating form filters.
 *
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 *
 * @author Brendon
 */
public class FormPane extends JPanel implements PagePanel {

    private Map<String, JTextField> textFields = new HashMap<>();
    GridBagConstraints gbc;
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;
    private int row = 1;

    /**
     * Constructs a FormPane with the specified attributes.
     *
     * @param table The table.
     * @param sorter The table row sorter.
     * @param labels The labels for form fields.
     * @author Brendon
     */
    public FormPane(JTable table, TableRowSorter<DefaultTableModel> sorter, String[] labels) {
        super();
        this.table = table;
        this.sorter = sorter;

        setLayout(new GridBagLayout());
        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
        gbc = new GridBagConstraints();

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add a label title Filters:

        JPanel panel = new JPanel();
        JLabel title = new JLabel("Filters");

        panel.add(title);

        gbc.gridx = 0;
        gbc.gridy = 0;
        
        add(panel, gbc);

        for (String label : labels) {
            addFormLabel(label + ":", row);
            JTextField textField = new JTextField();
            addFormTextfield(textField, row);
            textFields.put(label, textField);
            row++;
        }

        addDocumentListeners();
    }

    /**
     * Applies filters based on the input values.
     *
     * @author Brendon
     */
    private void applyFilters() {
        // If current expression doesn't parse, don't update.
        if (table == null)
            return;
        try {
            List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<RowFilter<DefaultTableModel, Object>>();
            
            // Create filters for each text field
            textFields.forEach((key, textField) -> {
                if (!textField.getText().isEmpty()) {
                    RowFilter<DefaultTableModel, Object> filter = RowFilter.regexFilter(textField.getText(),
                            table.getColumnModel().getColumnIndex(key));
                    filters.add(filter);
                }
            });

            // Apply filters, if any
            if (filters.isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                RowFilter<DefaultTableModel, Object> rf = RowFilter.andFilter(filters); // AND filter
                sorter.setRowFilter(rf);
            }
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
    }

    /**
     * Adds a label to the form.
     *
     * @param text The text of the label.
     * @param row The row position.
     * @author Brendon
     */
    private void addFormLabel(String text, int row) {
        
        // Add label to the form
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0.1;
        gbc.anchor = GridBagConstraints.CENTER;
        JLabel label = new JLabel(text);
        add(label, gbc);
    }

    /**
     * Adds a text field to the form.
     *
     * @param textfield The text field to add.
     * @param row The row position.
     * @author Brendon
     */
    private void addFormTextfield(JTextField textfield, int row) {
        
        // Add text field to the form
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1.0;
        add(textfield, gbc);
    }

    /**
     * Adds document listeners to all text fields.
     *
     * @author Brendon
     */
    private void addDocumentListeners() {

        // Add document listeners to all text fields
        textFields.forEach((key, textField) -> {
            textField.getDocument().addDocumentListener(new DocumentListener() {
                @Override
                public void insertUpdate(DocumentEvent e) {
                    applyFilters();
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    applyFilters();
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    applyFilters();
                }
            });
        });
    }

    /**
     * Clears all text fields.
     *
     * @author Brendon
     */
    @Override
    public void clear() {
        // Clear all text fields
        textFields.forEach((key, textField) -> {
            textField.setText("");
        });
    }
}
