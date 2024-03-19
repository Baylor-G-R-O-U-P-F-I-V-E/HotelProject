import java.awt.event.*;
import javax.swing.*;

public class ReserveActionListener implements ActionListener {
    private JTextField roomNumberField;
    private JTextField startDateField;
    private JTextField endDateField;

    public ReserveActionListener(JTextField roomNumberField, JTextField startDateField, JTextField endDateField) {
        this.roomNumberField = roomNumberField;
        this.startDateField = startDateField;
        this.endDateField = endDateField;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the room number
        String roomNumber = roomNumberField.getText();

        // Get the start date
        String startDate = startDateField.getText();

        // Get the end date
        String endDate = endDateField.getText();

        /* Create a new reservation
        Reservation reservation = new Reservation(roomNumber, startDate, endDate);

        // Add the reservation to the list of reservations
        reservations.add(reservation);

        */

        // Clear the text fields
        roomNumberField.setText("");
        startDateField.setText("");
        endDateField.setText("");

        // Display a message
        JOptionPane.showMessageDialog(null, "Reservation successful!");
        
    }
}
