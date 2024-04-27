package edu.baylor.GroupFive.ui.utils.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * A renderer for table cell borders.
 *
 * @author Brendon
 */
public class BorderRenderer extends DefaultTableCellRenderer {
    /**
     * Returns the component used for drawing the cell.
     *
     * @param table  The {@code JTable}.
     * @param value  The value to assign to the cell at {@code [row, column]}.
     * @param isSelected {@code true} if cell is selected.
     * @param hasFocus {@code true} if cell has focus.
     * @param row  The row of the cell to render.
     * @param column The column of the cell to render.
     * @return The component used for drawing the cell.
     * @author Brendon
     */
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JComponent c = (JComponent) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        c.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 1, Color.BLACK));
        return c;
    }
}
