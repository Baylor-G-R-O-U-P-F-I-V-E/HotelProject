package edu.baylor.GroupFive.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Room {
    public enum THEME { ThemeA, ThemeB, ThemeC };
    public enum BED_TYPE {Single, Double, Queen, King};

    private int roomNumber;
    //private QualityDescription quality;
    private int quality;
    private THEME theme;
    private boolean smoking;
    private BED_TYPE bedType;
    private int numBeds;

    private List<Booking> bookings;

    Room(){
        this.roomNumber = -1;
        this.bookings = new ArrayList<>();
    }

    public Room(int roomNumber, int quality, THEME theme, boolean smoking, int numBeds, BED_TYPE bedType){
        this.roomNumber = roomNumber;
        this.quality = quality;
        this.theme = theme;
        this.smoking = smoking;
        this.numBeds = numBeds;
        this.bedType = bedType;
    }

//    public boolean isAvailableOn(Date date){
//        return this.bookings.stream().findAny();
//    }
    public BED_TYPE getBedType() {
        return bedType;
    }

    public void setBedType(BED_TYPE bedType) {
        this.bedType = bedType;
    }

    public int getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }
    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;
    }

    public THEME getTheme() {
        return theme;
    }

    public void setTheme(THEME theme) {
        this.theme = theme;
    }

    public boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> reservations) {
        this.bookings = reservations;
    }

}
