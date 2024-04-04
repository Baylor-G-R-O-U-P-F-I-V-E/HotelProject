package edu.baylor.GroupFive.controllers;

import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.services.AccountService;
import edu.baylor.GroupFive.services.LoginService;

// Assume a User is logged in when calling these functions
// i.e. Don't need to read pswd
public class AccountController {

    public static User login(String username, String password) {
        return LoginService.login(username, password);
    }

    public static User getUser(String username) {
        return AccountService.getUser(username);
    }

    public static void register(String firstName, String lastName, String username, String password, String privilege) {
        //AccountService.register(firstName, lastName, username, password, privilege);
    }

    public static void checkAccountExists(String username) {
        return AccountService.checkAccountExists(username);
    }

    public static void modifyAccount(String username) {
         
    }

    public static void changePassword(String username) {
    }

}
