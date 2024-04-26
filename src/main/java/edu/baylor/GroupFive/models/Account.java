package edu.baylor.GroupFive.models;

import edu.baylor.GroupFive.models.enums.Privilege;

/**
 *
 */
public class Account {

    private String name;
    private int phoneNumber;
    private String username;
    private String password;
    private String email;
    private Address address;
    private Privilege privilege;

    /**
     *
     * @param name_
     * @param phoneNumber_
     * @param username_
     * @param password_
     * @param email_
     * @param address_
     * @param privilege_
     */
    public Account( String name_,
                    int phoneNumber_,
                    String username_,
                    String password_,
                    String email_,
                    Address address_,
                    Privilege privilege_) {
        name = name_;
        phoneNumber = phoneNumber_;
        username = username_;
        password = password_;
        email = email_;
        address = address_;
        privilege = privilege_;
    }

    // >>>> Setters >>>>

    /**
     *
     * @param name
     */
    public void setName(String name) { this.name = name; }

    /**
     *
     * @param phoneNumber
     */
    public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }

    /**
     *
     * @param username
     */
    public void setUsername(String username) { this.username = username; }

    /**
     *
     * @param password
     */
    public void setPassword(String password) { this.password = password; }

    /**
     *
     * @param email
     */
    public void setEmail(String email) { this.email = email; }

    /**
     *
     * @param address
     */
    public void setAddress(Address address) { this.address = address; }

    /**
     *
     * @param privilege
     */
    public void setPrivilege(Privilege privilege) { this.privilege = privilege; }
    // <<<< Setters <<<<

    // >>>> Getters >>>>

    /**
     *
     * @return
     */
    public String getName() { return name; }

    /**
     *
     * @return
     */
    public int getPhoneNumber() { return phoneNumber; }

    /**
     *
     * @return
     */
    public String getUsername() { return username; }

    /**
     *
     * @return
     */
    public String getPassword() { return password; }

    /**
     *
     * @return
     */
    public String getEmail() { return email; }

    /**
     *
     * @return
     */
    public Address getAddress() { return address; }

    /**
     *
     * @return
     */
    public Privilege getPrivilege() { return privilege; }
    // <<<< Getters <<<<
}
