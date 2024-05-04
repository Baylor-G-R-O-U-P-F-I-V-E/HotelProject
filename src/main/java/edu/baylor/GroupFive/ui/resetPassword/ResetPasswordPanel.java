package edu.baylor.GroupFive.ui.resetPassword;

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
 * Represents a panel for resetting the password of a user.
 * Extends {@link javax.swing.JPanel} and implements {@link PagePanel}.
 * 
 * @see PagePanel
 *
 * @author Brendon
 */
public class ResetPasswordPanel extends JPanel implements PagePanel {

    @SuppressWarnings("unused")
    private Page page;
    private JPanel textPanel;
    private JTextField usernameField, newPasswordField;

    /**
     * Constructs a ResetPasswordPanel object with the specified page.
     * 
     * @param page The page associated with the panel.
     */
    public ResetPasswordPanel(Page page) {
        super();
        this.page = page;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setVisible(true);
        setOpaque(true);
        setBackground(page.getContentPane().getBackground());

        JLabel titleLabel = new JLabel("Reset User Password");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        // Add some glue
        add(Box.createVerticalGlue());

        // Add the title
        add(titleLabel);

        // Add some glue
        add(Box.createVerticalGlue());

        addUsernamePanel(textPanel);
        addNewPasswordPanel(textPanel);

        add(textPanel);

        // Add some glue
        add(Box.createVerticalGlue());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);

        addResetButton(buttonPanel);

        add(buttonPanel);

        // Add some glue
        add(Box.createVerticalGlue());
        
    }

    /**
     * Adds a panel for entering the username of the account to reset.
     * @param panel
     */
    private void addUsernamePanel(JPanel panel) {
        JPanel usernamePanel = new JPanel();
        usernamePanel.setOpaque(true);

        usernameField = new JTextField();

        // Style text field
        usernameField.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameField.setOpaque(true);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        usernameField.setEditable(true);
        usernameField.setPreferredSize(new Dimension(200, 50));

        JLabel usernameLabel = new JLabel("Enter username for the account to reset: ");
        usernameLabel.setBounds(200, 50, 200, 50);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        usernamePanel.add(usernameLabel);
        usernamePanel.add(usernameField);

        panel.add(usernamePanel);
    }

    /**
     * Adds a panel for entering the new password.
     * @param panel
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
     * Adds a button for resetting the password.
     * @param panel
     */
    private void addResetButton(JPanel panel) {
        PanelButton resetButton = new PanelButton("Reset Password");

        resetButton.addActionListener(e -> {

            if (usernameField.getText().isEmpty() || newPasswordField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Reset the password
            User user = AccountController.getUser(usernameField.getText());

            if (user == null) {
                JOptionPane.showMessageDialog(this, "No user found with that username", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            user.setPasswordHash(CoreUtils.hashPassword(newPasswordField.getText()));
            Boolean result = AccountController.modifyAccount(user);

            if (!result) {
                JOptionPane.showMessageDialog(this, "An error occurred while resetting the password.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Password successfully reset.", "Success", JOptionPane.INFORMATION_MESSAGE);
            clear();
        });

        panel.add(resetButton);
    }

    /**
     * Clears the text fields.
     */
    @Override
    public void clear() {
        usernameField.setText("");
        newPasswordField.setText("");
    }

}
