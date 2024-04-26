package edu.baylor.GroupFive.database.querybuilders;

import java.sql.PreparedStatement;

/**
 * Planned class to generate User queries
 *
 * @author Icko
 * */
public class UserQueryParamsBuilder {
    protected String query;
    protected String firstName;
    protected String lastName;
    protected String userName;
    protected String password;
    protected String privilege;

    /**
     * Initialize our query
     *
     * @param query_ Base query statement
     * @param firstName_ Users first name
     * @param lastName_ Users last name
     * @param userName_ Users username
     * @param password_ Users password
     * @param privilege_ Users privilege level
     * @author Icko
     */
    public UserQueryParamsBuilder(String query_, String firstName_, String lastName_, String userName_, String password_, String privilege_) {
        firstName = firstName_;
        lastName = lastName_;
        userName = userName_;
        password = password_;
        privilege = privilege_;
    }

    /**
     * Builds a SQL query and returns a PreparedStatement with that query.
     *
     * @param firstName Users first name
     * @param lastName Users last name
     * @param userName Users username
     * @param password Users password
     * @param privilege Users privilege level.
     * @return PreparedStatement initialized with our query.
     * @author Icko
     */
    public PreparedStatement build(String firstName, String lastName, String userName, String password, String privilege) {
        StringBuilder builder = new StringBuilder();
        return null;
    }
}
