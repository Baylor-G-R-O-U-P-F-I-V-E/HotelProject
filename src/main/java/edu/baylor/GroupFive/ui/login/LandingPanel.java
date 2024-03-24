package edu.baylor.GroupFive.ui.login;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JPanel;

import edu.baylor.GroupFive.ui.utils.buttons.landingButtons.CreateAccountButton;
import edu.baylor.GroupFive.ui.utils.buttons.landingButtons.LoginButton;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;
import edu.baylor.GroupFive.ui.utils.interfaces.PagePanel;
import java.awt.Insets;

public class LandingPanel extends JPanel implements PagePanel {
    GridBagConstraints gbc;
    InputDelegate delegate;
    
    public LandingPanel(InputDelegate delegate) {
        super();
        this.delegate = delegate;
        setLayout(new GridBagLayout());

        setOpaque(false);

        // Create a GridBagConstraints object
        gbc = new GridBagConstraints();

        // Add the login button to the surface panel
        addLoginButton();

        // Add the create account button to the surface panel
        addCreateAccountButton();
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
        add(new LoginButton(delegate, "src/main/resources/button-icons/login-button-icon.png"), gbc);
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
        add(new CreateAccountButton(delegate, "src/main/resources/button-icons/create-acct-button-icon.png"), gbc);
    }

    @Override
    public void clear() {
        //Nothing to clear
    }
}
