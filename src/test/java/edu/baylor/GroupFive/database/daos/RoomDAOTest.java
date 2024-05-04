package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.database.TestHotelDatabase;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.enums.Theme;
import edu.baylor.GroupFive.models.enums.BedType;
import edu.baylor.GroupFive.models.enums.Quality;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests methods of {@link edu.baylor.GroupFive.database.daos.RoomDAO}.
 *
 * @author Cole
 */
public class RoomDAOTest {

    /**
     * Initialize our database.
     */
    @BeforeEach
    void init(){
        new TestHotelDatabase();
    }

    /**
     * Tests {@link RoomDAO#save(Room)}.
     */
    @Test
    void addARoom(){
        new TestHotelDatabase();
        Room newRoom = new Room(995, Quality.ECONOMY, Theme.UrbanElegance, true, 5, BedType.KING, 12.34);
        RoomDAOExt conn = new RoomDAOExt();
        Integer added = conn.save(newRoom);
        assert(added.equals(1));
    }

    /**
     * Tests {@link RoomDAO#get(int)}.
     */
    @Test
    void addAndGetRoom(){
        new TestHotelDatabase();
        Room newRoom = new Room(995, Quality.COMFORT, Theme.NatureRetreat, true, 5, BedType.KING, 12.34);
        RoomDAOExt conn = new RoomDAOExt();
        Integer added = conn.save(newRoom);
        assertNotNull(added);
        Room pulledRoom = conn.get(995);
        System.out.println(pulledRoom.getDailyPrice());
        assert(pulledRoom.equals(newRoom));
    }

    /**
     * Tests {@link RoomDAO#get(int)} on existing room.
     */
    @Test
    void getSetupRoom(){
        new TestHotelDatabase();
        RoomDAOExt conn = new RoomDAOExt();
        Room pulledRoom = conn.get(109);
        assert(pulledRoom != null);
    }

    /**
     * Tests {@link RoomDAO#get(int)} on non-existing room.
     */
    @Test
    void getNonexistingRoom(){
        new TestHotelDatabase();
        RoomDAOExt conn = new RoomDAOExt();
        Room pulledRoom = conn.get(1709);
        assert(pulledRoom == null);
    }


    /**
     * Tests {@link RoomDAO#update(Room)}.
     */
    @Test
    void modifyRoom(){
        new TestHotelDatabase();
        Room myRoom = new Room(99, Quality.EXECUTIVE, Theme.VintageCharm,true, 11, BedType.KING, 90D);
        RoomDAOExt conn = new RoomDAOExt();

        Integer added = conn.save(myRoom);
        if(added != 1){
            System.out.println("Initial add failed");
            assert false;
        }

        myRoom.setNumBeds(6);

        Integer isModified = conn.update(myRoom);
        if(isModified != 1){
            System.out.println("Initial modify failed");
            assert false;
        }

        Room modifiedRoom = conn.get(99);
        assert(modifiedRoom.getNumBeds().equals(6));
    }

}