package edu.baylor.GroupFive.models;

public class User {
    String firstName;
    String lastName;
    String userName;
    String passwordHash;

    public User(String firstName, String lastName, String userName, String passwordHash){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passwordHash = passwordHash;
    }



}
