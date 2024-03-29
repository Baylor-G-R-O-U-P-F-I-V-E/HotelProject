package edu.baylor.GroupFive.ui.login;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import java.awt.Insets;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Color;

public class LoginPanel extends JPanel implements PagePanel {

    private Dimension fieldSize = new Dimension(200, 50);
    private GridBagConstraints gbc = new GridBagConstraints();
    private JTextField nameField;
    private JTextField passwordField;
    private LoginPage delegate;
    public String username;
    public String password;

    public LoginPanel(LoginPage delegate) {
        super();
        this.delegate = delegate;
        // Set the layout of the panel
        setLayout(new GridBagLayout());
        setOpaque(false);

        addNamePanel();
        addPasswordPanel();

        // Add the reserve button to the panel
        addLoginButton();

        // Set the panel to be visible
        setVisible(true);

        // Set size of the panel
        setSize(400, 400);
    }

    public void addNamePanel() {
        // Create a new panel for the name
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(true);

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

        // Set the constraints for the name panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(0, 250, 100, 0);

        add(namePanel, gbc);
    }

    public void addPasswordPanel() {
        // Create a panel for the room number
        JPanel passwordPanel = new JPanel();
        passwordPanel.setOpaque(true);

        // Create the label for the room number
        JLabel roomNumberLabel = new JLabel("Password:");
        roomNumberLabel.setBounds(200, 150, 200, 50);
        roomNumberLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        // Init the text field for the room number
        passwordField = new JTextField();
        passwordField.setBounds(400, 150, 200, 50);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 20));
        passwordField.setPreferredSize(fieldSize);
        passwordField.setMaximumSize(fieldSize);

        passwordPanel.add(roomNumberLabel);
        passwordPanel.add(passwordField);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.insets = new Insets(0, 0, 100, 250);

        add(passwordPanel, gbc);
    }

    public void addLoginButton() {
        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setPreferredSize(new Dimension(200, 50));
        loginButton.setFont(new Font("Arial", Font.PLAIN, 22));
        loginButton.setOpaque(true);
        loginButton.setBorderPainted(false);
        loginButton.setBackground(new Color(0, 0, 153));
        loginButton.setForeground(new Color(255, 255, 255));

        // Add an action listener to the button
        loginButton.addActionListener(new LoginActionListener(delegate, nameField, passwordField));

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 100, 600);

        add(loginButton, gbc);
    }

    public void onClick(String username, String password) {
        delegate.setUsername(username);
        delegate.setPassword(password);

        delegate.onPageSwitch("success");
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public void clear() {
        // Clears textfields
        nameField.setText("");
        passwordField.setText("");
    }
}
