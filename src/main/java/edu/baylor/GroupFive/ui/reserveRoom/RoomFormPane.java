package edu.baylor.GroupFive.ui.reserveRoom;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.List;

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

public class RoomFormPane extends JPanel implements PagePanel {

    private JTextField qualityText;
    private JTextField themeText;
    private JTextField smokingText;
    private JTextField bedNumText;
    private JTextField bedTypeText;
    GridBagConstraints gbc;
    private JTable table;
    private TableRowSorter<DefaultTableModel> sorter;

    public RoomFormPane(JTable table, TableRowSorter<DefaultTableModel> sorter) {
        super();
        this.table = table;
        this.sorter = sorter;

        setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Add the first label
        addFormLabel("Quality:", 0);

        // Add the first text field
        qualityText = new JTextField();
        addFormTextfield(qualityText, 0);

        // Add the second label
        addFormLabel("Theme:", 1);

        // Add the second text field
        themeText = new JTextField();
        addFormTextfield(themeText, 1);

        // Add the third label
        addFormLabel("Smoking:", 2);

        // Add the third text field
        smokingText = new JTextField();
        addFormTextfield(smokingText, 2);

        // Add the fourth label
        addFormLabel("Number of Beds:", 3);

        // Add the fourth text field
        bedNumText = new JTextField();
        addFormTextfield(bedNumText, 3);

        // Add the fifth label
        addFormLabel("Bed Type:", 4);

        // Add the fifth text field
        bedTypeText = new JTextField();
        addFormTextfield(bedTypeText, 4);

        addDocumentListeners();
    }

    private void applyQualityFilters() {
        // If current expression doesn't parse, don't update.
        if (table == null)
            return;
        try {
            if (qualityText.getText().isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter(qualityText.getText(),
                        table.getColumnModel().getColumnIndex("Quality"));
                sorter.setRowFilter(rf);
            }
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
    }

    private void themeFilters() {
        // If current expression doesn't parse, don't update.
        if (table == null)
            return;
        try {
            if (qualityText.getText().isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                RowFilter<DefaultTableModel, Object> rf = RowFilter.regexFilter(themeText.getText(),
                        table.getColumnModel().getColumnIndex("Theme"));
                sorter.setRowFilter(rf);
            }
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
    }

    private void smokingFilters() {
        RowFilter<DefaultTableModel, Object> rf = null;
        // If current expression doesn't parse, don't update.
        try {
            if (smokingText.getText().isEmpty()) {
                sorter.setRowFilter(null);
                return;
            }
            rf = RowFilter.regexFilter(smokingText.getText(), table.getColumnModel().getColumnIndex("Smoking"));
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    private void numFilters() {
        RowFilter<DefaultTableModel, Object> rf = null;
        // If current expression doesn't parse, don't update.
        try {
            if (bedNumText.getText().isEmpty()) {
                sorter.setRowFilter(null);
                return;
            }
            rf = RowFilter.regexFilter(bedNumText.getText(), table.getColumnModel().getColumnIndex("Number of Beds"));
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    private void typeFilters() {
        RowFilter<DefaultTableModel, Object> rf = null;
        // If current expression doesn't parse, don't update.
        try {
            if (bedTypeText.getText().isEmpty()) {
                sorter.setRowFilter(null);
                return;
            }
            rf = RowFilter.regexFilter(bedTypeText.getText(), table.getColumnModel().getColumnIndex("Bed Type"));
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    private void applyFilters() {
        // If current expression doesn't parse, don't update.
        if (table == null)
            return;
        try {
            List<RowFilter<DefaultTableModel, Object>> filters = new ArrayList<RowFilter<DefaultTableModel, Object>>();

            if (!qualityText.getText().isEmpty()) {
                RowFilter<DefaultTableModel, Object> qualityFilter = RowFilter.regexFilter(qualityText.getText(),
                        table.getColumnModel().getColumnIndex("Quality"));
                filters.add(qualityFilter);
            }

            if (!themeText.getText().isEmpty()) {
                RowFilter<DefaultTableModel, Object> themeFilter = RowFilter.regexFilter(themeText.getText(),
                        table.getColumnModel().getColumnIndex("Theme"));
                filters.add(themeFilter);
            }

            if (filters.isEmpty()) {
                sorter.setRowFilter(null);
            } else {
                RowFilter<DefaultTableModel, Object> rf = RowFilter.andFilter(filters); // use
                                                                                        // RowFilter.orFilter(filters)
                                                                                        // for OR logic
                sorter.setRowFilter(rf);
            }
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
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
        qualityText.getDocument().addDocumentListener(new DocumentListener() {
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

        themeText.getDocument().addDocumentListener(new DocumentListener() {
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

        smokingText.getDocument().addDocumentListener(new DocumentListener() {
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

        bedNumText.getDocument().addDocumentListener(new DocumentListener() {
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

        bedTypeText.getDocument().addDocumentListener(new DocumentListener() {
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
    }

    @Override
    public void clear() {
        qualityText.setText("");
        themeText.setText("");
        smokingText.setText("");
        bedNumText.setText("");
        bedTypeText.setText("");
    }
}
