package edu.baylor.GroupFive.models;

public class User {
    public String firstName;
    public String lastName;
    public String userName;
    public String passwordHash;

    public User(String firstName, String lastName, String userName, String passwordHash){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passwordHash = passwordHash;
    }



}
