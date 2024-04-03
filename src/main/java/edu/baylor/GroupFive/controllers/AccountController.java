package edu.baylor.GroupFive.controllers;

import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.services.AccountService;
import edu.baylor.GroupFive.services.LoginService;

public class AccountController {

    public User login(String username, String password) {
        return LoginService.login(username, password);
    }

    public static User getUser(String username) {
        return AccountService.getUser(username);
    }

    public void register(String firstName, String lastName, String username, String password, String privilege) {
        //AccountService.register(firstName, lastName, username, password, privilege);
    }
}
