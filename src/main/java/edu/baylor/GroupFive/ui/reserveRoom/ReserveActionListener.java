package edu.baylor.GroupFive.ui.reserveRoom;

import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.*;

import org.jdatepicker.impl.JDatePickerImpl;

public class ReserveActionListener implements ActionListener {
    private JTextField roomNumberField;
    private JDatePickerImpl startDatePicker;
    private JDatePickerImpl endDatePicker;
    private JTextField nameField;

    public ReserveActionListener(JTextField nameField, JTextField roomNumberField, JDatePickerImpl startDate,
            JDatePickerImpl endDate) {
        this.nameField = nameField;
        this.roomNumberField = roomNumberField;
        this.startDatePicker = startDate;
        this.endDatePicker = endDate;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a default message
        String message = "Reservation successful!";

        // Get the name
        String name = nameField.getText();
        if (name.isEmpty()) {
            message = "Please enter a name";
            try {
                getBadInputDialog(message);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        // Get the room number
        String roomNumber = roomNumberField.getText();
        if (roomNumber.isEmpty()) {
            message = "Please enter a room number";
            try {
                getBadInputDialog(message);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        // Get the selected start date
        int startYear = startDatePicker.getModel().getYear();
        int startMonth = startDatePicker.getModel().getMonth();
        int startDay = startDatePicker.getModel().getDay();
        Date startDate = Date.from(LocalDate.of(startYear, startMonth + 1, startDay).atStartOfDay().toInstant(ZoneOffset.UTC));

        // Get the selected end date
        int endYear = endDatePicker.getModel().getYear();
        int endMonth = endDatePicker.getModel().getMonth();
        int endDay = endDatePicker.getModel().getDay();
        Date endDate = Date.from(LocalDate.of(endYear, endMonth + 1, endDay).atStartOfDay().toInstant(ZoneOffset.UTC));

        // Check if the start date is before the end date
        if (startDate.after(endDate)) {
            message = "The start date must be before the end date";
            try {
                getBadInputDialog(message);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        // Convert dates to strings
        //String startDateString = startDate.toString();
        //String endDateString = endDate.toString();

        // Clear the text fields
        nameField.setText("");
        roomNumberField.setText("");

        // Set date pickers back to default dates
        Calendar cal = Calendar.getInstance();
        startDatePicker.getModel().setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        startDatePicker.getModel().setSelected(true);
        cal.add(Calendar.DATE, 1);
        endDatePicker.getModel().setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        endDatePicker.getModel().setSelected(true);

        // Display a message
        JOptionPane.showMessageDialog(null, message);

    }

    public void getBadInputDialog(String message) throws IOException {
        BufferedImage badImage = ImageIO.read(new File("src/main/resources/dialog-icons/mad-guy.png"));
        ImageIcon badIcon = new ImageIcon(badImage);

        JOptionPane.showMessageDialog(null, message, "Reservation not made", JOptionPane.INFORMATION_MESSAGE, badIcon);
    }
}
