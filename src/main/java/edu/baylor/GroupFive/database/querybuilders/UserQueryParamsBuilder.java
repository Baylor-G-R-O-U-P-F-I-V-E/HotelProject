package edu.baylor.GroupFive.database.querybuilders;

import java.sql.PreparedStatement;

public class UserQueryParamsBuilder {
    protected String query;
    protected String firstName;
    protected String lastName;
    protected String userName;
    protected String password;
    protected String privilege;

    public UserQueryParamsBuilder(String query_, String firstName_, String lastName_, String userName_, String password_, String privilege_) {
        firstName = firstName_;
        lastName = lastName_;
        userName = userName_;
        password = password_;
        privilege = privilege_;
    }

    public PreparedStatement build(String firstName, String lastName, String userName, String password, String privilege) {
        StringBuilder builder = new StringBuilder();
        return null;
    }
}
