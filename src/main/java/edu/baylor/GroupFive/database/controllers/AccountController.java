package edu.baylor.GroupFive.database.controllers;

import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.database.services.AccountService;
import edu.baylor.GroupFive.database.services.LoginService;

// Assume a User is logged in when calling these functions
// i.e. Don't need to read pswd
 /**
  * Was supposed to log in users into their account
  *
  * @deprecated use
  *     {@link #CreateAccountActionListener()}
  *     {@link #CreateAccountButton()}
  *     {@link #LoginButton()}
  *     {@link #LogoutButton()}
  * instead.
  * */
@Deprecated
public class AccountController {

    public static User login(String username, String password) {
        return LoginService.login(username, password);
    }

    public static User getUser(String username) {
        return AccountService.getUser(username);
    }

    public static boolean register(User user) {
        return AccountService.register(user);
    }

    public static boolean checkAccountExists(String username) {
        return AccountService.checkAccountExists(username);
    }

    public static void modifyAccount(String username) {
         
    }

    public static void changePassword(String username) {
    }

}
