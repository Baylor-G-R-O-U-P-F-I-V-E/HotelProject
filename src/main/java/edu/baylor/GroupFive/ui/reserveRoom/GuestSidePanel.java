package edu.baylor.GroupFive.ui.reserveRoom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class GuestSidePanel extends JPanel {
    // Creates a default size
    Dimension buttonSize = new Dimension(250, 250);

    public GuestSidePanel() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        
        // Init home button
        try {
            JButton homeButton = getHomeButton();
            add(homeButton);
        } catch (Exception e) {
            System.out.println("Error: home-icon.png not found");
        }

        // Init checkout button
        try {
            JButton checkoutButton = getCheckoutButton(); 
            add(checkoutButton);
        } catch (Exception e) {
            System.out.println("Error: reservations-icon.png not found");
        }

        // Init view reservations button
        try {
            JButton reservationsButton = getReservationsButton();
            add(reservationsButton);
        } catch (Exception e) {
            System.out.println("Error: reservations-icon.png not found");
        }
    }

    public JButton getHomeButton() throws IOException {
        // Init home button
        Image homeImage = ImageIO.read(new File("src/main/resources/button-icons/home-icon.png"));
        ImageIcon homeIcon = new ImageIcon(homeImage);
        JButton homeButton = new JButton(homeIcon);
        homeButton.setPreferredSize(buttonSize);
        homeButton.setMaximumSize(buttonSize);
        return homeButton;
    }

    public JButton getReservationsButton() throws IOException {
        // Init view reservations button
        Image viewReservationsImage = ImageIO.read(new File("src/main/resources/button-icons/reservations-icon.png"));
        ImageIcon checkoutIcon = new ImageIcon(viewReservationsImage);
        JButton checkoutButton = new JButton(checkoutIcon);
        checkoutButton.setPreferredSize(buttonSize);
        checkoutButton.setMaximumSize(buttonSize);
        return checkoutButton;
    }

    public JButton getCheckoutButton() throws IOException {
        // Init view reservations button
        Image checkoutImage = ImageIO.read(new File("src/main/resources/button-icons/checkout-icon.png"));
        ImageIcon checkoutIcon = new ImageIcon(checkoutImage);
        JButton checkoutButton = new JButton(checkoutIcon);
        checkoutButton.setPreferredSize(buttonSize);
        checkoutButton.setMaximumSize(buttonSize);
        return checkoutButton;
    }
}
