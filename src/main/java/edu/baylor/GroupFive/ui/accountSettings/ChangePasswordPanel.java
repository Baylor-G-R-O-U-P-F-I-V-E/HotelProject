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

public class ChangePasswordPanel extends JPanel implements PagePanel {

    private Page page;
    private User user;
    private JPanel textPanel;
    private JTextField currentPasswordField, newPasswordField;

    public ChangePasswordPanel(Page page, User user) {
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

        JLabel userLabel = new JLabel("Enter current password: ");
        userLabel.setBounds(200, 50, 200, 50);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        currentPasswordPanel.add(userLabel);
        currentPasswordPanel.add(currentPasswordField);

        panel.add(currentPasswordPanel);
    }

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

        JLabel userLabel = new JLabel("Enter new password: ");
        userLabel.setBounds(200, 50, 200, 50);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        newPasswordPanel.add(userLabel);
        newPasswordPanel.add(newPasswordField);

        panel.add(newPasswordPanel);
    }

    private void addSaveButton(JPanel panel) {

        // Create and style the save button
        PanelButton saveButton = new PanelButton("Save Changes");

        saveButton.addActionListener(e -> {
            
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

    private void addBackButton(JPanel panel) {

        // Create and style the back button
        PanelButton backButton = new PanelButton("Back");

        backButton.addActionListener(e -> {
            page.onPageSwitch("account-settings");
        });

        // Add the back button to the panel
        panel.add(backButton);
    }

    @Override
    public void clear() {
        currentPasswordField.setText("");
        newPasswordField.setText("");
    }

}
