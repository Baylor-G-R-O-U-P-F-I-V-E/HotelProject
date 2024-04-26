package edu.baylor.GroupFive.models;

import edu.baylor.GroupFive.models.enums.Privilege;

import java.util.Objects;

/**
 * The User class represents a user of our system.
 *
 * @author Cole
 * */
public class User {
    private Integer id = null;
    private String firstName;
    private String lastName;
    private String userName;
    private String passwordHash;
    private Privilege privilege;

    /**
     * Construct a User object with the specified attributes.
     *
     * @param firstName First name of user.
     * @param lastName Last name of user.
     * @param userName Username for user.
     * @param passwordHash Hashed password for user.
     * @param privilege Privilege level of user.
     * @author Cole
     * */
    public User(String firstName, String lastName, String userName, String passwordHash, String privilege){
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setUsername(userName);
        this.setPasswordHash(passwordHash);
        this.setPrivilege(Privilege.fromString(privilege));
    }

    // >>>> Getters >>>>
    public int getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getUsername() { return userName; }
    public String getPasswordHash() { return passwordHash; }
    public Privilege getPrivilege() { return privilege; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setId(int id_) { id = id_; }
    public void setFirstName(String firstName_) { firstName = firstName_; }
    public void setLastName(String lastName_) { lastName = lastName_; }
    public void setUsername(String userName_) { userName = userName_; }
    public void setPasswordHash(String passwordHash_) { passwordHash = passwordHash_; }
    public void setPrivilege(Privilege privilege_) { privilege = privilege_; }
    // <<<< Setters <<<<

    /**
     * Compares this User to another object.
     *
     * @param o Object to compare to.
     * @return {@code true} if {@code o} is equivalent this User. {@code false} otherwise.
     * @author Cole
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(userName, user.userName) && Objects.equals(passwordHash, user.passwordHash);
    }

    /**
     * Returns a hash of this object.
     *
     * @return Hash of this object.
     * @author Cole
     * */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, userName, passwordHash);
    }

    /**
     * Verifies a password matches our stored password hash.
     *
     * @param password Password to verify.
     * @return {@code true} if verified. {@code false} otherwise.
     * @author Icko
     * */
    public boolean verify(String password) {
        return password.equals(passwordHash);
    }

    /**
     * Changes password hash to a new password
     *
     * @param newPasswordHash New password.
     * @author Icko
     * */
    public void changePassword(String newPasswordHash) {
        passwordHash = newPasswordHash;
    }

}
