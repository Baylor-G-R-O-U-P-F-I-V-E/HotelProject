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
import edu.baylor.GroupFive.ui.utils.buttons.landingButtons.CreateAccountButton;
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

        // Add the login button to the surface panel
        addLoginButton();

        // Add the create account button to the surface panel
        addCreateAccountButton();

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

    private void addLoginButton() {
        
        // Set the constraints for the login button
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(0, 250, 130, 0); // Add a margin of 20 pixels on the left and bottom

        // Add the login button to the surface panel with the specified constraints
        surface.add(new LoginButton(this, "src/main/resources/button-icons/login-button-icon.png"), gbc);
    }

    //Write method to create create account button
    private void addCreateAccountButton() {
        // Set the constraints for the create account button
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.insets = new Insets(0, 0, 130, 250); // Add a margin of 20 pixels on the left and bottom

        // Add the create account button to the surface panel with the specified constraints
        surface.add(new CreateAccountButton(this, "src/main/resources/button-icons/create-acct-button-icon.png"), gbc);
    }

    public void onPageSwitch(String option) {
        // Switch to the login page
        if (option.equals("login")) {
            // Close the frame and open a Page object
            dispose();

            // Open a new Page
            @SuppressWarnings("unused")
            InputDelegate page = new Page("admin");
        } else if (option.equals("createAccount")) {
            // TODO: Switch to the create account panel
        
        } else {
            System.out.println("Invalid option");
        }
    }

}
