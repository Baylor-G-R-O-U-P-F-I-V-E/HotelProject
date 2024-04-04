package edu.baylor.GroupFive.services;

import edu.baylor.GroupFive.models.User;

public class LoginService {
    public static User login(String username, String password) {
        //TEMPORARY SOLUTION
        return new User("John", "Doe", "johndoe", "password", "ADMIN");
    }
}
