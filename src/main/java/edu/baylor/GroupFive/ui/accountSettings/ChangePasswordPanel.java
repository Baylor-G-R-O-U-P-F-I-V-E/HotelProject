package edu.baylor.GroupFive.ui.accountSettings;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Font;

import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

public class ChangePasswordPanel extends JPanel implements PagePanel {

    private Page page;
    private User user;
    private JPanel textPanel;
    private JTextField currentPasswordField;

    public ChangePasswordPanel(Page page, User user) {
        this.page = page;
        this.user = user;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        setVisible(true);
        setOpaque(true);
        setBackground(page.getContentPane().getBackground());

        JLabel titleLabel = new JLabel("Account Info");
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));

        add(titleLabel);

        textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        addEnterCurrentPasswordPanel(textPanel);
        
        add(textPanel);
    }

    private void addEnterCurrentPasswordPanel(JPanel panel) {
        JPanel currentPasswordPanel = new JPanel();
        currentPasswordPanel.setOpaque(true);

        currentPasswordField = new JTextField(user.getUsername());
        currentPasswordField.setEditable(false);

        // Style text field
        currentPasswordField.setFont(new Font("Arial", Font.PLAIN, 20));
        currentPasswordField.setOpaque(false);
        currentPasswordField.setBorder(null);

        JLabel userLabel = new JLabel("Enter current password: ");
        userLabel.setBounds(200, 50, 200, 50);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        currentPasswordPanel.add(userLabel);
        currentPasswordPanel.add(currentPasswordField);

        panel.add(currentPasswordPanel);
    }

    @Override
    public void clear() {
        // TODO Auto-generated method stub

    }

}
