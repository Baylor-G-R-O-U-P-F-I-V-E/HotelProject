package edu.baylor.GroupFive.ui.utils.table;

import javax.swing.table.DefaultTableModel;

/**
 *
 */
public class HotelModel extends DefaultTableModel {

    private final Class<?>[] columnClasses;

    /**
     *
     * @param columnNames
     * @param columnClasses
     */
    public HotelModel(String[] columnNames, Class<?>[] columnClasses) {
        super(null, columnNames);
        this.columnClasses = columnClasses;
    }

    public Class<?>[] getColumnClasses() {
        return columnClasses;
    }

    /**
     *
     * @param row             the row whose value is to be queried
     * @param column          the column whose value is to be queried
     * @return
     */
    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    /**
     *
     * @param columnIndex  the column being queried
     * @return
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }
}
