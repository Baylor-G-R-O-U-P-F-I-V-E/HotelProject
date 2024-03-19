// Purpose: This file is used to create the panel for the reserve room page.
import javax.swing.*;
import java.awt.*;

public class ReserveRoomPanel extends JPanel{
    ReserveRoomPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 255, 255));

        // Init a default field size
        Dimension fieldSize = new Dimension(200, 50);

        // Create the title for the reserve room page
        JLabel reserveRoomTitle = new JLabel("Reserve a Room");
        reserveRoomTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        reserveRoomTitle.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create a panel for the room number
        JPanel roomNumberPanel = new JPanel();

        // Create the label for the room number
        JLabel roomNumberLabel = new JLabel("Room Number:");
        roomNumberLabel.setBounds(200, 150, 200, 50);
        roomNumberLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create the text field for the room number
        JTextField roomNumberField = new JTextField();
        roomNumberField.setBounds(400, 150, 200, 50);
        roomNumberField.setFont(new Font("Arial", Font.PLAIN, 20));
        roomNumberField.setPreferredSize(fieldSize);
        roomNumberField.setMaximumSize(fieldSize);

        // Create a panel for the date
        JPanel startDatePanel = new JPanel();

        // Create the label for the date
        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setBounds(200, 250, 200, 50);
        startDateLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create the text field for the date
        JTextField startDateField = new JTextField();
        startDateField.setBounds(400, 250, 200, 50);
        startDateField.setFont(new Font("Arial", Font.PLAIN, 20));
        startDateField.setPreferredSize(fieldSize);
        startDateField.setMaximumSize(fieldSize);

        // Create a panel for the time
        JPanel endDatePanel = new JPanel();

        // Create the label for the time
        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setBounds(200, 350, 200, 50);
        endDateLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create the text field for the time
        JTextField endDateField = new JTextField();
        endDateField.setBounds(400, 350, 200, 50);
        endDateField.setFont(new Font("Arial", Font.PLAIN, 20));
        endDateField.setPreferredSize(fieldSize);
        endDateField.setMaximumSize(fieldSize);

        // Create the button to reserve the room
        JButton reserveButton = new JButton("Reserve");
        reserveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reserveButton.setFont(new Font("Arial", Font.PLAIN, 20));
        reserveButton.setOpaque(true);
        reserveButton.setBorderPainted(false);
        reserveButton.setBackground(new Color(0, 0, 153));
        reserveButton.setForeground(new Color(255, 255, 255));

        // Add the components to the panel
        add(reserveRoomTitle);

        // Add the room number panel to the panel
        roomNumberPanel.add(roomNumberLabel);
        roomNumberPanel.add(roomNumberField);
        add(roomNumberPanel);

        // Add the date panel to the panel
        startDatePanel.add(startDateLabel);
        startDatePanel.add(startDateField);
        add(startDatePanel);

        // Add the time panel to the panel
        endDatePanel.add(endDateLabel);
        endDatePanel.add(endDateField);
        add(endDatePanel);

        // Add the reserve button to the panel
        add(reserveButton);

        // Set the panel to be visible
        setVisible(true);

        //Set size of the panel
        setSize(800, 600);
    }
}
