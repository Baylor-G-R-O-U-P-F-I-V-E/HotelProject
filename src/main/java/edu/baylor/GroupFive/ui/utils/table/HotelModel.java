package edu.baylor.GroupFive.ui.utils.table;

import javax.swing.table.DefaultTableModel;

public class HotelModel extends DefaultTableModel {

    private final Class<?>[] columnClasses;

    public HotelModel(String[] columnNames, Class<?>[] columnClasses) {
        super(null, columnNames);
        this.columnClasses = columnClasses;
    }

    public Class<?>[] getColumnClasses() {
        return columnClasses;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClasses[columnIndex];
    }
}
