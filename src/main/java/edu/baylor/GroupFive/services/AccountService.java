package edu.baylor.GroupFive.services;

import edu.baylor.GroupFive.database.userDAO.UserDatabaseConnection;
import edu.baylor.GroupFive.models.User;

public class AccountService {
    
        public static User getUser(String username) {
            UserDatabaseConnection conn = new UserDatabaseConnection();
            return conn.getUser(username);
        }

        public static void register(String firstName, String lastName, String username, String password, String privilege){
            UserDatabaseConnection conn = new UserDatabaseConnection();
            User user = new User(firstName, lastName, username, password, privilege);
            conn.addUser(user);
        }

}
