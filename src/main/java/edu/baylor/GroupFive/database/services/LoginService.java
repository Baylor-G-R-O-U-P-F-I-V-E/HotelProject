package edu.baylor.GroupFive.database.services;

import edu.baylor.GroupFive.database.daos.UserDAO;
import edu.baylor.GroupFive.models.User;

public class LoginService {
    public static User login(String username, String password) {
        UserDAO conn = new UserDAO();
        return conn.getByUsername(username);
    }
}
