package edu.baylor.GroupFive.ui.landing;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.image.*;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.baylor.GroupFive.database.controllers.AccountController;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.landing.createAccount.CreateAccountPanel;
import edu.baylor.GroupFive.ui.landing.login.LoginPanel;
import edu.baylor.GroupFive.ui.utils.Page;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;

/**
 * Landing page of the application, responsible for user interaction and navigation.
 *
 * This class implements {@link edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate}.
 *
 * @see edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate
 * @author Brendon
 */
public class LandingPage extends JFrame implements InputDelegate {

    private JPanel background;
    private JPanel surface;
    private String username;

    /**
     * Constructs a LandingPage
     *
     * @author Brendon
     */
    public LandingPage() {
        super();

        // Init new frame
        createFrame();

        // Set the frame to be visible
        setVisible(true);

        // Create the surface panel
        surface = new LandingPanel(this);

        // Add the surface panel to the frame
        add(surface);
    }

    /**
     * Creates the frame and initializes the components.
     *
     * @author Brendon
     */
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

    /**
     * Handles navigation to different pages based on the specified option.
     *
     * @param option The option indicating the page to switch to.
     * @author Brendon
     */
    public void onPageSwitch(String option) {
        
        // Switch to the login page
        if (option.equals("login")) {

            //Replace surface panel with login panel
            remove(surface);
            surface = new LoginPanel(this);
            add(surface);
            revalidate();
            repaint();

        } else if (option.equals("createAccount")) {
            
            //Replace surface panel with create account panel
            remove(surface);
            surface = new CreateAccountPanel(this);
            add(surface);
            revalidate();
            repaint();
        
        } else if (option.equals("success")) {
            User user = AccountController.getUser(username);
            
            if (user == null) {
                //Display an error optionpane
                JOptionPane.showMessageDialog(null, "Invalid username or password");
                return;
            }
            
            dispose();
            
            @SuppressWarnings("unused")
            Page page = new Page(user);

        } else if (option.equals("cancel")) {
            
            //Replace surface panel with landing panel
            remove(surface);
            surface = new LandingPanel(this);
            add(surface);
            revalidate();
            repaint();

        } else {
            System.out.println("Invalid option");
        }
    }

    /**
     * Sets the username.
     *
     * @param username The username.
     * @author Brendon
     */
    public void setUsername(String username) {
        this.username = username;
    }

}
