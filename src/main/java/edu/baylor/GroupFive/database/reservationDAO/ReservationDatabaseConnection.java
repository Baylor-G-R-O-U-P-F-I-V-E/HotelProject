package edu.baylor.GroupFive.database.reservationDAO;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.ArrayList;

public class ReservationDatabaseConnection {

    
    ArrayList<Reservation> data;
    Integer currID = 0;
    ReservationDatabaseConnection(){
        this.data = new ArrayList<>();
        try{
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH);
            File myFile = new File("Reservations.txt");
            Scanner myReader = new Scanner(myFile);
            while(myReader.hasNextLine()){
                //assume row looks like startDate,endDate,guestID,roomID
                String[] row = myReader.nextLine().split(",");
                Date startDate = formatter.parse(row[0]);
                Date endDate = formatter.parse(row[1]);
                if(Integer.parseInt(row[4]) >= currID){
                    currID = Integer.parseInt(row[4]) + 1;
                }
                data.add(new Reservation(startDate,endDate,row[2],row[3],row[4], Double.parseDouble(row[5])));
            }
            myReader.close();
        }catch(FileNotFoundException e){
            System.out.println("Error unable to find file");
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addReservation(Date startDate, Date endDate, String roomID, String guestID, Double price){
        Reservation r = new Reservation(startDate,endDate,roomID,guestID, currID.toString(), price);
        currID++;
        data.add(r);
    }

    public void save()  {
        try{
            FileWriter myWriter = new FileWriter("filename.txt");
            StringBuilder toF = new StringBuilder();
            for(Reservation r : data){
                toF.append(r.startDate.toString() + "," + r.endDate.toString() + "," +
                             r.guestID + "," + r.roomID + "," + r.reservationID + "," + r.price + '\n');

            }
            myWriter.write(toF.toString());
            //Date startDate, Date endDate, String guestID, String roomID, String reservationID
        }catch (IOException e){
            System.out.println("Unable to save");
        }
    }


    public Boolean cancelReservation(String reservationID){
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).reservationID.equals(reservationID)){
                data.remove(i);
                return true;
            }
        }
        return false;
    }

    public Reservation getInfo(String reservationID){
        for(int i = 0; i < data.size(); i++){
            if(data.get(i).reservationID.equals(reservationID)){
                return data.get(i);
            }
        }
        return null;
    }



    public Boolean checkIfAvailable(String roomID, Date startDate, Date endDate){
        for(Reservation r : data){
            if(!roomID.equals(r.roomID)){continue;}
            if((startDate.after(r.startDate) || startDate.equals(r.startDate)) && startDate.before(r.endDate)){
                return false;
            }
            if(endDate.after(r.startDate) && (endDate.equals(r.endDate) || endDate.before(r.endDate))){
                return false;
            }

            if((startDate.after(r.startDate) || startDate.equals(r.startDate)) &&
                    (endDate.equals(r.endDate) || endDate.before(r.endDate))){
                return false;
            }
        }
        return true;
    }









    
}
