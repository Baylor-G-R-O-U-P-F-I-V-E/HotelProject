package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.database.DbSetup;
import edu.baylor.GroupFive.database.daos.UserDAO;
import edu.baylor.GroupFive.models.enums.Privilege;
import edu.baylor.GroupFive.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests methods in {@link edu.baylor.GroupFive.database.daos.UserDAO}.
 *
 * @author Cole
 */
public class UserDAOTest {
    /**
     * Initializes our database.
     */
    @BeforeEach
    void init() {
        DbSetup setup = new DbSetup();
    
    }


    /**
     * Tests {@link UserDAO#save(User)}.
     */
    @Test
    void addAUser() {
        UserDAO conn = new UserDAO();
        User newUser = new User("Cole", "Flenniken", "colef8", "cole123", "Admin");

        assert(conn.save(newUser) == 1);
    }

    /**
     * Tests {@link UserDAO#getByUsername(String)}.
     */
    @Test
    void addThenGetUser(){
        UserDAO conn = new UserDAO();
        User newUser = new User("Cole", "Flenniken", "colef8", "cole123", "Clerk");
        conn.save(newUser);
        User cole = conn.getByUsername("colef8");
        assertEquals(cole,newUser);
    }

    /**
     * Tests {@link UserDAO#getByUsername(String)} on existing user.
     */
    @Test
    void findExistingUserFromSetup(){
        UserDAO conn = new UserDAO();
        User cole = conn.getByUsername("Axel112");
        assert(cole != null);
    }

    /**
     * Tests {@link UserDAO#getByUsername(String)} on non-existing user.
     */
    @Test
    void findNonExistingUser(){
        UserDAO conn = new UserDAO();
        User cole = conn.getByUsername("Axel113");
        assert(null == cole);
    }


    /**
     * Tests {@link UserDAO#update(User)}.
     */
    @Test
    void modifyUser(){
        User newUser = new User("ColeS", "Flenniken", "colef888", "cole123", "Clerk");
        UserDAO conn = new UserDAO();
        Integer added = conn.save(newUser);
        assert(added == 1);

        newUser.setFirstName("Modified");

        conn.update(newUser);

        User h = conn.getByUsername("colef888");
        assert(h.getFirstName().equals("Modified"));
    }

}
