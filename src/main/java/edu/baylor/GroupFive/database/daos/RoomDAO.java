package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.enums.BedType;
import edu.baylor.GroupFive.models.enums.Theme;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;
import edu.baylor.GroupFive.util.logging.G5Logger;

import java.sql.*;
import java.util.*;

public class RoomDAO extends BaseDAO<Room>{

    public RoomDAO(){}

    public List<Room> getAll() {

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            
            String sqlQuery = "SELECT * FROM Room";
            ResultSet rs = statement.executeQuery(sqlQuery);
            List<Room> output = new ArrayList<>();

            while (rs.next()) {
                Room out = new Room(rs.getInt("roomNumber"),
                        rs.getInt("quality"),
                        Theme.fromString(rs.getString("theme")),
                        rs.getBoolean("smoking"),
                        rs.getInt("numbeds"),
                        BedType.fromString(rs.getString("bedtype")),
                        rs.getDouble("dailyPrice")
                );

                output.add(out);

            }

            return output;

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());;
            return null;
        }

    }

    public Integer save(Room room){

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            
            Room exists = get(room.getRoomNumber());
            
            if (exists == null){
                return insert(room);
            } else {
                return update(room);
            }

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());;
            return null;
        }

    }

    public Integer insert(Room newRoom){

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            
            String sqlInsert = "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (" +
                newRoom.getRoomNumber().toString() + "," + newRoom.getQuality() +
                ",'" + newRoom.getTheme().toString() + "'," + newRoom.isSmoking().toString() + ",'" +
                newRoom.getBedType().toString() + "'," +
                newRoom.getNumBeds().toString()  + "," +
                newRoom.getDailyPrice().toString() + ")";
            statement.executeUpdate(sqlInsert);

            return 1;

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());;
            return 0;
        }
       
    }

    public Room get(int roomNumber){

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            
            String sqlQuery = "SELECT * FROM Room WHERE roomNumber = " + String.valueOf(roomNumber);
            ResultSet rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                Room out = new Room(rs.getInt("roomNumber"),
                        rs.getInt("quality"),
                        Theme.fromString(rs.getString("theme")),
                        rs.getBoolean("smoking"),
                        rs.getInt("numbeds"),
                        BedType.fromString(rs.getString("bedtype")),
                        rs.getDouble("dailyPrice")
                );

                return out;
            }

            return null;

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());;
            return null;
        }

    }



    public Integer update(Room updatedInfo){

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            
            String sqlUpdate = "UPDATE ROOM SET quality = " + updatedInfo.getQuality() +
                ", theme = '" + updatedInfo.getTheme().toString() + "', smoking = " + updatedInfo.isSmoking().toString() +
                ", bedType = '" + updatedInfo.getBedType().toString() + "', numbeds = " + updatedInfo.getNumBeds().toString() +
                ", dailyPrice = " + updatedInfo.getDailyPrice().toString() + " WHERE roomNumber = " + updatedInfo.getRoomNumber().toString();
            statement.executeUpdate(sqlUpdate);

            return 1;

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());;
            return 0;
        }

    }

    public Integer delete(Room room){

        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            
            String sqlDelete = "DELETE FROM room WHERE roomNumber = " + room.getRoomNumber();
            statement.execute(sqlDelete);

            return 1;

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
            return 0;
        }

    }

}
