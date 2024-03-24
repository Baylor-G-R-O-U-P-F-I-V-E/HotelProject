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
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

public class LoginPage extends JFrame implements InputDelegate {

    private JPanel background;
    private JPanel surface;

    public LoginPage() {
        super();

        // Init new frame
        createFrame();

        // Set the frame to be visible
        setVisible(true);

        // Create the surface panel
        surface = new LandingPanel(this);

        // Add the surface panel to the background panel
        add(surface);
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

            // Remove surface panel
            remove(surface);

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
