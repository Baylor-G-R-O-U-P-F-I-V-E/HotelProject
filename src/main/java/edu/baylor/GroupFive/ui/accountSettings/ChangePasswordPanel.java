package edu.baylor.GroupFive.ui.accountSettings;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import edu.baylor.GroupFive.database.controllers.AccountController;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import edu.baylor.GroupFive.util.CoreUtils;

/**
 * Represents a panel for changing the password of a user.
 * Extends {@link javax.swing.JPanel} and implements {@link PagePanel}.
 * 
 * @see PagePanel
 * 
 * @author Brendon
 */
public class ChangePasswordPanel extends JPanel implements PagePanel {

    private Page page;
    private User user;
    private JPanel textPanel;
    private JTextField currentPasswordField, newPasswordField;

    /**
     * Constructs a ChangePasswordPanel object with the specified page and user.
     * 
     * @param page The page associated with the panel.
     * @param user The user associated with the panel.
     */
    public ChangePasswordPanel(Page page, User user) {
        super();
        this.page = page;
        this.user = user;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setVisible(true);
        setOpaque(true);
        setBackground(page.getContentPane().getBackground());

        JLabel titleLabel = new JLabel("Change Password");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        // Add some glue
        add(Box.createVerticalGlue());

        // Add the title
        add(titleLabel);

        // Add some glue
        add(Box.createVerticalGlue());

        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        addEnterCurrentPasswordPanel(textPanel);
        addNewPasswordPanel(textPanel);
        
        add(textPanel);

        // Add some glue
        add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        addSaveButton(buttonPanel);
        addBackButton(buttonPanel);

        add(buttonPanel);

        // Add some glue
        add(Box.createVerticalGlue());
    }

    /**
     * Adds a panel for entering the current password.
     * @param panel The panel to add the current password panel to.
     */
    private void addEnterCurrentPasswordPanel(JPanel panel) {
        JPanel currentPasswordPanel = new JPanel();
        currentPasswordPanel.setOpaque(true);

        currentPasswordField = new JTextField();

        // Style text field
        currentPasswordField.setFont(new Font("Arial", Font.PLAIN, 20));
        currentPasswordField.setOpaque(true);
        currentPasswordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        currentPasswordField.setEditable(true);
        currentPasswordField.setPreferredSize(new Dimension(200, 50));

        JLabel currentPasswordLabel = new JLabel("Enter current password: ");
        currentPasswordLabel.setBounds(200, 50, 200, 50);
        currentPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        currentPasswordPanel.add(currentPasswordLabel);
        currentPasswordPanel.add(currentPasswordField);

        panel.add(currentPasswordPanel);
    }

    /**
     * Adds a panel for entering the new password.
     * @param panel The panel to add the new password panel to.
     */
    private void addNewPasswordPanel(JPanel panel) {
        JPanel newPasswordPanel = new JPanel();
        newPasswordPanel.setOpaque(true);

        newPasswordField = new JTextField();

        // Style text field
        newPasswordField.setFont(new Font("Arial", Font.PLAIN, 20));
        newPasswordField.setOpaque(true);
        newPasswordField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        newPasswordField.setEditable(true);
        newPasswordField.setPreferredSize(new Dimension(200, 50));

        JLabel newPasswordLabel = new JLabel("Enter new password: ");
        newPasswordLabel.setBounds(200, 50, 200, 50);
        newPasswordLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        newPasswordPanel.add(newPasswordLabel);
        newPasswordPanel.add(newPasswordField);

        panel.add(newPasswordPanel);
    }

    /**
     * Adds a save button to the panel.
     * @param panel The panel to add the save button to.
     */
    private void addSaveButton(JPanel panel) {

        // Create and style the save button
        PanelButton saveButton = new PanelButton("Save Changes");

        saveButton.addActionListener(e -> {

            if (currentPasswordField.getText().length() < 1 || newPasswordField.getText().length() < 1) {
                JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Check if the current password is correct
            if (!user.getPasswordHash().equals(CoreUtils.hashPassword(currentPasswordField.getText()))) {
                JOptionPane.showMessageDialog(this, "Incorrect current password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check if the new password is valid
            if (newPasswordField.getText().length() < 1) {
                JOptionPane.showMessageDialog(this, "No new password entered.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Update the user's password
            user.setPasswordHash(CoreUtils.hashPassword(newPasswordField.getText()));
            Boolean result = AccountController.modifyAccount(user);

            if (result) {
                JOptionPane.showMessageDialog(this, "Password successfully changed.", "Success", JOptionPane.INFORMATION_MESSAGE);
                page.onPageSwitch("account-settings");
            } else {
                JOptionPane.showMessageDialog(this, "An error occurred while changing the password.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        });

        // Add the save button to the panel
        panel.add(saveButton);
    }

    /**
     * Adds a back button to the panel.
     * @param panel The panel to add the back button to.
     */
    private void addBackButton(JPanel panel) {

        // Create and style the back button
        PanelButton backButton = new PanelButton("Back");

        backButton.addActionListener(e -> {
            page.onPageSwitch("account-settings");
        });

        // Add the back button to the panel
        panel.add(backButton);
    }

    /**
     * Clears the text fields in the panel.
     */
    @Override
    public void clear() {
        currentPasswordField.setText("");
        newPasswordField.setText("");
    }

}
