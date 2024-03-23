package edu.baylor.GroupFive.ui.utils.table;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

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

public class FormPane extends JPanel implements PagePanel {

    private JTextField nameText;
    private JTextField roomText;
    GridBagConstraints gbc;
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;

    public FormPane(JTable table, TableRowSorter<DefaultTableModel> sorter) {
        super();
        this.table = table;
        this.sorter = sorter;
        
        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add the first label
        addFormLabel("Customer name:", 0);

        // Add the first text field
        nameText = new JTextField();
        addFormTextfield(nameText, 0);

        // Add the second label
        addFormLabel("Room number:", 1);

        // Add the second text field
        roomText = new JTextField();
        addFormTextfield(roomText, 1);

        addDocumentListeners();
    }

    private void newNameFilter() {
        RowFilter<DefaultTableModel, Object> rf = null;
        // If current expression doesn't parse, don't update.
        if (table == null)
            return;
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
            if (roomText.getText().isEmpty()) {
                sorter.setRowFilter(null);
                return;
            }
            String regex = "^" + roomText.getText() + "$";
            rf = RowFilter.regexFilter(regex, table.getColumnModel().getColumnIndex("Room"));
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    private void addFormLabel(String text, int row) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;
        JLabel label = new JLabel(text);
        add(label, gbc);
    }

    private void addFormTextfield(JTextField textfield, int row) {
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1.0;
        add(textfield, gbc);
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

    public JTextField getNameField() {
        return nameText;
    }

    public String getNameText() {
        return nameText.getText();
    }

    public JTextField getRoomField() {
        return roomText;
    }

    public String getRoomText() {
        return roomText.getText();
    }

    @Override
    public void clear() {
        nameText.setText("");
        roomText.setText("");
    }
}
