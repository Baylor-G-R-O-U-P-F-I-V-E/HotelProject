package edu.baylor.GroupFive.ui.landing.login;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;

import edu.baylor.GroupFive.util.exceptions.InvalidCredentialsException;
import edu.baylor.GroupFive.database.controllers.AccountController;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.ui.landing.LandingPage;
import edu.baylor.GroupFive.ui.utils.BadInputDialog;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;
import edu.baylor.GroupFive.util.CoreUtils;

/**
 *
 */
public class LoginActionListener implements ActionListener {
    private InputDelegate loginPage;
    private JTextField nameField;
    private JTextField passwordField;
    private static String title = "Login Error";

    /**
     *
     * @param loginPage
     * @param nameField
     * @param passwordField
     */
    public LoginActionListener(LandingPage loginPage, JTextField nameField, JTextField passwordField) {
        this.loginPage = loginPage;
        this.nameField = nameField;
        this.passwordField = passwordField;
    }

    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a default message
        String message = "Login successful!";

        // Get the name
        String name = nameField.getText();

        // If the name is empty, show an error message
        if (name.isEmpty()) {
            message = """
                        Oh, honey, did you misplace your identity along with your username? 
                        You can't just stroll into the hotel like a mystery guest. 
                        We need a little something called a username to roll out the red 
                        carpet for you. It's like trying to check into a room without 
                        knowing your own name – not exactly a recipe for success. Take a 
                        moment, gather your thoughts, and give us a name to work with. 
                        We'll be waiting right here, ready to welcome you properly
                    """;
            try {
                new BadInputDialog(message, title);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        // Get the password
        String passwordHash = CoreUtils.hashPassword(passwordField.getText());
        
        // If the password is empty, show an error message
        if (passwordHash.isEmpty()) {
            message = """
                    
                            Well, well, well... look who's trying to
                            login without a password. You know that's
                            not how it works, right? You need to enter
                            a password to login. Try again. 
                    
                        """;
            try {
                new BadInputDialog(message, title);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        // Attempt to login
        User user = null;
        try {
            user = AccountController.login(name, passwordHash);
        } catch (InvalidCredentialsException ex) {
            message = "Incorrect name or password";
            try {
                new BadInputDialog(message, title);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }
        
        // If the user is null, the login failed
        if (user == null) {
        }

        // Switch to the success page
        ((LandingPage) loginPage).setUsername(name);

        loginPage.onPageSwitch("success");
        
    }
}


