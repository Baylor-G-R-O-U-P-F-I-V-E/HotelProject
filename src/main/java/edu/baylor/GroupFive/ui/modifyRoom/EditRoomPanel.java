package edu.baylor.GroupFive.ui.modifyRoom;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import edu.baylor.GroupFive.database.controllers.RoomController;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.enums.BedType;
import edu.baylor.GroupFive.models.enums.Quality;
import edu.baylor.GroupFive.models.enums.Theme;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

/**
 * Panel for editing a room.
 * This panel displays the current information for a room and allows the user to edit it.
 * 
 * What does this need to have?
 * - A text field for the room number
 * - A drop down for the room type
 * - A drop down for the quality
 * - A drop down for the bed type
 * 
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @see edu.baylor.GroupFive.ui.utils.Page
 * 
 * @author Brendon
 */
public class EditRoomPanel extends JPanel implements PagePanel {

    private Page page;
    private Room room;
    private JTextField numberField, bedCountField, priceField;
    private JComboBox<String> roomTypeField, bedTypeField, smokingField, qualityField;
    private JPanel buttonPanel, textPanel;

    /**
     * Constructs an EditRoomPanel with the specified page and room.
     *
     * @param page the page containing this panel.
     * @param room the room to be edited.
     */
    public EditRoomPanel(Page page, Room room) {
        super();
        this.page = page;
        this.room = room;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setVisible(true);
        setOpaque(true);
        setBackground(page.getContentPane().getBackground());

        JLabel titleLabel = new JLabel("Room Info");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        textPanel = new JPanel();
        textPanel.setLayout(new GridLayout(0, 1));
        textPanel.setOpaque(false);
        textPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        addRoomNumberPanel(textPanel);
        addRoomTypePanel(textPanel);
        addQualityPanel(textPanel);
        addBedTypePanel(textPanel);
        addBedCountPanel(textPanel);
        addSmokingPanel(textPanel);
        addPricePanel(textPanel);
        
        add(Box.createVerticalGlue());
        
        add(titleLabel);

        add(Box.createVerticalGlue());

        add(textPanel);

        // add a button panel
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        addSaveButton(buttonPanel);
        addBackButton(buttonPanel);

        add(buttonPanel);

        add(Box.createVerticalGlue());

    }

    /**
     * Adds a panel for entering the room number to the specified panel.
     * @param textPanel
     */
    private void addRoomNumberPanel(JPanel textPanel) {
        JPanel numberPanel = new JPanel();
        numberPanel.setOpaque(true);
        numberPanel.setLayout(new BoxLayout(numberPanel, BoxLayout.X_AXIS));
        numberPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel numberLabel = new JLabel("Room Number: ");
        numberLabel.setFont(new Font("Arial", Font.BOLD, 24));
        numberLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        numberField = new JTextField(String.valueOf(room.getRoomNumber()), 8);
        numberField.setFont(new Font("Arial", Font.PLAIN, 24));
        numberField.setEditable(false);
        numberField.setOpaque(false);
        numberField.setBorder(null);

        numberPanel.add(numberLabel);
        numberPanel.add(numberField);

        textPanel.add(numberPanel);
    }

    /**
     * Adds a panel for entering the room type to the specified panel.
     * @param textPanel
     */
    private void addRoomTypePanel(JPanel textPanel) {
        JPanel roomTypePanel = new JPanel();
        roomTypePanel.setOpaque(true);
        roomTypePanel.setLayout(new BoxLayout(roomTypePanel, BoxLayout.X_AXIS));
        roomTypePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel roomTypeLabel = new JLabel("Theme: ");
        roomTypeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        roomTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        roomTypeField = new JComboBox<>(new String[] { "NatureRetreat", "UrbanElegance", "VintageCharm"});
        roomTypeField.setFont(new Font("Arial", Font.PLAIN, 20));

        // Set the selected item
        roomTypeField.setSelectedItem(String.valueOf(room.getTheme()));

        roomTypePanel.add(roomTypeLabel);
        roomTypePanel.add(roomTypeField);

        textPanel.add(roomTypePanel);
    }

    /**
     * Adds a panel for entering the quality to the specified panel.
     * @param textPanel
     */
    private void addQualityPanel(JPanel textPanel) {
        JPanel qualityPanel = new JPanel();
        qualityPanel.setOpaque(true);
        qualityPanel.setLayout(new BoxLayout(qualityPanel, BoxLayout.X_AXIS));
        qualityPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel qualityLabel = new JLabel("Quality: ");
        qualityLabel.setFont(new Font("Arial", Font.BOLD, 24));
        qualityLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        qualityField = new JComboBox<>(new String[] { "Economy", "Comfort", "Busniess", "Executive" });
        qualityField.setFont(new Font("Arial", Font.PLAIN, 20));

        qualityPanel.add(qualityLabel);
        qualityPanel.add(qualityField);

        textPanel.add(qualityPanel);
    }

    /**
     * Adds a panel for entering the bed type to the specified panel.
     * @param textPanel
     */
    private void addBedTypePanel(JPanel textPanel) {
        JPanel bedTypePanel = new JPanel();
        bedTypePanel.setOpaque(true);
        bedTypePanel.setLayout(new BoxLayout(bedTypePanel, BoxLayout.X_AXIS));
        bedTypePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel bedTypeLabel = new JLabel("Bed Type: ");
        bedTypeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        bedTypeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        bedTypeField = new JComboBox<>(new String[] { "Single", "Double", "Queen", "King" });
        bedTypeField.setFont(new Font("Arial", Font.PLAIN, 20));

        // Set the selected item
        bedTypeField.setSelectedItem(String.valueOf(room.getBedType()));

        bedTypePanel.add(bedTypeLabel);
        bedTypePanel.add(bedTypeField);

        textPanel.add(bedTypePanel);
    }

