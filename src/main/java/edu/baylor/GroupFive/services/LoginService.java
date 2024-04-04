package edu.baylor.GroupFive.services;

import edu.baylor.GroupFive.database.userDAO.UserDatabaseConnection;
import edu.baylor.GroupFive.models.User;

public class LoginService {
    public static User login(String username, String password) {
        UserDatabaseConnection conn = new UserDatabaseConnection();
        return conn.getUser(username);
    }
}
