package edu.baylor.GroupFive.ui.utils.table;

import javax.swing.table.DefaultTableModel;

/**
 * A model for representing hotel data in a table, extending DefaultTableModel.
 *
 * @author Brendon
 */
public class HotelModel extends DefaultTableModel {

    private final Class<?>[] columnClasses;

    /**
     * Constructs a HotelModel with the specified column names and classes.
     *
     * @param columnNames An array of column names.
     * @param columnClasses An array of column classes.
     * @author Brendon
     */
    public HotelModel(String[] columnNames, Class<?>[] columnClasses) {
        super(null, columnNames);
        this.columnClasses = columnClasses;
    }

    /**
     * Gets the classes of the columns.
     *
     * @return An array of column classes.
     * @author Brendon
     * */
    public Class<?>[] getColumnClasses() {
        return columnClasses;
    }

    /**
     * Returns whether the call at the specified row and column is editable.
     *
     * @param row             the row whose value is to be queried.
     * @param column          the column whose value is to be queried.
     * @return {@code true} if the cell is editable, {@code false} otherwise.
     * @author Brendon
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     * Returns the class of the value at the specified column index.
     *
     * @param columnIndex  the column being queried.
     * @return The class of the value at the specified column index.
     * @author Brendon
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }
}
