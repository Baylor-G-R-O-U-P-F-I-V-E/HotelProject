package edu.baylor.GroupFive.ui.createClerk;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;

import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

public class CreateClerkAccountPanel extends JPanel implements PagePanel {
    private Page page;
    private JTextField firstName, lastName, username, password, email;
    private Dimension fieldSize = new Dimension(200, 50);

    public CreateClerkAccountPanel(Page page) {
        super();
        this.page = page;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setVisible(true);
        setOpaque(true);
        setBackground(page.getContentPane().getBackground());

        JLabel titleLabel = new JLabel("Create Clerk Account");
    titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the title label
    titleLabel.setFont(new Font("Arial", Font.BOLD, 24)); // Set a larger, bold font

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        addNamePanel(textPanel);
        addEmailPanel(textPanel);
        addUsernamePanel(textPanel);
        addPasswordPanel(textPanel);
        
        add(Box.createVerticalGlue());
        
        add(titleLabel);

        add(textPanel);

        // add a button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setOpaque(false);
        addCreateButton(buttonPanel);

        add(buttonPanel);

        add(Box.createVerticalGlue());

    }

    private void addUsernamePanel(JPanel textPanel) {
        JPanel userPanel = new JPanel();
        userPanel.setOpaque(true);
        
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(200, 50, 200, 50);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        username = new JTextField();
        username.setBounds(400, 50, 200, 50);
        username.setFont(new Font("Arial", Font.PLAIN, 20));
        username.setPreferredSize(fieldSize);

        userPanel.add(userLabel);
        userPanel.add(username);

        textPanel.add(userPanel);
    }

    private void addEmailPanel(JPanel textPanel) {
        
        JPanel emailPanel = new JPanel();
        emailPanel.setOpaque(true);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(200, 50, 200, 50);
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        email = new JTextField();
        email.setBounds(400, 50, 200, 50);
        email.setFont(new Font("Arial", Font.PLAIN, 20));
        email.setPreferredSize(fieldSize);

        emailPanel.add(emailLabel);
        emailPanel.add(email);

        textPanel.add(emailPanel);

    }

    private void addPasswordPanel(JPanel textPanel) {
        JPanel passPanel = new JPanel();
        passPanel.setOpaque(true);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(200, 50, 200, 50);
        passLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        password = new JTextField();
        password.setBounds(400, 50, 200, 50);
        password.setFont(new Font("Arial", Font.PLAIN, 20));
        password.setPreferredSize(fieldSize);

        passPanel.add(passLabel);
        passPanel.add(password);

        textPanel.add(passPanel);

    }

    private void addNamePanel(JPanel textPanel) {
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(true);

        JLabel firstLabel = new JLabel("First name:");
        firstLabel.setBounds(200, 50, 200, 50);
        firstLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        firstName = new JTextField();
        firstName.setBounds(400, 50, 200, 50);
        firstName.setFont(new Font("Arial", Font.PLAIN, 20));
        firstName.setPreferredSize(fieldSize);

        namePanel.add(firstLabel);
        namePanel.add(firstName);

        JLabel lastLabel = new JLabel("Last name:");
        lastLabel.setBounds(200, 50, 200, 50);
        lastLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        lastName = new JTextField();
        lastName.setBounds(400, 50, 200, 50);
        lastName.setFont(new Font("Arial", Font.PLAIN, 20));
        lastName.setPreferredSize(fieldSize);

        namePanel.add(lastLabel);
        namePanel.add(lastName);

        textPanel.add(namePanel);
    }

    private void addCreateButton(JPanel buttonPanel) {
        // Create the create button
        JButton createButton = new JButton("Create Account");
        createButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        createButton.setPreferredSize(new Dimension(200, 50));
        createButton.setFont(new Font("Arial", Font.PLAIN, 22));
        createButton.setOpaque(true);
        createButton.setBorderPainted(false);
        createButton.setBackground(new Color(0, 0, 153));
        createButton.setForeground(new Color(255, 255, 255));
        createButton.addActionListener(new CreateClerkAccountListener(page, firstName, lastName, username, password, email));

        buttonPanel.add(createButton);
    }

    @Override
    public void clear() {
        firstName.setText("");
        lastName.setText("");
        username.setText("");
        password.setText("");
        email.setText("");
    }

}
