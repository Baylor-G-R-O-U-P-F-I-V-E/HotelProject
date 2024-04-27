package edu.baylor.GroupFive.database.daos;

import edu.baylor.GroupFive.database.DbConnection;
import edu.baylor.GroupFive.models.Room;
import edu.baylor.GroupFive.models.enums.BedType;
import edu.baylor.GroupFive.models.enums.Quality;
import edu.baylor.GroupFive.models.enums.Theme;
import edu.baylor.GroupFive.util.exceptions.BadConnectionException;
import edu.baylor.GroupFive.util.logging.G5Logger;

import java.sql.*;
import java.util.*;

/**
 * The RoomDAO class provides methods for interacting with room data in a database.
 *
 * This class implements the {@code BaseDAO} abstract class.
 *
 * @see edu.baylor.GroupFive.database.daos.BaseDAO
 * @author Brendon
 * @author Cole
 */
public class RoomDAO extends BaseDAO<Room>{

    public RoomDAO(){}

    /**
     * Retrieves all rooms in our database.
     *
     * @return A List of every room in our database.
     */
    public List<Room> getAll() {

        try (Connection connection = DbConnection.getConnection(); Statement statement = connection.createStatement()) {
            
            String sqlQuery = "SELECT * FROM Room";
            ResultSet rs = statement.executeQuery(sqlQuery);
            List<Room> output = new ArrayList<>();

            while (rs.next()) {
                Room out = new Room(rs.getInt("roomNumber"),
                        Quality.fromString(rs.getString("quality")),
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

    /**
     * Saves a room in our database. Either inserts or updates behind-the-scenes.
     *
     * @param room Room to save.
     * @return Number of rows affected by query.
     */
    public Integer save(Room room){

        try (Connection connection = DbConnection.getConnection(); Statement statement = connection.createStatement()) {
            
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

    /**
     * Inserts a room into our database.
     *
     * @param newRoom Room to insert.
     * @return Number of rows affected by query.
     */
    public Integer insert(Room newRoom){

        try (Connection connection = DbConnection.getConnection(); Statement statement = connection.createStatement()) {
            
            String sqlInsert = "INSERT INTO ROOM(roomNumber,quality,theme,smoking,bedType,numbeds,dailyprice) VALUES (" +
                newRoom.getRoomNumber().toString() + ",'" + Quality.formatQuality(newRoom.getQuality()) +
                "','" + newRoom.getTheme().toString() + "'," + newRoom.isSmoking().toString() + ",'" +
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

    /**
     * Retrieves a room from our database given a room number. Returns the
     * Room if found, {@code null} otherwise.
     *
     * @param roomNumber Room number.
     * @return Room object if found, {@code null} otherwise.
     */
    public Room get(int roomNumber){

        try (Connection connection = DbConnection.getConnection(); Statement statement = connection.createStatement()) {
            
            String sqlQuery = "SELECT * FROM Room WHERE roomNumber = " + String.valueOf(roomNumber);
            ResultSet rs = statement.executeQuery(sqlQuery);

            while (rs.next()) {
                Room out = new Room(rs.getInt("roomNumber"),
                        Quality.fromString(rs.getString("quality")),
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


    /**
     * Updates an existing room in our database.
     *
     * @param updatedInfo Room with updated information.
     * @return Number of rows affected by query.
     */
    public Integer update(Room updatedInfo){

        try (Connection connection = DbConnection.getConnection(); Statement statement = connection.createStatement()) {
            
            String sqlUpdate = "UPDATE ROOM SET quality = '" + Quality.formatQuality(updatedInfo.getQuality()) +
                "', theme = '" + updatedInfo.getTheme().toString() + "', smoking = " + updatedInfo.isSmoking().toString() +
                ", bedType = '" + updatedInfo.getBedType().toString() + "', numbeds = " + updatedInfo.getNumBeds().toString() +
                ", dailyPrice = " + updatedInfo.getDailyPrice().toString() + " WHERE roomNumber = " + updatedInfo.getRoomNumber().toString();
            statement.executeUpdate(sqlUpdate);

            return 1;

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());;
            return 0;
        }

    }

    /**
     * Deletes a room in our database.
     *
     * @param room Room to delete.
     * @return Number of rows affected by query.
     */
    public Integer delete(Room room){

        try (Connection connection = DbConnection.getConnection(); Statement statement = connection.createStatement()) {
            
            // FIXME should not actually delete? -Icko
            String sqlDelete = "DELETE FROM room WHERE roomNumber = " + room.getRoomNumber();
            statement.execute(sqlDelete);

            return 1;

        } catch (SQLException | BadConnectionException e) {
            G5Logger.logger.error(e.getMessage());
            return 0;
        }

    }

}
