package edu.baylor.GroupFive.database.userDAO;
import  edu.baylor.GroupFive.database.dbSetup;
import edu.baylor.GroupFive.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserDatabaseConnection {
    @BeforeEach
    void init() {
        dbSetup setup = new dbSetup();
    }


    @Test
    void addAUser() {
        UserDatabaseConnection conn = new UserDatabaseConnection();
        User newUser = new User("Cole", "Flenniken", "colef8", "cole123");

        assertEquals(conn.addUser(newUser), true);
    }

    @Test
    void addThenGetUser(){
        UserDatabaseConnection conn = new UserDatabaseConnection();
        User newUser = new User("Cole", "Flenniken", "colef8", "cole123");
        conn.addUser(newUser);
        User cole = conn.getUser("colef8");
        assertEquals(cole,newUser);
    }

    @Test
    void findExistingUserFromSetup(){
        UserDatabaseConnection conn = new UserDatabaseConnection();
        User cole = conn.getUser("Axel112");
        assert(cole != null);
    }

    @Test
    void findNonExistingUser(){
        UserDatabaseConnection conn = new UserDatabaseConnection();
        User cole = conn.getUser("Axel113");
        assert(null == cole);
    }


}
