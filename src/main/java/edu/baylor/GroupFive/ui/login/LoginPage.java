package edu.baylor.GroupFive.ui.login;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.*;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.buttons.landingButtons.LoginButton;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;
import java.awt.Insets;

public class LoginPage extends JFrame implements InputDelegate {

    private JPanel background;
    private JPanel surface;
    private GridBagConstraints gbc;

    public LoginPage() {
        super();

        // Init new frame
        createFrame();

        // Set the frame to be visible
        setVisible(true);

        // Create the surface panel
        surface = new JPanel(new GridBagLayout());
        surface.setOpaque(false);

        // Create a GridBagConstraints object
        gbc = new GridBagConstraints();

        // Set the constraints for the login button
        // ...

            gbc.gridx = 0; // The x-coordinate of the cell where the button should be placed
            gbc.gridy = 1; // The y-coordinate of the cell where the button should be placed
            gbc.weightx = 1.0; // Make the cell take up all available horizontal space
            gbc.weighty = 1.0; // Make the cell take up all available vertical space
            gbc.anchor = GridBagConstraints.SOUTHWEST; // The button should be anchored to the bottom-left corner of the
                                   // cell
            gbc.insets = new Insets(0, 80, 80, 0); // Add a margin of 20 pixels on the left and bottom

            // Add the login button to the surface panel with the specified constraints
            surface.add(new LoginButton(this, "src/main/resources/button-icons/login-button-icon.png"), gbc);

            // Add the surface panel to the background panel
            background.add(surface, BorderLayout.CENTER);
    }

    public void createFrame() {
        try {
            // Load the background image
            final BufferedImage img = ImageIO.read(getClass().getResource("/landing-background.png"));

            // Create a panel that will display the image
            background = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    // Draw the image scaled to fit the panel
                    g.drawImage(img.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH), 0, 0, null);
                }
            };

            // Set the layout manager of the panel
            background.setLayout(new BorderLayout());

            // Set the panel as the content pane of the frame
            setContentPane(background);

        } catch (IOException e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
    }
    
    public void onPageSwitch(String option) {
        // Switch to the login page
        if (option.equals("login")) {
            // Close the frame and open a Page object
            dispose();

            //Open a new Page
            @SuppressWarnings("unused")
            InputDelegate page = new Page("admin");
        }
    }

}
