package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.database.DbSetup;
import edu.baylor.GroupFive.models.User;
import edu.baylor.GroupFive.util.CoreUtils;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

/**
 * Tests methods in {@link edu.baylor.GroupFive.database.daos.UserDAO}.
 *
 * @author Cole
 * @author Brendon
 */
public class UserDAOTest {

    private static List<Object[]> userInits = new ArrayList<>();

    /**
     * Initializes our users before all tests.
     */
    @BeforeAll
    static void initUsers() {
        userInits.add(new Object[] { "Joe",     "Smith",        "Bongo",            "p1234",    "admin" });
        userInits.add(new Object[] { "Kevin",   "James",        "KevDog",           "1234",     "clerk" });
        userInits.add(new Object[] { "Axel",    "Washington",   "Axel112",          "1234",     "clerk" });
        userInits.add(new Object[] { "Andrew",  "Wiles",        "BigA",             "1234",     "clerk" });
        userInits.add(new Object[] { "Larry",   "AB",           "LarryTheLobster",  "1234",     "guest" });
        userInits.add(new Object[] { "Josh",    "Smith",        "Jman",             "1234",     "guest" });
        userInits.add(new Object[] { "Tyler",   "Lee",          "T-Lee",            "1234",     "guest" });
        userInits.add(new Object[] { "Antoine", "Wu",           "Ant",              "1234",     "guest" });
        userInits.add(new Object[] { "Everett", "Anderson",     "andyEv",           "1234",     "guest" });
        userInits.add(new Object[] { "Icko",    "Iben",         "ickoxii",          "sicem",    "guest" });
    }
    
    /**
     * Initializes our database before each test.
     */
    @BeforeEach
    void init() {
        DbSetup db = new DbSetup();
    }

    /**
     * Tears down our database after each test.
     */
    @AfterEach
    void tearDown() {
        DbSetup.dbTearDown();
    }

    /**
     * Tests {@link UserDAO#save(User)}.
     */
    @DisplayName("Add User")
    @Test
    public void addAUser() {
        UserDAO conn = new UserDAO();
        User newUser = new User("Cole", "Flenniken", "colef8", "cole123", "admin");

        assertTrue(conn.save(newUser) == 1);
    }

    /**
     * Tests {@link UserDAO#getByUsername(String)}.
     */
    @DisplayName("Add and Get User")
    @Test
    public void addThenGetUser(){
        UserDAO conn = new UserDAO();
        User newUser = new User("test", "user", "test", "p1234", "admin");
        assertTrue(conn.save(newUser) == 1);
        User test = conn.getByUsername("test");
        assert(test.equals(newUser));
    }

    /**
     * Tests {@link UserDAO#getAll()}.
     */
    @DisplayName("Get All Users")
    @Test
    public void getAllUsers(){
        UserDAO conn = new UserDAO();
        List<User> users = conn.getAll();
        for (User u : users) {
            System.out.println(u.toString());
        }
    }

    /**
     * Tests {@link UserDAO#getByUsername(String)} on existing user.
     */
    @DisplayName("Find Existing User")
    @Test
    public void findExistingUserFromSetup(){
        UserDAO conn = new UserDAO();
        User test = new User("Axel", "Washington", "Axel112", CoreUtils.hashPassword("1234"), "clerk");
        User result = conn.getByUsername("Axel112");
        System.out.println(test.toString());
        System.out.println(result.toString());
        assert(test.equals(result));
    }

    /**
     * Tests {@link UserDAO#getByUsername(String)} on non-existing user.
     */
    @DisplayName("Find Non-Existing User")
    @Test
    public void findNonExistingUser(){
        UserDAO conn = new UserDAO();
        User test = conn.getByUsername("Axel113");
        assertNull(test);
    }


    /**
     * Tests {@link UserDAO#update(User)}.
     */
    @DisplayName("Update User")
    @Test
    public void modifyUser(){
        User newUser = new User("ColeS", "Flenniken", "colef888", "cole123", "Clerk");
        UserDAO conn = new UserDAO();

        // Add user
        assert(conn.save(newUser) == 1);

        newUser.setFirstName("Modified");

        // Modify user
        assert(conn.update(newUser) == 1);

        User h = conn.getByUsername("colef888");
        assert(h.getFirstName().equals("Modified"));
    }

    /**
     * Tests {@link UserDAO#delete(User)}.
     */
    @DisplayName("Delete User")
    @Test
    public void deleteUser(){
        User newUser = new User("Cole", "Flenniken", "colef888", "cole123", "Clerk");
        UserDAO conn = new UserDAO();

        // Delete user
        assert(conn.delete(newUser) == 1);
    }

}
