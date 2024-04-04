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
        dbSetup db = new dbSetup();
        Room newRoom = new Room(995, 1, Room.THEME.ThemeA, true, 5, Room.BED_TYPE.KING, 12.34);
        RoomDatabaseConnection conn = new RoomDatabaseConnection();
        Boolean added = conn.addRoom(newRoom);
        assert(added.equals(true));

    }

    @Test
    void addAndGetRoom(){
        dbSetup db = new dbSetup();
        Room newRoom = new Room(995, 1, Room.THEME.ThemeA, true, 5, Room.BED_TYPE.KING, 12.34);
        RoomDatabaseConnection conn = new RoomDatabaseConnection();
        Boolean added = conn.addRoom(newRoom);
        Room pulledRoom = conn.getRoom(995);
        System.out.println(pulledRoom.getDailyPrice());
        assert(pulledRoom.equals(newRoom));
    }

    @Test
    void getSetupRoom(){
        dbSetup db = new dbSetup();
        RoomDatabaseConnection conn = new RoomDatabaseConnection();
        Room pulledRoom = conn.getRoom(109);
        assert(pulledRoom != null);
    }

    @Test
    void getNonexistingRoom(){
        dbSetup db = new dbSetup();
        RoomDatabaseConnection conn = new RoomDatabaseConnection();
        Room pulledRoom = conn.getRoom(1709);
        assert(pulledRoom == null);
    }


    @Test
    void modifyRoom(){
        dbSetup db = new dbSetup();
        Room myRoom = new Room(99,3, Room.THEME.ThemeA,true, 11, Room.BED_TYPE.KING, 90D);
        RoomDatabaseConnection conn = new RoomDatabaseConnection();

        Boolean added = conn.addRoom(myRoom);
        if(!added){
            System.out.println("Initial add failed");
            assert false;
        }

        myRoom.setNumBeds(6);

        Boolean isModified = conn.modifyRoom(myRoom);
        if(!isModified){
            System.out.println("Initial modify failed");
            assert false;
        }

        Room modifiedRoom = conn.getRoom(99);
        assert(modifiedRoom.getNumBeds().equals(6));
    }

}
