package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.daos.UserDAO;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.util.exceptions.UserNotFoundException;
import edu.baylor.GroupFive.util.exceptions.InvalidCredentialsException;

public class AccountService {
    
    public static User getUser(String username) {
        UserDAO conn = new UserDAO();
        return conn.getUser(username);
    }

    public static void changePassword(String username, String oldPassword, String newPassword) throws UserNotFoundException, InvalidCredentialsException {
        UserDAO conn = new UserDAO();
        User user = conn.getUser(username);

        if(user == null) {
            throw new UserNotFoundException();
        }
        if(!user.verify(oldPassword)) {
            throw new InvalidCredentialsException();
        }

        // TODO hash password
        user.changePassword(newPassword);
    }
    
    public static boolean register(User user){
        UserDAO conn = new UserDAO();
        return conn.addUser(user);
    }

    public static boolean checkAccountExists(String username) {
        UserDAO conn = new UserDAO();
        return conn.getUser(username) != null;
    }

}
