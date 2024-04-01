package edu.baylor.GroupFive.database.roomDAO;
import edu.baylor.GroupFive.database.dbSetup;

import edu.baylor.GroupFive.models.Room;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

public class TestRoomDatabaseConnection {

    @BeforeEach
    void init(){
        dbSetup db = new dbSetup();
    }


    @Test
    void addARoom(){
        Room newRoom = new Room(995, 1, Room.THEME.ThemeA, true, 5, Room.BED_TYPE.KING, 12.34);
        RoomDatabaseConnection conn = new RoomDatabaseConnection();
        Boolean added = conn.addRoom(newRoom);
        assert(added.equals(true));

    }

    @Test
    void addAndGetRoom(){
        Room newRoom = new Room(995, 1, Room.THEME.ThemeA, true, 5, Room.BED_TYPE.KING, 12.34);
        RoomDatabaseConnection conn = new RoomDatabaseConnection();
        Boolean added = conn.addRoom(newRoom);
        Room pulledRoom = conn.getRoom(995);
        System.out.println(pulledRoom.getDailyPrice());
        assert(pulledRoom.equals(newRoom));
    }

    @Test
    void getSetupRoom(){
        RoomDatabaseConnection conn = new RoomDatabaseConnection();
        Room pulledRoom = conn.getRoom(109);
        assert(pulledRoom != null);
    }

    @Test
    void getNonexistingRoom(){
        RoomDatabaseConnection conn = new RoomDatabaseConnection();
        Room pulledRoom = conn.getRoom(1709);
        assert(pulledRoom == null);
    }

}
