package edu.baylor.GroupFive.ui.utils.table;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

import java.awt.Font;

/**
 * A renderer for displaying string values in table cells, extending DefaultTableCellRenderer.
 *
 * @author Brendon
 */
public class StringRenderer extends DefaultTableCellRenderer {
    /**
     * Returns the component used for drawing the cell.
     *
     * @param table  the {@code JTable}.
     * @param value  the value to assign to the cell at
     *                  {@code [row, column]}.
     * @param isSelected true if cell is selected.
     * @param hasFocus true if cell has focus.
     * @param row  the row of the cell to render.
     * @param column the column of the cell to render.
     * @return The component used for drawing the cell.
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setFont(new Font("Arial", Font.PLAIN, 14));
        return c;
    }
}
