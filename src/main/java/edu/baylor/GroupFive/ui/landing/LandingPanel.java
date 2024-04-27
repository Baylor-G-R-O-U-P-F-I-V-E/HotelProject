package edu.baylor.GroupFive.ui.landing;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import edu.baylor.GroupFive.ui.utils.buttons.landingButtons.CreateAccountButton;
import edu.baylor.GroupFive.ui.utils.buttons.landingButtons.LoginButton;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import java.awt.Insets;

/**
 * Landing panel displayed on the landing page, providing options for user interactivity.
 *
 * Implements {@link edu.baylor.GroupFive.ui.utils.interfaces.PagePanel}.
 *
 * @see edu.baylor.GroupFive.ui.utils.interfaces.PagePanel
 * @author Brendon
 */
public class LandingPanel extends JPanel implements PagePanel {
    private GridBagConstraints gbc;
    private InputDelegate delegate;

    /**
     * Constructs a LandingPanel with the specified delegate.
     *
     * @param delegate The input delegate for handling user interactions
     * @author Brendon
     */
    public LandingPanel(InputDelegate delegate) {
        super();
        this.delegate = delegate;
        setLayout(new GridBagLayout());

        setOpaque(false);
        setVisible(true);

        // Create a GridBagConstraints object
        gbc = new GridBagConstraints();

        // Add the login button to the surface panel
        addLoginButton();

        // Add the create account button to the surface panel
        addCreateAccountButton();
    }

    /**
     * Adds the login button to the landing panel.
     *
     * @author Brendon
     */
    private void addLoginButton() {
        
        // Set the constraints for the login button
        gbc.gridx = 0;
        gbc.gridy = 1; 
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHWEST;
        gbc.insets = new Insets(0, 250, 130, 0); // Add a margin of 20 pixels on the left and bottom

        // Add the login button to the surface panel with the specified constraints
        add(new LoginButton(delegate, "src/main/resources/button-icons/login-button-icon.png"), gbc);
    }


    /**
     * Adds the create account button to the landing panel.
     *
     * @author Brendong
     */
    private void addCreateAccountButton() {
        // Set the constraints for the create account button
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.SOUTHEAST;
        gbc.insets = new Insets(0, 0, 130, 250); // Add a margin of 20 pixels on the left and bottom

        // Add the create account button to the surface panel with the specified constraints
        add(new CreateAccountButton(delegate, "src/main/resources/button-icons/create-acct-button-icon.png"), gbc);
    }

    /**
     * Clears the landing panel.
     *
     * @author Intellij
     */
    @Override
    public void clear() {
        //Nothing to clear
    }
}
