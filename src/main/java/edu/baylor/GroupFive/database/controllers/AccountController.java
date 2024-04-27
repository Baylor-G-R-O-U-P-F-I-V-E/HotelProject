package edu.baylor.GroupFive.database.controllers;

import edu.baylor.GroupFive.util.exceptions.InvalidCredentialsException;

import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.database.services.AccountService;
import edu.baylor.GroupFive.database.services.LoginService;

/**
 * This class represents a handler between our UI layer and our application
 * layer. Methods from this class are called from our UI layer. {@code AccountController}
 * then determines which Service object in our application layer to continue 
 * operations with. The status of each operation is then returned to the UI layer.
 *
 * @author Brendon
 * @author Icko
 * */
public class AccountController {

    /**
     * Takes in a username a password and attempts to log in the user. This
     * function calls {@link LoginService#login(String,String)}.
     *
     * @param username Users username
     * @param password Users password
     * @return User object associated with correct {@code username} and {@code password}
     * @throws InvalidCredentialsException If invalid credentials are provided
     * @see LoginService#login(String, String)
     * */
    public static User login(String username, String password) throws InvalidCredentialsException {
        return LoginService.login(username, password);
    }

    /**
     * Fetches a user from our database given a username. This function calls
     * {@link AccountService#getUser(String)}.
     * 
     * @param username Users username
     * @return User associated with {@code username}
     * @see AccountService#getUser(String)
     * */
    public static User getUser(String username) {
        return AccountService.getUser(username);
    }

    /**
     * Takes in a user objects and registers them for an account. This function
     * calls {@link AccountService#register(User)}.
     *
     * @param user User to register
     * @return {@code true} if saved successfully. {@code false} otherwise.
     * @see AccountService#register(User)
     * */
    public static boolean register(User user) {
        return AccountService.register(user);
    }

    /**
     * Takes in a String {@code username} and checks if an account with
     * this username exists in our database. This function calls
     * {@link AccountService#checkAccountExists(String)}.
     *
     * @param username Users username
     * @return {@code true} if account with that username exists. {@code false} otherwise
     * @see AccountService#checkAccountExists(String)
     * */
    public static boolean checkAccountExists(String username) {
        return AccountService.checkAccountExists(username);
    }

    /**
     * Takes in a new User object and updates the account information.
     * This function calls {@link AccountService#modifyAccount(User)}
     *
     * @param user User with modifications
     * @return {@code true} if account was successfully modified. {@code false} otherwise
     * @see AccountService#modifyAccount(User)
     * */
    public static Boolean modifyAccount(User user) {
         return AccountService.modifyAccount(user);
    }

    /**
     * TODO does not do anything atm
     *
     * @param username Users username
     * */
    public static void changePassword(String username) {
    }

}
