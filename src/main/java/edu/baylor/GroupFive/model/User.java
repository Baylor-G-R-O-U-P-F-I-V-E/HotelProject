package edu.baylor.GroupFive.model;

public class User {
    private String username;
    private Privilege privilege;

    public User(String username, Privilege privilege) {
        this.username = username;
        this.privilege = privilege;
    }

    // Getter and setter methods
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Privilege getPrivilege() {
        return privilege;
    }

    public void setPrivilege(Privilege privilege) {
        this.privilege = privilege;
    }
}
