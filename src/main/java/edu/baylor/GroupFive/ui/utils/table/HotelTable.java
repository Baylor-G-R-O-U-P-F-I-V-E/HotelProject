package edu.baylor.GroupFive.ui.utils.table;

import java.awt.*;

import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

/**
 * A table for displaying hotel data, extending JTable.
 *
 * @author Brendon
 */
public class HotelTable extends JTable {

    private final TableRowSorter<DefaultTableModel> sorter;

    /**
     * Constructs a HotelTable with the specified table model.
     *
     * @param model The table model.
     * @author Brendon
     */
    public HotelTable(DefaultTableModel model) {
        super(model);

        // Create a table with a sorter.
        sorter = new TableRowSorter<DefaultTableModel>(model);

        // Set the sorter
        setRowSorter(sorter);

        // Set the table properties
        setPreferredScrollableViewportSize(new Dimension(700, 300));
        setFillsViewportHeight(true);
        setDefaultRenderer(Object.class, new BorderRenderer());
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        // Set the header font
        JTableHeader header = getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 14));

    }

    /**
     * Gets the table sorter.
     *
     * @return The table sorter.
     * @author Brendon
     */
    public TableRowSorter<DefaultTableModel> getSorter() {
        return sorter;
    }
}
