package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.daos.UserDAO;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.util.exceptions.UserNotFoundException;
import edu.baylor.GroupFive.util.exceptions.InvalidCredentialsException;

public class AccountService {
    
    public static User getUser(String username) {
        UserDAO conn = new UserDAO();
        return conn.getByUsername(username);
    }

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
    
    public static boolean register(User user){
        UserDAO conn = new UserDAO();
        return conn.save(user) == 1 ? true : false;
    }

    public static boolean checkAccountExists(String username) {
        UserDAO conn = new UserDAO();
        return conn.getByUsername(username) != null;
    }

    public static Boolean modifyAccount(User user) {
        UserDAO conn = new UserDAO();
        return conn.update(user) == 1 ? true : false;
    }

}
