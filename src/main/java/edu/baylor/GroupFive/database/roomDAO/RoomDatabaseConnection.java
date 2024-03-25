package edu.baylor.GroupFive.database.reservationDAO;

import edu.baylor.GroupFive.models.Room;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class RoomDatabaseConnection {


    ArrayList<Room> data;
    Integer currID = 0;
    public RoomDatabaseConnection(){
        this.data = new ArrayList<>();
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
            File myFile = new File("Rooms.txt");
            Scanner myReader = new Scanner(myFile);
            while(myReader.hasNextLine()){
                //assume row looks like rooomNumber,quality,theme,smoking,singles,doubles,queens,kings
                String[] row = myReader.nextLine().split(",");
                int roomNumber = Integer.parseInt(row[0]);
                int quality = Integer.parseInt(row[1]);
                String theme = row[2];
                boolean smoking = Boolean.parseBoolean(row[3]);
                int singles = Integer.parseInt(row[4]);
                int doubles = Integer.parseInt(row[5]);
                int queens = Integer.parseInt(row[6]);
                int kings = Integer.parseInt(row[7]);

                if(Integer.parseInt(row[4]) >= currID){
                    currID = Integer.parseInt(row[4]) + 1;
                }
                Room.THEME themeEnum = theme.equals("themeA") ? Room.THEME.ThemeA
                    : theme.equals("themeB") ? Room.THEME.ThemeB
                    : theme.equals("themeC") ? Room.THEME.ThemeC
                    : null;
                data.add(new Room(roomNumber, quality, themeEnum, smoking, singles, doubles, queens, kings));
            }
            myReader.close();
        }catch(FileNotFoundException e){
            System.out.println("Error unable to find file");
        }
    }

    public List<Room> getData(){
        return this.data;
    }

    public boolean save()  {
        try{
            FileWriter myWriter = new FileWriter("RoomsSave.txt");
            StringBuilder toF = new StringBuilder();
            for(Room room : data){
                toF.append(room.getRoomNumber()+","
                    +room.getQuality()+","
                    +room.getTheme().name()+","
                    +room.isSmoking()+","
                    +room.getSingleBeds()+","
                    +room.getDoubleBeds()+","
                    +room.getQueenBeds()+","
                    +room.getKingBeds()+","
                    +"\n"
                );

            }
            myWriter.write(toF.toString());
            //Date startDate, Date endDate, String guestID, String roomID, String reservationID
        }catch (IOException e){
            System.out.println("Unable to save");
            return false;
        }
        return true;
    }





}