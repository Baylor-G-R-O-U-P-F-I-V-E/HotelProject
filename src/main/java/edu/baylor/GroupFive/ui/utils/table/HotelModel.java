package edu.baylor.GroupFive.ui.utils.table;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.table.DefaultTableModel;

public class HotelModel extends DefaultTableModel {

    private final Class<?>[] columnClass;

    public HotelModel(String[] columnNames, Class<?>[] columnClass, String path) {
        super(null, columnNames);
        this.columnClass = columnClass;

        try {
            getData(path);
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

     public void getData(String path) throws RuntimeException {
        // Open the file and read the data
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                addRow(parts);
            }
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("Error reading data file", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException("Error closing data file", e);
                }
            }
        }
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnClass[columnIndex];
    }
}
