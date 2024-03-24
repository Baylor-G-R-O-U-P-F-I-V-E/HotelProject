package edu.baylor.GroupFive.ui.login;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JTextField;


import edu.baylor.GroupFive.ui.utils.BadInputDialog;
import edu.baylor.GroupFive.ui.utils.interfaces.InputDelegate;


public class LoginActionListener implements ActionListener {
    private InputDelegate loginPage;
    private JTextField nameField;
    private JTextField passwordField;
    private static String title = "Login Error";

    public LoginActionListener(InputDelegate loginPage, JTextField nameField, JTextField passwordField) {
        this.loginPage = loginPage;
        this.nameField = nameField;
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Create a default message
        String message = "Login successful!";

        // Get the name
        String name = nameField.getText();
        if (name.isEmpty()) {
            message = "Please enter a name";
            try {
                new BadInputDialog(message, title);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        // Get the password
        String password = passwordField.getText();
        if (password.isEmpty()) {
            message = "Please enter a password";
            try {
                new BadInputDialog(message, title);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        // Check if the name and password are correct
        if (!name.equals("admin") || !password.equals("admin")) {
            message = "Incorrect name or password";
            try {
                new BadInputDialog(message, title);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return;
        }

        // If the name and password are correct, show the success dialog
        //loginPage.getGoodInputDialog(message);
    }

}
