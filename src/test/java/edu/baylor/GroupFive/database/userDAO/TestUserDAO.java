package edu.baylor.GroupFive.database.userDAO;

import edu.baylor.GroupFive.util.CoreUtils;
import edu.baylor.GroupFive.database.DbSetup;
import edu.baylor.GroupFive.database.daos.UserDAO;
import edu.baylor.GroupFive.models.enums.Privilege;
import edu.baylor.GroupFive.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
  Password hashes:
[p1234] = 4cca6a0a73b811a47a2c4032f32a3db4bcd1918b354d1697df9bdcfde004ee5e
[1234] = 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4
[1234] = 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4
[1234] = 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4
[1234] = 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4
[1234] = 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4
[1234] = 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4
[1234] = 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4
[1234] = 03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4
[sicem] = d565907d5124c5a0c9e8155065d458bfa0e27db0fafcc627af24e6d7e6e6db09
*/

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestUserDAO {
    @BeforeEach
    void init() {
        // DbSetup setup = new DbSetup();
    }


    @Test
    void addAUser() {
        DbSetup db = new DbSetup();
        UserDAO conn = new UserDAO();
        User newUser = new User("Cole", "Flenniken", "colef8", CoreUtils.hashPassword("cole123"), "Admin");

        assert(conn.save(newUser) == 1);
    }

    @Test
    void addThenGetUser(){
        DbSetup db = new DbSetup();
        UserDAO conn = new UserDAO();
        User newUser = new User("Cole", "Flenniken", "colef8", CoreUtils.hashPassword("cole123"), "Clerk");
        conn.save(newUser);
        User cole = conn.getByUsername("colef8");
        assertEquals(cole,newUser);
    }

    @Test
    void findExistingUserFromSetup(){
        DbSetup db = new DbSetup();
        UserDAO conn = new UserDAO();
        User cole = conn.getByUsername("Axel112");
        assert(cole != null);
    }

    @Test
    void findNonExistingUser(){
        DbSetup db = new DbSetup();
        UserDAO conn = new UserDAO();
        User cole = conn.getByUsername("Axel113");
        assert(null == cole);
    }


    @Test
    void modifyUser(){
        DbSetup db = new DbSetup();
        User newUser = new User("ColeS", "Flenniken", "colef888", CoreUtils.hashPassword("cole123"), "Clerk");
        UserDAO conn = new UserDAO();
        Integer added = conn.save(newUser);
        assert(added == 1);

        newUser.setFirstName("Modified");

        conn.update(newUser);

        User h = conn.getByUsername("colef888");
        assert(h.getFirstName().equals("Modified"));
    }

}
