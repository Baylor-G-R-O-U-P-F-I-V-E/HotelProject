package edu.baylor.GroupFive.models;

import edu.baylor.GroupFive.models.enums.Privilege;

public class Account {

    private String name;
    private int phoneNumber;
    private String username;
    private String password;
    private String email;
    private Address address;
    private Privilege privilege;

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
    public void setName(String name) { this.name = name; }
    public void setPhoneNumber(int phoneNumber) { this.phoneNumber = phoneNumber; }
    public void setUsername(String username) { this.username = username; }
    public void setPassword(String password) { this.password = password; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(Address address) { this.address = address; }
    public void setPrivilege(Privilege privilege) { this.privilege = privilege; }
    // <<<< Setters <<<<

    // >>>> Getters >>>>
    public String getName() { return name; }
    public int getPhoneNumber() { return phoneNumber; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
    public Address getAddress() { return address; }
    public Privilege getPrivilege() { return privilege; }
    // <<<< Getters <<<<
}
