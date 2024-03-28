package edu.baylor.GroupFive.ui.utils.table;

import java.util.List;
import javax.swing.table.DefaultTableModel;

import edu.baylor.GroupFive.controllers.ReservationController;
import edu.baylor.GroupFive.models.Room;

public class ReservationModel extends DefaultTableModel {

    private final Class<?>[] columnClass;

    public ReservationModel(String[] columnNames, Class<?>[] columnClass) {
        super(null, columnNames);
        this.columnClass = columnClass;

        try {
            getData();
        } catch (RuntimeException e) {
            System.out.println(e.getLocalizedMessage());
        }

    }

     public void getData() throws RuntimeException {
        // Fetch room data from the database
        List<Room> rooms = ReservationController.getAllRooms();
        
        // Check if data was fetched successfully
        if (rooms == null) {
            throw new RuntimeException("Error fetching data from the database");
        }

        // Add the data to the table
        for (Room room : rooms) {
            try {
                // Add the row to the table
                addRow(new Object[] {room.getRoomNumber(), room.getQuality(), room.getTheme(), String.valueOf(room.isSmoking()), room.getNumBeds(), room.getBedType()});
            } catch (Exception e) {
                // Log any errors
                System.out.println("Error adding row to table");
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
