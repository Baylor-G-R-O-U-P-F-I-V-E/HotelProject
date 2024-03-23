// Purpose: This file is used to create the panel for the reserve room page.
package edu.baylor.GroupFive.ui.reserveRoom;

import javax.swing.*;

import org.jdatepicker.impl.JDatePickerImpl;

import edu.baylor.GroupFive.ui.utils.DatePanel;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

import java.awt.*;

public class ReserveRoomPanel extends JPanel implements PagePanel {

    // Init a default field size
    Dimension fieldSize = new Dimension(200, 50);
    JTextField nameField;
    JTextField roomNumberField;
    DatePanel startDate;
    DatePanel endDate;

    public ReserveRoomPanel(){
        // Set the layout of the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set the background color of the panel
        setBackground(new Color(255, 255, 255));

        // Add subpanels to main panel
        add(getReserveRoomTitle());
        add(getNamePanel());
        add(getRoomNumberPanel());
        add(getStartDatePanel());
        add(getEndDatePanel());

        // Add the reserve button to the panel
        add(getReserveButton());

        // Set the panel to be visible
        setVisible(true);

        //Set size of the panel
        setSize(800, 600);
    }

    public JLabel getReserveRoomTitle() {
        JLabel reserveRoomTitle = new JLabel("Reserve a Room");
        reserveRoomTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        reserveRoomTitle.setFont(new Font("Arial", Font.PLAIN, 20));
        return reserveRoomTitle;
    }

    public JPanel getNamePanel () {
        //Create a new panel for the name
        JPanel namePanel = new JPanel();

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(200, 50, 200, 50);
        nameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        nameField = new JTextField();
        nameField.setBounds(400, 50, 200, 50);
        nameField.setFont(new Font("Arial", Font.PLAIN, 20));
        nameField.setPreferredSize(fieldSize);
        nameField.setMaximumSize(fieldSize);

        namePanel.add(nameLabel);
        namePanel.add(nameField);

        return namePanel;
    }

    public JPanel getRoomNumberPanel() {
        // Create a panel for the room number
        JPanel roomNumberPanel = new JPanel();

        // Create the label for the room number
        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setBounds(200, 150, 200, 50);
        roomNumberLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Init the text field for the room number
        roomNumberField = new JTextField();
        roomNumberField.setBounds(400, 150, 200, 50);
        roomNumberField.setFont(new Font("Arial", Font.PLAIN, 20));
        roomNumberField.setPreferredSize(fieldSize);
        roomNumberField.setMaximumSize(fieldSize);

        roomNumberPanel.add(roomNumberLabel);
        roomNumberPanel.add(roomNumberField);
        return roomNumberPanel;
    }

    public JPanel getStartDatePanel() {
        startDate = new DatePanel("Start Date:");
        return startDate;
    }

    public JDatePickerImpl getDate(DatePanel panel) {
        for (Component component : panel.getComponents()) {
            if (component instanceof JDatePickerImpl) {
                return (JDatePickerImpl) component;
            }
        }
        return null;
    }

    public JPanel getEndDatePanel() {
        endDate = new DatePanel("End Date:", 1);
        return endDate;
    }

    public JButton getReserveButton() {
        JButton reserveButton = new JButton("Reserve");
        reserveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reserveButton.setFont(new Font("Arial", Font.PLAIN, 20));
        reserveButton.setOpaque(true);
        reserveButton.setBorderPainted(false);
        reserveButton.setBackground(new Color(0, 0, 153));
        reserveButton.setForeground(new Color(255, 255, 255));

        reserveButton.addActionListener(new ReserveActionListener(nameField, roomNumberField, getDate(startDate), getDate(endDate)));

        return reserveButton;
    }

    @Override
    public void clear() {
        nameField.setText("");
        roomNumberField.setText("");
        startDate.clear();
        endDate.clear();
    }
}
