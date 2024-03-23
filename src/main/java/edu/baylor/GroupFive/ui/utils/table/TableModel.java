package edu.baylor.GroupFive.ui.utils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;

class TableModel extends AbstractTableModel {
    private String[] columnNames = {"First Name",
            "Last Name",
            "Sport",
            "# of Years",
            "Vegetarian"};
    private Object[][] data = {
            {"Kathy", "Smith",
                    "Snowboarding", 5, false},
            {"John", "Doe",
                    "Rowing", 3, true},
            {"Sue", "Black",
                    "Knitting", 2, false},
            {"Jane", "White",
                    "Speed reading", 20, true},
            {"Joe", "Brown",
                    "Pool", 10, false}
    };

    public TableModel(Object[][] data, String[] columnNames) {
        super();
    }

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
            return false;
        } else {
            return true;
        }
    }

    public void removeRow(int row) {
        // Check if the row index is valid
        if (row < 0 || row >= getRowCount()) {
            throw new IndexOutOfBoundsException("Invalid row index: " + row);
        }
        // Remove the row from the data vector
        data = removeEntry(data, row);
        // Notify the table that the model has changed
        fireTableRowsDeleted(row, row);
    }

    public static Object[][] removeEntry(Object[][] originalArray, int rowIndex) {
        // Check if the row index is valid
        if (rowIndex < 0 || rowIndex >= originalArray.length) {
            throw new IndexOutOfBoundsException("Invalid row index: " + rowIndex);
        }

        // Calculate the new array size (original size - 1)
        int numRows = originalArray.length - 1;

        // Create a new array with the new size
        Object[][] newArray = new Object[numRows][originalArray[0].length];

        // Copy elements from the original array to the new array
        int destIndex = 0;
        for (int srcIndex = 0; srcIndex < originalArray.length; srcIndex++) {
            if (srcIndex != rowIndex) {
                System.arraycopy(originalArray[srcIndex], 0, newArray[destIndex], 0, originalArray[srcIndex].length);
                destIndex++;
            }
        }

        // Return the new array without the removed entry
        return newArray;
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) {
        if (false) {
            System.out.println("Setting value at " + row + "," + col
                    + " to " + value
                    + " (an instance of "
                    + value.getClass() + ")");
        }

        data[row][col] = value;
        fireTableCellUpdated(row, col);

        if (false) {
            System.out.println("New value of data:");
            printDebugData();
        }
    }

    private void printDebugData() {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i = 0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j = 0; j < numCols; j++) {
                System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