    /**
     * Adds a panel for entering the bed count to the specified panel.
     * @param textPanel
     */
    private void addBedCountPanel(JPanel textPanel) {
        JPanel bedCountPanel = new JPanel();
        bedCountPanel.setOpaque(true);
        bedCountPanel.setLayout(new BoxLayout(bedCountPanel, BoxLayout.X_AXIS));
        bedCountPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel bedCountLabel = new JLabel("Bed Count: ");
        bedCountLabel.setFont(new Font("Arial", Font.BOLD, 24));
        bedCountLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        bedCountField = new JTextField(String.valueOf(room.getNumBeds()), 8);
        bedCountField.setFont(new Font("Arial", Font.PLAIN, 24));
        bedCountField.setEditable(true);
        bedCountField.setOpaque(true);
        bedCountField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        bedCountPanel.add(bedCountLabel);
        bedCountPanel.add(bedCountField);

        textPanel.add(bedCountPanel);
    }

    /**
     * Adds a panel for entering the smoking status to the specified panel.
     * @param textPanel
     */
    private void addSmokingPanel(JPanel textPanel) {
        JPanel smokingPanel = new JPanel();
        smokingPanel.setOpaque(true);
        smokingPanel.setLayout(new BoxLayout(smokingPanel, BoxLayout.X_AXIS));
        smokingPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel smokingLabel = new JLabel("Smoking: ");
        smokingLabel.setFont(new Font("Arial", Font.BOLD, 24));
        smokingLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        smokingField = new JComboBox<>(new String[] { "true", "false"});
        smokingField.setFont(new Font("Arial", Font.PLAIN, 20));

        // Set the selected item
        smokingField.setSelectedItem(String.valueOf(room.isSmoking()).toLowerCase());

        smokingPanel.add(smokingLabel);
        smokingPanel.add(smokingField);

        textPanel.add(smokingPanel);
    }

    /**
     * Adds a panel for entering the price to the specified panel.
     * @param textPanel
     */
    private void addPricePanel(JPanel textPanel) {
        JPanel pricePanel = new JPanel();
        pricePanel.setOpaque(true);
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.X_AXIS));
        pricePanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel priceLabel = new JLabel("Price per Night: ");
        priceLabel.setFont(new Font("Arial", Font.BOLD, 24));
        priceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        priceField = new JTextField(String.valueOf(room.getDailyPrice()), 8);
        priceField.setFont(new Font("Arial", Font.PLAIN, 24));
        priceField.setEditable(true);
        priceField.setOpaque(true);
        priceField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        pricePanel.add(priceLabel);
        pricePanel.add(priceField);

        textPanel.add(pricePanel);
    }

    /**
     * Adds a back button to the specified panel.
     * @param panel
     */
    private void addBackButton(JPanel panel) {

        // Create and style the back button
        PanelButton backButton = new PanelButton("Back");

        // Add action listener to reset the panel
        backButton.addActionListener(e -> {
            clear();
            page.onPageSwitch("modify-rooms");
        });

        // Add the back button to the panel
        panel.add(backButton);
    }

    /**
     * Adds a save button to the specified panel.
     * @param panel
     */
    private void addSaveButton(JPanel panel) {

        // Create and style the save button
        PanelButton saveButton = new PanelButton("Save Changes");

        saveButton.addActionListener(e -> {
            // Save the changes
            saveChanges();
        });

        // Add the save button to the panel
        panel.add(saveButton);
    }

    /**
     * Saves the changes made to the room.
     */
    private void saveChanges() {
        // Get the values from the text fields
        int roomNumber = Integer.parseInt(numberField.getText());
        Theme theme = Theme.valueOf((String) roomTypeField.getSelectedItem());
        Quality quality = Quality.fromString(String.valueOf(qualityField.getSelectedItem()));
        BedType bedType = BedType.fromString((String) bedTypeField.getSelectedItem());
        int numBeds = Integer.parseInt(bedCountField.getText());
        boolean smoking = Boolean.parseBoolean((String) smokingField.getSelectedItem());
        double dailyPrice = Double.parseDouble(priceField.getText());

        // Create a new room object
        Room newRoom = new Room(roomNumber, quality, theme, smoking, numBeds, bedType, dailyPrice);

        // Update the room in the database
        Boolean result = RoomController.modifyRoom(newRoom);

        // Check if the update was successful
        if (!result) {
            JOptionPane.showMessageDialog(this, "Failed to update room in database.");
            return;
        } else {
            JOptionPane.showMessageDialog(this, "Room updated successfully.");
        }

        // Refresh the table
        page.onPageSwitch("modify-rooms");
        page.refresh();
    }

    /**
     * Clears the input fields.
     */
    @Override
    public void clear() {
        // Set textfields back to original values
        roomTypeField.setSelectedItem(String.valueOf(room.getTheme()));
        qualityField.setSelectedItem(String.valueOf(room.getQuality()));
        bedTypeField.setSelectedItem(String.valueOf(room.getBedType()));
        bedCountField.setText(String.valueOf(room.getNumBeds()));
        smokingField.setSelectedItem(String.valueOf(room.isSmoking()).toLowerCase());
        priceField.setText(String.valueOf(room.getDailyPrice()));
    }
}
