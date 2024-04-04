package edu.baylor.GroupFive.ui.modifyReservation;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.Date;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.baylor.GroupFive.ui.utils.DatePanel;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;

public class ModifyReservationPanel extends JPanel implements PagePanel {

    private Page delegate;
    private Dimension fieldSize = new Dimension(200, 50);
    private JTextField roomID, price;
    private DatePanel startDate, endDate;
    private String originalRoom;
    private Date originalStart;

    public ModifyReservationPanel(Page delegate, String originalRoom, Date originalStart) {
        super();
        this.delegate = delegate;
        this.originalRoom = originalRoom;
        this.originalStart = originalStart;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setOpaque(false);

        // Add a vertical strut to modify vertical space at the top
        add(Box.createVerticalStrut(500)); // Adjust the value as needed

        JPanel textPanel = new JPanel();

        addRoomPanel(textPanel);
        addPricePanel(textPanel);

        startDate = new DatePanel("Start Date:");
        endDate = new DatePanel("End Date:", 1);

        textPanel.add(startDate);
        textPanel.add(endDate);

        textPanel.setOpaque(false);

        add(textPanel);

        JPanel buttonPanel = new JPanel();

        addModifyReservationButton(buttonPanel);
        addBackButton(buttonPanel);

        buttonPanel.setOpaque(false);

        add(buttonPanel);

        setVisible(true);

        setSize(400, 400);

    }

    public void addRoomPanel(JPanel textPanel) {
        JPanel roomPanel = new JPanel();
        roomPanel.setOpaque(true);

        JLabel roomLabel = new JLabel("First Name:");
        roomLabel.setBounds(200, 50, 200, 50);
        roomLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        roomID = new JTextField();
        roomID.setBounds(400, 50, 200, 50);
        roomID.setFont(new Font("Arial", Font.PLAIN, 20));
        roomID.setPreferredSize(fieldSize);

        roomPanel.add(roomLabel);
        roomPanel.add(roomID);

        textPanel.add(roomPanel);
    }

    public void addPricePanel(JPanel textPanel) {
        JPanel pricePanel = new JPanel();
        pricePanel.setOpaque(true);

        JLabel priceLabel = new JLabel("Last Name:");
        priceLabel.setBounds(200, 50, 200, 50);
        priceLabel.setFont(new Font("Arial", Font.PLAIN, 20));

        price = new JTextField();
        price.setBounds(400, 50, 200, 50);
        price.setFont(new Font("Arial", Font.PLAIN, 20));
        price.setPreferredSize(fieldSize);
        price.setMaximumSize(fieldSize);

        pricePanel.add(priceLabel);
        pricePanel.add(price);

        textPanel.add(pricePanel);
    }

    public void addModifyReservationButton(JPanel buttonPanel) {
        JButton modifyButton = new JButton("Confirm Changes");
        modifyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        modifyButton.setPreferredSize(new Dimension(200, 50));
        modifyButton.setFont(new Font("Arial", Font.PLAIN, 22));
        modifyButton.setOpaque(true);
        modifyButton.setBorderPainted(false);
        modifyButton.setBackground(new Color(0, 0, 153));
        modifyButton.setForeground(new Color(255, 255, 255));

        // Add an action listener to the button
        modifyButton.addActionListener(new ModifyReservationActionListener(delegate, originalRoom, originalStart, roomID, price, startDate, endDate));

        buttonPanel.add(modifyButton);
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
        backButton.addActionListener(e -> delegate.onPageSwitch("home"));

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
