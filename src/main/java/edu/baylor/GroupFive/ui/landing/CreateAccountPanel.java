package edu.baylor.GroupFive.ui.landing;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

public class CreateAccountPanel extends JPanel implements PagePanel {

    private LandingPage delegate;
    private Dimension fieldSize = new Dimension(200, 50);
    private JTextField firstName, lastName, username, password;

    public CreateAccountPanel(LandingPage delegate) {
        super();
        this.delegate = delegate;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        // Add a vertical strut to create vertical space at the top
        add(Box.createVerticalStrut(500)); // Adjust the value as needed

        JPanel textPanel = new JPanel();

        addFirstNamePanel(textPanel);
        addLastNamePanel(textPanel);
        addUsernamePanel(textPanel);
        addPasswordPanel(textPanel);

        textPanel.setOpaque(false);

        add(textPanel);

        JPanel buttonPanel = new JPanel();

        addCreateAccountButton(buttonPanel);
        addBackButton(buttonPanel);

        buttonPanel.setOpaque(false);

        add(buttonPanel);

        setVisible(true);

        setSize(400, 400);

    }

    public void addFirstNamePanel(JPanel textPanel) {
        JPanel firstNamePanel = new JPanel();
        firstNamePanel.setOpaque(true);

        JLabel firstNameLabel = new JLabel("First Name:");
        firstNameLabel.setBounds(200, 50, 200, 50);
        firstNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        firstName = new JTextField();
        firstName.setBounds(400, 50, 200, 50);
        firstName.setFont(new Font("Arial", Font.PLAIN, 20));
        firstName.setPreferredSize(fieldSize);

        firstNamePanel.add(firstNameLabel);
        firstNamePanel.add(firstName);

        textPanel.add(firstNamePanel);
    }

    public void addLastNamePanel(JPanel textPanel) {
        JPanel lastNamePanel = new JPanel();
        lastNamePanel.setOpaque(true);

        JLabel lastNameLabel = new JLabel("Last Name:");
        lastNameLabel.setBounds(200, 50, 200, 50);
        lastNameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        lastName = new JTextField();
        lastName.setBounds(400, 50, 200, 50);
        lastName.setFont(new Font("Arial", Font.PLAIN, 20));
        lastName.setPreferredSize(fieldSize);
        lastName.setMaximumSize(fieldSize);

        lastNamePanel.add(lastNameLabel);
        lastNamePanel.add(lastName);

        textPanel.add(lastNamePanel);
    }

    public void addUsernamePanel(JPanel textPanel) {
        JPanel usernamePanel = new JPanel();
        usernamePanel.setOpaque(true);

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(200, 50, 200, 50);
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        username = new JTextField();
        username.setBounds(400, 50, 200, 50);
        username.setFont(new Font("Arial", Font.PLAIN, 20));
        username.setPreferredSize(fieldSize);
        
        usernamePanel.add(usernameLabel);
        usernamePanel.add(username);

        textPanel.add(usernamePanel);
    }

    public void addPasswordPanel(JPanel textPanel) {
        JPanel passwordPanel = new JPanel();
        passwordPanel.setOpaque(true);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(200, 50, 200, 50);
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        password = new JTextField();
        password.setBounds(400, 50, 200, 50);
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setPreferredSize(fieldSize);

        passwordPanel.add(passwordLabel);
        passwordPanel.add(password);

        textPanel.add(passwordPanel);
    }

    public void addCreateAccountButton(JPanel buttonPanel) {
        JButton createButton = new JButton("Create Account");
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.setPreferredSize(new Dimension(200, 50));
        createButton.setFont(new Font("Arial", Font.PLAIN, 22));
        createButton.setOpaque(true);
        createButton.setBorderPainted(false);
        createButton.setBackground(new Color(0, 0, 153));
        createButton.setForeground(new Color(255, 255, 255));

        // Add an action listener to the button
        createButton.addActionListener(new CreateAccountActionListener(delegate, firstName, lastName, username, password));

        buttonPanel.add(createButton);
    }

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
        backButton.addActionListener(e -> delegate.onPageSwitch("cancel"));

        buttonPanel.add(backButton);
    }

    public void onClick(String username, String password) {
        // TODO Auto-generated method stub
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }
}
