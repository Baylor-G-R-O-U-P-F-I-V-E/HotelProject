package edu.baylor.GroupFive.database;

import edu.baylor.GroupFive.database.services.ReservationServices;
import edu.baylor.GroupFive.database.DbSetup;
import edu.baylor.GroupFive.models.Reservation;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import java.sql.SQLException;


public class TestDbSetup {

    @Test
    public void testDbSetup() {
        DbSetup db = new DbSetup();
    }
}
