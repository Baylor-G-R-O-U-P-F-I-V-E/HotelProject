package edu.baylor.GroupFive.ui.landing.login;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.baylor.GroupFive.ui.landing.LandingPage;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.Color;

/**
 *
 */
public class LoginPanel extends JPanel implements PagePanel {

    private Dimension fieldSize = new Dimension(200, 50);
    private GridBagConstraints gbc = new GridBagConstraints();
    private JTextField nameField;
    private JTextField passwordField;
    private LandingPage delegate;
    public String username;
    public String passwordHash;

    /**
     *
     * @param delegate
     */
    public LoginPanel(LandingPage delegate) {
        super();
        this.delegate = delegate;
        // Set the layout of the panel
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);
        add(Box.createVerticalStrut(500));

        JPanel textPanel = new JPanel();
        textPanel.setOpaque(false);

        addNamePanel(textPanel);
        addPasswordPanel(textPanel);

        add(textPanel);

        // Add the reserve button to the panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        addLoginButton(buttonPanel);
        addBackButton(buttonPanel);

        add(buttonPanel);

        // Set the panel to be visible
        setVisible(true);

        // Set size of the panel
        setSize(400, 400);
    }

    /**
     *
     * @param textPanel
     */
    public void addNamePanel(JPanel textPanel) {
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

        textPanel.add(namePanel, gbc);
    }

    /**
     *
     * @param textPanel
     */
    public void addPasswordPanel(JPanel textPanel) {
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

        textPanel.add(passwordPanel, gbc);
    }

    /**
     *
     * @param buttonPanel
     */
    public void addLoginButton(JPanel buttonPanel) {
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

        buttonPanel.add(loginButton);
    }

    /**
     *
     * @param buttonPanel
     */
    public void addBackButton(JPanel buttonPanel) {
        JButton backButton = new JButton("Back");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFont(new Font("Arial", Font.PLAIN, 22));
        backButton.setOpaque(true);
        backButton.setBorderPainted(false);
        backButton.setBackground(new Color(0, 0, 153));
        backButton.setForeground(new Color(255, 255, 255));

        // Add an action listener to the button
        backButton.addActionListener(e -> {
            delegate.onPageSwitch("cancel");
        });

        buttonPanel.add(backButton);
    }

    /**
     *
     * @param username
     * @param password
     */
    public void onClick(String username, String password) {
        delegate.setUsername(username);

        delegate.onPageSwitch("success");
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return passwordHash;
    }

    /**
     *
     */
    @Override
    public void clear() {
        // Clears textfields
        nameField.setText("");
        passwordField.setText("");
    }
}
