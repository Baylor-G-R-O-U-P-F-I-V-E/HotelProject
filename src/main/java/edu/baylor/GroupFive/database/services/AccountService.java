package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.daos.UserDAO;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.util.exceptions.UserNotFoundException;
import edu.baylor.GroupFive.util.exceptions.InvalidCredentialsException;

/**
 *
 */
public class AccountService {

    /**
     *
     * @param username
     * @return
     */
    public static User getUser(String username) {
        UserDAO conn = new UserDAO();
        return conn.getByUsername(username);
    }

    /**
     *
     * @param username
     * @param oldPassword
     * @param newPassword
     * @throws UserNotFoundException
     * @throws InvalidCredentialsException
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
     *
      * @param user
     * @return
     */
    public static boolean register(User user){
        UserDAO conn = new UserDAO();
        return conn.save(user) == 1 ? true : false;
    }

    /**
     *
     * @param username
     * @return
     */
    public static boolean checkAccountExists(String username) {
        UserDAO conn = new UserDAO();
        return conn.getByUsername(username) != null;
    }

    /**
     *
     * @param user
     * @return
     */
    public static Boolean modifyAccount(User user) {
        UserDAO conn = new UserDAO();
        return conn.update(user) == 1 ? true : false;
    }

}
