package edu.baylor.GroupFive.ui.createClerk;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

        setVisible(true);
        setOpaque(true);
        setBackground(page.getContentPane().getBackground());

        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));

        addNamePanel(textPanel);
        addPasswordPanel(textPanel);
        addEmailPanel(textPanel);
        addUsernamePanel(textPanel);

        add(textPanel);

    }

    private void addUsernamePanel(JPanel textPanel) {
        JPanel userPanel = new JPanel();
        userPanel.setOpaque(true);
        
        JLabel userLabel = new JLabel("Last name:");
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
        // TODO Auto-generated method stub

    }

    private void addPasswordPanel(JPanel textPanel) {
        // TODO Auto-generated method stub

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

    @Override
    public void clear() {
        // TODO Auto-generated method stub
    }

}
