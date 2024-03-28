package edu.baylor.GroupFive.database.roomDAO;

import edu.baylor.GroupFive.models.Room;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
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
            URL url = getClass().getResource("Rooms.txt");
            File myFile = new File(url.toURI());
            Scanner myReader = new Scanner(myFile);
            while(myReader.hasNextLine()){
                //assume row looks like rooomNumber,quality,theme,smoking,singles,doubles,queens,kings
                String[] row = myReader.nextLine().split(",");
                int roomNumber = Integer.parseInt(row[0]);
                int quality = Integer.parseInt(row[1]);
                String theme = row[2];
                boolean smoking = Boolean.parseBoolean(row[3]);
                int numBeds = Integer.parseInt(row[4]);
                String bedType = row[5];

                if(Integer.parseInt(row[4]) >= currID){
                    currID = Integer.parseInt(row[4]) + 1;
                }
                Room.THEME themeEnum = theme.equals("themeA") ? Room.THEME.ThemeA
                    : theme.equals("themeB") ? Room.THEME.ThemeB
                    : theme.equals("themeC") ? Room.THEME.ThemeC
                    : null;
                data.add(new Room(roomNumber, quality, themeEnum, smoking, numBeds, Room.BED_TYPE.valueOf(bedType)));
            }
            myReader.close();
        }catch(Exception e){
            System.out.println("Error unable to find file");
            e.printStackTrace();
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
                    +room.getNumBeds()+","
                    +room.getBedType().name()
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