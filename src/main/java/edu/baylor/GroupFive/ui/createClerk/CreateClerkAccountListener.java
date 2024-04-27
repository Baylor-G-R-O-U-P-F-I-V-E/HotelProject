package edu.baylor.GroupFive.ui.createClerk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.baylor.GroupFive.database.controllers.AccountController;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.models.enums.Privilege;
import edu.baylor.GroupFive.ui.utils.BadInputDialog;
import edu.baylor.GroupFive.ui.utils.Page;

/**
 * ActionListener implementation for creating a clerk account.
 * This class handles the creation of a clerk account when triggeredby an action event.
 * It validates the input fields and registers the new account if all fields are filled.
 * If the account already exists or if any field is empty, appropriate error messages are displayed.
 *
 * @author Brendon
 */
public class CreateClerkAccountListener implements ActionListener {
    
    private JTextField firstName, lastName, username, password;

    /**
     * ActionListener implementation for creating a clerk account.
     *
     * @param page The page associated with the action.
     * @param firstName The text field for the first name.
     * @param lastName The text field for the last name.
     * @param username The text field for the username.
     * @param password The text field for the password.
     */
    public CreateClerkAccountListener(Page page, JTextField firstName, JTextField lastName, JTextField username, JTextField password) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    /**
     * Clears the text fields associated with this listener.
     */
    public void clearFields() {
        firstName.setText("");
        lastName.setText("");
        username.setText("");
        password.setText("");
    }

    /**
     * Performs the action associated with creating a clerk account.
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        // Get the text from the fields
        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String username = this.username.getText();
        String password = this.password.getText();
        
        // Check if the fields are empty
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty()) {
            
            // Display an error message
            String message = "Please fill out all fields";

            try {

                new BadInputDialog(message, "Cannot make account");

            } catch (Exception e1) {
                e1.printStackTrace();
            }

        } else {
            // Create the clerk account
            User user = new User(firstName, lastName, username, password, "clerk");
            if (AccountController.register(user)) {
                // Display a success message
                JOptionPane.showMessageDialog(null, "Account created successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                
            } else {
                // Display an error message
                String message = "Account already exists";

                try {

                    new BadInputDialog(message, "Cannot make account");

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
