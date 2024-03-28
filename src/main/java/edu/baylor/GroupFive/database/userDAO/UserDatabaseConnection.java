package edu.baylor.GroupFive.database.userDAO;

import edu.baylor.GroupFive.models.User;

public class UserDatabaseConnection {
    public User getUser(){
        return new User(null, null, null, null);
    }

    public Boolean addUser(User user){
        return false;
    }



}
