import java.awt.event.*;
import javax.swing.*;

public class ReserveActionListener implements ActionListener {
    private JTextField roomNumberField;
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField nameField;

    public ReserveActionListener(JTextField nameField,JTextField roomNumberField, JTextField startDateField, JTextField endDateField) {
        this.nameField = nameField;
        this.roomNumberField = roomNumberField;
        this.startDateField = startDateField;
        this.endDateField = endDateField;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a default message
        String message = "Reservation successful!";

        // Get the name
        String name = nameField.getText();
        if (name.isEmpty()) {
            message = "Please enter a name";
            JOptionPane.showMessageDialog(null, message);
            return;
        }

        // Get the room number
        String roomNumber = roomNumberField.getText();
        if (roomNumber.isEmpty()) {
            message = "Please enter a room number";
            JOptionPane.showMessageDialog(null, message);
            return;
        }

        // Get the start date
        String startDate = startDateField.getText();
        if (startDate.isEmpty()) {
            message = "Please enter a start date";
            JOptionPane.showMessageDialog(null, message);
            return;
        }

        // Get the end date
        String endDate = endDateField.getText();
        if (endDate.isEmpty()) {
            message = "Please enter an end date";
            JOptionPane.showMessageDialog(null, message);
            return;
        }

        /* Create a new reservation
        Reservation reservation = new Reservation(roomNumber, startDate, endDate);

        // Add the reservation to the list of reservations
        reservations.add(reservation);

        */

        // Clear the text fields
        nameField.setText("");
        roomNumberField.setText("");
        startDateField.setText("");
        endDateField.setText("");

        // Display a message
        JOptionPane.showMessageDialog(null, message);
        
    }
}
