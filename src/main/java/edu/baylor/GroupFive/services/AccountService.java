package edu.baylor.GroupFive.services;

import edu.baylor.GroupFive.database.userDAO.UserDatabaseConnection;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.exceptions.UserNotFoundException;
import edu.baylor.GroupFive.exceptions.InvalidCredentialsException;

public class AccountService {
    
    public static User getUser(String username) {
        UserDatabaseConnection conn = new UserDatabaseConnection();
        return conn.getUser(username);
    }

    public static void changePassword(String username, String oldPassword, String newPassword) throws UserNotFoundException, InvalidCredentialsException {
        UserDatabaseConnection conn = new UserDatabaseConnection();
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
    
    public static void register(String firstName, String lastName, String username, String password, String privilege){
        UserDatabaseConnection conn = new UserDatabaseConnection();
        User user = new User(firstName, lastName, username, password, privilege);
        conn.addUser(user);
    }

    public static boolean checkAccountExists(String username) {
        UserDatabaseConnection conn = new UserDatabaseConnection();
        return conn.getUser(username) != null;
    }

}
