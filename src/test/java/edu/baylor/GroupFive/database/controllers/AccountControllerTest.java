package edu.baylor.GroupFive.database.controllers;

import edu.baylor.GroupFive.database.TestHotelDatabase;
import org.junit.jupiter.api.BeforeAll;

public class AccountControllerTest {

    @BeforeAll
    static void init() {
        new TestHotelDatabase();
    }

}
