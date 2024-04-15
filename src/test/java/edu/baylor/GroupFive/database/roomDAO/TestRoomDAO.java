package edu.baylor.GroupFive.database.roomDAO;
import edu.baylor.GroupFive.database.DbSetup;
import edu.baylor.GroupFive.database.daos.RoomDAO;

import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.enums.Theme;
import edu.baylor.GroupFive.models.enums.BedType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertEquals;

public class TestRoomDAO {

    @BeforeEach
    void init(){
        DbSetup db = new DbSetup();
    }


    @Test
    void addARoom(){
        DbSetup db = new DbSetup();
        Room newRoom = new Room(995, 1, Theme.UrbanElegance, true, 5, BedType.KING, 12.34);
        RoomDAO conn = new RoomDAO();
        Boolean added = conn.addRoom(newRoom);
        assert(added.equals(true));

    }

    @Test
    void addAndGetRoom(){
        DbSetup db = new DbSetup();
        Room newRoom = new Room(995, 1, Theme.NatureRetreat, true, 5, BedType.KING, 12.34);
        RoomDAO conn = new RoomDAO();
        Boolean added = conn.addRoom(newRoom);
        Room pulledRoom = conn.getRoom(995);
        System.out.println(pulledRoom.getDailyPrice());
        assert(pulledRoom.equals(newRoom));
    }

    @Test
    void getSetupRoom(){
        DbSetup db = new DbSetup();
        RoomDAO conn = new RoomDAO();
        Room pulledRoom = conn.getRoom(109);
        assert(pulledRoom != null);
    }

    @Test
    void getNonexistingRoom(){
        DbSetup db = new DbSetup();
        RoomDAO conn = new RoomDAO();
        Room pulledRoom = conn.getRoom(1709);
        assert(pulledRoom == null);
    }


    @Test
    void modifyRoom(){
        DbSetup db = new DbSetup();
        Room myRoom = new Room(99,3, Theme.VintageCharm,true, 11, BedType.KING, 90D);
        RoomDAO conn = new RoomDAO();

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
