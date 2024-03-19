// Purpose: This file is used to create the panel for the reserve room page.
import javax.swing.*;
import java.awt.*;

public class ReserveRoomPanel extends JPanel{
    ReserveRoomPanel(){
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(new Color(255, 255, 255));

        // Create the title for the reserve room page
        JLabel reserveRoomTitle = new JLabel("Reserve a Room");
        reserveRoomTitle.setBounds(300, 50, 200, 50);
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

        // Create the label for the date
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(200, 250, 200, 50);
        dateLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create the text field for the date
        JTextField dateField = new JTextField();
        dateField.setBounds(400, 250, 200, 50);
        dateField.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create the label for the time
        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setBounds(200, 350, 200, 50);
        timeLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create the text field for the time
        JTextField timeField = new JTextField();
        timeField.setBounds(400, 350, 200, 50);
        timeField.setFont(new Font("Arial", Font.PLAIN, 20));

        // Create the button to reserve the room
        JButton reserveButton = new JButton("Reserve");
        reserveButton.setBounds(300, 450, 200, 50);
        reserveButton.setFont(new Font("Arial", Font.PLAIN, 20));
        reserveButton.setOpaque(true);
        reserveButton.setBorderPainted(false);
        reserveButton.setBackground(new Color(0, 0, 153));
        reserveButton.setForeground(new Color(255, 255, 255));

        // Add the components to the panel
        add(reserveRoomTitle);

        roomNumberPanel.add(roomNumberLabel);
        roomNumberPanel.add(roomNumberField);
        
        add(roomNumberPanel);
        add(dateLabel);
        add(dateField);
        add(timeLabel);
        add(timeField);
        add(reserveButton);

        // Set the panel to be visible
        setVisible(true);

        //Set size of the panel
        setSize(800, 600);
    }
}
