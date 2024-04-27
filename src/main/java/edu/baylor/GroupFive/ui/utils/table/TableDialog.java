package edu.baylor.GroupFive.ui.utils.table;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.*;

/**
 * A dialog for displaying table data, extending JDialog.
 *
 * @author Brendon
 */
public class TableDialog extends JDialog {
private JTable table;

    /**
     * Constructs a TableDialog with the specified owner table.
     *
     * @param owner The owner table.
     * @author Brendon
     */
    public TableDialog(JTable owner) {
        super(javax.swing.SwingUtilities.windowForComponent(owner));
        table = owner;
        createGUI();
    }

    /**
     * Creates the GUI components for the dialog.
     *
     * @author Brendon
     */
    private void createGUI() {
        //Sets up dialog panel
        setPreferredSize(new Dimension(600, 400));
        setTitle(getClass().getSimpleName());

        //Sets up list
        JPanel listPane = new JPanel();
        listPane.setLayout(new BoxLayout(listPane, BoxLayout.Y_AXIS));

        //Adds hello label
        JLabel label = new JLabel("Hello:");
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        listPane.add(label);

        //Gets the selected row and adds appropriate label
        int row = table.getSelectedRow();
        JLabel dataLabel = null;
        if(row < 0) {
            dataLabel = new JLabel("no row selected");
        } else {
            dataLabel = new JLabel(table.getModel().getValueAt(row, 0)+" "+table.getModel().getValueAt(row, 1));
        }
        dataLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        listPane.add(dataLabel);

        //Adds close button
        add(listPane);
        pack();
        setLocationRelativeTo(getParent());
    }

    /**
     * Disposes the dialog.
     *
     * @author Brendon
     */
    @Override
    public void dispose() {
        super.dispose();
    }
}
