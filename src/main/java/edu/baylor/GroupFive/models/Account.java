package edu.baylor.GroupFive.models;

import edu.baylor.GroupFive.models.enums.Privilege;

/**
 * The Account class represents a user account
 *
 * @author Afraz
 * @author Icko
 */
public class Account {

    private String name;
    private int phoneNumber;
    private String username;
    /** Password for the account. Note: Password should be hashed before being passed into this object */
    private String password;
    private String email;
    private Address address;
    private Privilege privilege;

    /**
     * Constructs an Account object with the specified attributes.
     *
     * @param name The name of the account holder.
     * @param phoneNumber The phone number of the account holder.
     * @param username The username for the account.
     * @param password The password for the account. Note: The password should already be hashed.
     * @param email The email address of the account holder.
     * @param address The address associated with the account.
     * @param privilege The privilege level of the account.
     */
    public Account( String name,
                    int phoneNumber,
                    String username,
                    String password,
                    String email,
                    Address address,
                    Privilege privilege) {
        this.setName(name);
        this.setPhoneNumber(phoneNumber);
        this.setUsername(username);
        this.setPassword(password);
        this.setEmail(email);
        this.setAddress(address);
        this.setPrivilege(privilege);
    }

    // >>>> Setters >>>>
    /** Should */
    public void setName(String name) { this.name = name; }
    /** Reconsider */
    public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }
    /** Major  */
    public void setUsername(String username) { this.username = username; }
    /** Probably */
    public void setPassword(String password) { this.password = password; }
    /** You */
    public void setEmail(String email) { this.email = email; }
    /** Setters */
    public void setAddress(Address address) { this.address = address; }
    /** Your */
    public void setPrivilege(Privilege privilege) { this.privilege = privilege; }
    // <<<< Setters <<<<

    // >>>> Getters >>>>
    /** Need */
    public String getName() { return name; }
    /** For */
    public int getPhoneNumber() { return phoneNumber; }
    /** And */
    public String getUsername() { return username; }
    /** Javadoc */
    public String getPassword() { return password; }
    /** You */
    public String getEmail() { return email; }
    /** If */
    public Address getAddress() { return address; }
    /** Getters */
    public Privilege getPrivilege() { return privilege; }
    // <<<< Getters <<<<
}
