package edu.baylor.GroupFive.ui.accountSettings;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

public class AccountSettingsPanel extends JPanel implements PagePanel {

    private Page page;
    private User user;
    private JTextField firstNameField, lastNameField, usernameField;

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

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        addFirstNamePanel(textPanel);
        addLastNamePanel(textPanel);
        addUsernamePanel(textPanel);
        
        add(Box.createVerticalGlue());
        
        add(titleLabel);

        add(Box.createVerticalGlue());

        add(textPanel);

        // add a button panel
        JPanel buttonPanel = new JPanel();
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
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(true);

        firstNameField = new JTextField(user.getFirstName());
        firstNameField.setEditable(false);

        firstNameField.setFont(new Font("Arial", Font.PLAIN, 20));
        firstNameField.setOpaque(false);
        firstNameField.setBorder(null);

        JLabel firstLabel = new JLabel("First name: ");
        firstLabel.setBounds(200, 50, 200, 50);
        firstLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        namePanel.add(firstLabel);
        namePanel.add(firstNameField);

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
        JButton ModifyButton = new JButton("Modify Account");
        ModifyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        ModifyButton.setPreferredSize(new Dimension(200, 50));
        ModifyButton.setFont(new Font("Arial", Font.PLAIN, 22));
        ModifyButton.setOpaque(true);
        ModifyButton.setBorderPainted(false);
        ModifyButton.setBackground(new Color(0, 0, 153));
        ModifyButton.setForeground(new Color(255, 255, 255));

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

        // Set focus to first name field
        firstNameField.requestFocus();

        // Remove modify button
        this.remove(this.getComponentCount() - 1);

        // Add save and cancel buttons
        addSaveButton(this);
        addBackButton(this);
    }

    private void addBackButton(JPanel panel) {
        JButton backButton = new JButton("Cancel");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFont(new Font("Arial", Font.PLAIN, 22));
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setBackground(new Color(0, 0, 153));
        backButton.setForeground(new Color(255, 255, 255));

        backButton.addActionListener(e -> {
            clear();
        });

        panel.add(backButton);
    }

    private void addSaveButton(JPanel panel) {
        JButton saveButton = new JButton("Save Changes");
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setPreferredSize(new Dimension(200, 50));
        saveButton.setFont(new Font("Arial", Font.PLAIN, 22));
        saveButton.setOpaque(true);
        saveButton.setBorderPainted(false);
        saveButton.setBackground(new Color(0, 0, 153));
        saveButton.setForeground(new Color(255, 255, 255));

        saveButton.addActionListener(e -> {
            
            // Save the changes
            User newUser = new User(firstNameField.getText(), lastNameField.getText(), usernameField.getText(), user.getPasswordHash(), user.getPrivilege().toString());


            // Set textfields back to original values
            firstNameField.setText(user.getFirstName());
            lastNameField.setText(user.getLastName());
            usernameField.setText(user.getUsername());

            // Set textfields to uneditable
            firstNameField.setEditable(false);
            lastNameField.setEditable(false);
            usernameField.setEditable(false);
        });

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


    }
}
