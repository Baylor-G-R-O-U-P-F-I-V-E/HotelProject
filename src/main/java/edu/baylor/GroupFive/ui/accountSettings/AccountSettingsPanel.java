package edu.baylor.GroupFive.ui.accountSettings;

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
        
        JLabel userLabel = new JLabel("Username: " + user.getUsername());
        userLabel.setBounds(200, 50, 200, 50);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        userPanel.add(userLabel);

        textPanel.add(userPanel);
    }

    private void addFirstNamePanel(JPanel textPanel) {
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(true);

        JLabel firstLabel = new JLabel("First name: " + user.getFirstName());
        firstLabel.setBounds(200, 50, 200, 50);
        firstLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        namePanel.add(firstLabel);

        textPanel.add(namePanel);
    }

    private void addLastNamePanel(JPanel textPanel) {
        JPanel namePanel = new JPanel();
        namePanel.setOpaque(true);

        JLabel lastLabel = new JLabel("Last name: " + user.getLastName());
        lastLabel.setBounds(200, 50, 200, 50);
        lastLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        namePanel.add(lastLabel);

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

        buttonPanel.add(ModifyButton);
    }

    @Override
    public void clear() {
        // Nothing to clear
    }
}
