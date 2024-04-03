package edu.baylor.GroupFive.models;

import java.util.Objects;

public class User {
    public String firstName;
    public String lastName;
    public String userName;
    public String passwordHash;
    public Privilege privilege;

    public User(String firstName, String lastName, String userName, String passwordHash, String privilege){
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.privilege = Privilege.fromString(privilege);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(userName, user.userName) && Objects.equals(passwordHash, user.passwordHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userName, passwordHash);
    }

    public Privilege getPrivilege() {
        return privilege;
    }
}