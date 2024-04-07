package edu.baylor.GroupFive.ui.createClerk;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import edu.baylor.GroupFive.controllers.AccountController;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.models.enums.Privilege;
import edu.baylor.GroupFive.ui.utils.BadInputDialog;
import edu.baylor.GroupFive.ui.utils.Page;

public class CreateClerkAccountListener implements ActionListener {
    
    private JTextField firstName, lastName, username, password, email;

    public CreateClerkAccountListener(Page page, JTextField firstName, JTextField lastName, JTextField username, JTextField password, JTextField email) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public void clearFields() {
        firstName.setText("");
        lastName.setText("");
        username.setText("");
        password.setText("");
        email.setText("");
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        // Get the text from the fields
        String firstName = this.firstName.getText();
        String lastName = this.lastName.getText();
        String username = this.username.getText();
        String password = this.password.getText();
        String email = this.email.getText();
        
        // Check if the fields are empty
        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || email.isEmpty()) {
            
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
