package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.daos.UserDAO;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.util.exceptions.UserNotFoundException;
import edu.baylor.GroupFive.util.exceptions.InvalidCredentialsException;

/**
 * The AccountService class provides static methods for managing user accounts.
 *
 * @author Brendon
 * @author Icko
 */
public class AccountService {

    /**
     * Retrieves a user by username.
     *
     * @param username The username of the user to retrieve.
     * @return The User object corresponding to the username.
     * @author Brendon
     */
    public static User getUser(String username) {
        UserDAO conn = new UserDAO();
        return conn.getByUsername(username);
    }

    /**
     * Changes the password for a user.
     *
     * @param username       The username of the user whose password is to be changed.
     * @param oldPassword    The current password of the user.
     * @param newPassword    The new password to be set for the user.
     * @throws UserNotFoundException        If the user with the specified username does not exist.
     * @throws InvalidCredentialsException  If the provided old password does not match the user's current password.
     * @author Icko
     */
    public static void changePassword(String username, String oldPassword, String newPassword) throws UserNotFoundException, InvalidCredentialsException {
        UserDAO conn = new UserDAO();
        User user = conn.getByUsername(username);

        if (user == null) {
            throw new UserNotFoundException();
        }
        if (!user.verify(oldPassword)) {
            throw new InvalidCredentialsException();
        }

        // TODO hash password
        user.changePassword(newPassword);
    }

    /**
     * Registers a new user.
     *
     * @param user The User object representing the new user to be registered.
     * @return true if the user is successfully registered, false otherwise.
     * @author Brendon
     */
    public static boolean register(User user){
        UserDAO conn = new UserDAO();
        return conn.save(user) == 1 ? true : false;
    }

    /**
     * Checks if an account with the given username already exists.
     *
     * @param username The username to check for existence.
     * @return true if an account with the username exists, false otherwise.
     * @author Icko
     */
    public static boolean checkAccountExists(String username) {
        UserDAO conn = new UserDAO();
        return conn.getByUsername(username) != null;
    }

    /**
     * Modifies an existing user account.
     *
     * @param user The User object representing the modified user account.
     * @return true if the account is successfully modified, false otherwise.
     * @author Brendon
     */
    public static Boolean modifyAccount(User user) {
        UserDAO conn = new UserDAO();
        return conn.update(user) == 1 ? true : false;
    }

}
