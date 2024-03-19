// Purpose: This file is used to create the panel for the reserve room page.
import javax.swing.*;
import java.awt.*;

public class ReserveRoomPanel extends JPanel{

    // Init a default field size
    Dimension fieldSize = new Dimension(200, 50);
    JTextField roomNumberField;
    JTextField startDateField;
    JTextField endDateField;

    ReserveRoomPanel(){
        // Set the layout of the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Set the background color of the panel
        setBackground(new Color(255, 255, 255));

        // Add subpanels to main panel
        add(getReserveRoomTitle());
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
        // Create a panel for the start date
        JPanel startDatePanel = new JPanel();

        // Create the label for the start date
        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setBounds(200, 250, 200, 50);
        startDateLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Init the text field for the start date
        startDateField = new JTextField();
        startDateField.setBounds(400, 250, 200, 50);
        startDateField.setFont(new Font("Arial", Font.PLAIN, 20));
        startDateField.setPreferredSize(fieldSize);
        startDateField.setMaximumSize(fieldSize);

        startDatePanel.add(startDateLabel);
        startDatePanel.add(startDateField);
        return startDatePanel;
    }

    public JPanel getEndDatePanel() {
        // Create a panel for the end date
        JPanel endDatePanel = new JPanel();

        // Create the label for the end date
        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setBounds(200, 350, 200, 50);
        endDateLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Init the text field for the end date
        endDateField = new JTextField();
        endDateField.setBounds(400, 350, 200, 50);
        endDateField.setFont(new Font("Arial", Font.PLAIN, 20));
        endDateField.setPreferredSize(fieldSize);
        endDateField.setMaximumSize(fieldSize);

        endDatePanel.add(endDateLabel);
        endDatePanel.add(endDateField);
        return endDatePanel;
    }

    public JButton getReserveButton() {
        JButton reserveButton = new JButton("Reserve");
        reserveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reserveButton.setFont(new Font("Arial", Font.PLAIN, 20));
        reserveButton.setOpaque(true);
        reserveButton.setBorderPainted(false);
        reserveButton.setBackground(new Color(0, 0, 153));
        reserveButton.setForeground(new Color(255, 255, 255));

        reserveButton.addActionListener(new ReserveActionListener(roomNumberField, startDateField, endDateField));

        return reserveButton;
    }
}
