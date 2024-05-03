package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.database.TestDatabase;
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

    private static List<User> userInits = new ArrayList<>();

    /**
     * Initializes our users before all tests.
     */
    @BeforeAll
    static void initUsers() {
        userInits.add(new User("Joe", "Smith", "Bongo", CoreUtils.hashPassword("p1234"), "admin"));
        userInits.add(new User("Kevin", "James", "KevDog", CoreUtils.hashPassword("1234"), "clerk"));
        userInits.add(new User("Axel", "Washington", "Axel112", CoreUtils.hashPassword("1234"), "clerk"));
        userInits.add(new User("Andrew", "Wiles", "BigA", CoreUtils.hashPassword("1234"), "clerk"));
        userInits.add(new User("Larry", "AB", "LarryTheLobster", CoreUtils.hashPassword("1234"), "guest"));
        userInits.add(new User("Josh", "Smith", "Jman", CoreUtils.hashPassword("1234"), "guest"));
        userInits.add(new User("Tyler", "Lee", "T-Lee", CoreUtils.hashPassword("1234"), "guest"));
        userInits.add(new User("Antoine", "Wu", "Ant", CoreUtils.hashPassword("1234"), "guest"));
        userInits.add(new User("Everett", "Anderson", "andyEv", CoreUtils.hashPassword("1234"), "guest"));
        userInits.add(new User("Icko", "Iben", "ickoxii", CoreUtils.hashPassword("sicem"), "guest"));
    }
    
    /**
     * Initializes our database before each test.
     */
    @BeforeEach
    void init() {
        TestDatabase db = new TestDatabase();
    }

    /**
     * Tears down our database after each test.
     */
    @AfterEach
    void tearDown() {
        TestDatabase.dbTearDown();
    }

    /**
     * Tests {@link UserDAO#save(User)}.
     */
    @DisplayName("Add User")
    @Test
    public void addAUser() {
        UserDAO conn = new UserDAO();
        User newUser = new User("Cole", "Flenniken", "colef8", CoreUtils.hashPassword("cole123"), "admin");

        assertTrue(conn.save(newUser) == 1);
    }

    /**
     * Tests {@link UserDAO#getByUsername(String)}.
     */
    @DisplayName("Add and Get User")
    @Test
    public void addThenGetUser(){
        UserDAO conn = new UserDAO();
        User newUser = new User("test", "user", "test", CoreUtils.hashPassword("p1234"), "admin");
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
        
        // Compare the users in the database to the users we initialized
        for (int i = 0; i < userInits.size(); i++) {
            assert(users.get(i).equals(userInits.get(i)));
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
        User newUser = new User("ColeS", "Flenniken", "colef888", CoreUtils.hashPassword("cole123"), "Clerk");
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
        User newUser = new User("Cole", "Flenniken", "colef888", CoreUtils.hashPassword("cole123"), "Clerk");
        UserDAO conn = new UserDAO();

        // Delete user
        assert(conn.delete(newUser) == 1);
    }

}
