package edu.baylor.GroupFive.ui.accountSettings;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.util.logging.Logger;

import edu.baylor.GroupFive.database.controllers.AccountController;
import edu.baylor.GroupFive.models.Account;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.PanelButton;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

public class AccountSettingsPanel extends JPanel implements PagePanel {
    private static final Logger LOGGER = Logger.getLogger(AccountSettingsPanel.class.getName());

    private Page page;
    private User user;
    private JTextField firstNameField, lastNameField, usernameField;
    private JPanel buttonPanel, textPanel;

    public AccountSettingsPanel(Page page, User user) {
        super();
        this.page = page;
        this.user = user;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setVisible(true);
        setOpaque(true);
        setBackground(page.getContentPane().getBackground());

        JLabel titleLabel = new JLabel("Account Info");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        addFirstNamePanel(textPanel);
        addLastNamePanel(textPanel);
        addUsernamePanel(textPanel);
        
        add(Box.createVerticalGlue());
        
        add(titleLabel);

        add(Box.createVerticalGlue());

        add(textPanel);

        // add a button panel
        buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        addModifyButton(buttonPanel);

        add(buttonPanel);

        add(Box.createVerticalGlue());

    }

    private void addUsernamePanel(JPanel textPanel) {
        JPanel userPanel = new JPanel();
        userPanel.setOpaque(true);

        usernameField = new JTextField(user.getUsername());
        usernameField.setEditable(false);

        // Style text field
        usernameField.setFont(new Font("Arial", Font.PLAIN, 20));
        usernameField.setOpaque(false);
        usernameField.setBorder(null);

        JLabel userLabel = new JLabel("Username: ");
        userLabel.setBounds(200, 50, 200, 50);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        userPanel.add(userLabel);
        userPanel.add(usernameField);

        textPanel.add(userPanel);
    }

    private void addFirstNamePanel(JPanel textPanel) {

        // Create a panel for the first name
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(true);

        // Create a text field for the first name
        firstNameField = new JTextField(user.getFirstName());
        firstNameField.setEditable(false);

        // Style the text field
        firstNameField.setFont(new Font("Arial", Font.PLAIN, 20));
        firstNameField.setOpaque(false);
        firstNameField.setBorder(null);

        // Create a label for the first name
        JLabel firstLabel = new JLabel("First name: ");
        firstLabel.setBounds(200, 50, 200, 50);
        firstLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Add the label and text field to the panel
        namePanel.add(firstLabel);
        namePanel.add(firstNameField);

        // Add the panel to the text panel
        textPanel.add(namePanel);
    }

    private void addLastNamePanel(JPanel textPanel) {
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(true);

        lastNameField = new JTextField(user.getLastName());
        lastNameField.setEditable(false);

        lastNameField.setFont(new Font("Arial", Font.PLAIN, 20));
        lastNameField.setOpaque(false);
        lastNameField.setBorder(null);

        JLabel lastLabel = new JLabel("Last name: ");
        lastLabel.setBounds(200, 50, 200, 50);
        lastLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        namePanel.add(lastLabel);
        namePanel.add(lastNameField);

        textPanel.add(namePanel);
    }

    private void addModifyButton(JPanel buttonPanel) {
        // Modify the Modify button
        PanelButton ModifyButton = new PanelButton("Modify Account Info", 300, 50);


        ModifyButton.addActionListener(e -> {
            makeEditable();
        });

        buttonPanel.add(ModifyButton);
    }

    private void makeEditable() {

        // Set textfields to editable
        firstNameField.setEditable(true);
        lastNameField.setEditable(true);
        usernameField.setEditable(true);

        // Make them opaque with borders
        firstNameField.setOpaque(true);
        firstNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        lastNameField.setOpaque(true);
        lastNameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        usernameField.setOpaque(true);
        usernameField.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Increase the size of the textfields
        firstNameField.setPreferredSize(new Dimension(200, 50));
        lastNameField.setPreferredSize(new Dimension(200, 50));
        usernameField.setPreferredSize(new Dimension(200, 50));

        // Set focus to first name field
        firstNameField.requestFocus();

        // Remove modify button
        buttonPanel.removeAll();

        // Add save and cancel buttons
        addSaveButton(buttonPanel);
        addBackButton(buttonPanel);

        // Refresh the button panel
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void addBackButton(JPanel panel) {

        // Create and style the back button
        PanelButton backButton = new PanelButton("Back");

        // Add action listener to reset the panel
        backButton.addActionListener(e -> {
            clear();
        });

        // Add the back button to the panel
        panel.add(backButton);
    }

    private void addSaveButton(JPanel panel) {

        // Create and style the save button
        PanelButton saveButton = new PanelButton("Save Changes");

        saveButton.addActionListener(e -> {
            
            // Save the changes
            User newUser = new User(firstNameField.getText(), lastNameField.getText(), usernameField.getText(), user.getPasswordHash(), user.getPrivilege().toString());
            newUser.setId(user.getId());

            Boolean success = AccountController.modifyAccount(newUser);

            if (!success) {
                // Pop up a message saying the changes could not be saved
                JOptionPane.showMessageDialog(null, "Changes could not be saved. Please try again.");
                return;
            } else {
                user = AccountController.getUser(newUser.getUsername());
                if (user == null) {
                    JOptionPane.showMessageDialog(null, "Changes could not be saved. Please try again.");
                    LOGGER.severe("User not found in database");
                    user = page.getUser();
                    return;
                }
                page.setUser(user);
                JOptionPane.showMessageDialog(null, "Changes have been saved!");
            }       

            // Reset the panel
            clear();
        });

        // Add the save button to the panel
        panel.add(saveButton);
    }

    @Override
    public void clear() {

        // Set textfields back to original values
        firstNameField.setText(user.getFirstName());
        lastNameField.setText(user.getLastName());
        usernameField.setText(user.getUsername());

        // Set textfields to uneditable
        firstNameField.setEditable(false);
        lastNameField.setEditable(false);
        usernameField.setEditable(false);

        // Set textfields to unopaque
        firstNameField.setOpaque(false);
        lastNameField.setOpaque(false);
        usernameField.setOpaque(false);

        // Set textfields to unbordered
        firstNameField.setBorder(null);
        lastNameField.setBorder(null);
        usernameField.setBorder(null);

        // Reset the size of the textfields
        firstNameField.setPreferredSize(null);
        lastNameField.setPreferredSize(null);
        usernameField.setPreferredSize(null);

        // Remove all buttons
        buttonPanel.removeAll();

        // Add back the modify button
        addModifyButton(buttonPanel);

        // Refresh text fields
        textPanel.revalidate();
        textPanel.repaint();

        // Refresh buttons
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }
}
