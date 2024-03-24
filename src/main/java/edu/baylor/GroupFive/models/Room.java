package edu.baylor.GroupFive.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Room {
    public enum THEME { ThemeA, ThemeB, ThemeC };

    private int roomNumber;
    private QualityDescription quality;
    private THEME theme;
    private boolean smoking;
    private int singleBeds;
    private int doubleBeds;
    private int queenBeds;
    private int kingBeds;

    private List<Booking> bookings;

    Room(){
        this.roomNumber = -1;
        this.bookings = new ArrayList<>();
    }

//    public boolean isAvailableOn(Date date){
//        return this.bookings.stream().findAny();
//    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public QualityDescription getQuality() {
        return quality;
    }

    public void setQuality(QualityDescription quality) {
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

    public int getSingleBeds() {
        return singleBeds;
    }

    public void setSingleBeds(int singleBeds) {
        this.singleBeds = singleBeds;
    }

    public int getDoubleBeds() {
        return doubleBeds;
    }

    public void setDoubleBeds(int doubleBeds) {
        this.doubleBeds = doubleBeds;
    }

    public int getQueenBeds() {
        return queenBeds;
    }

    public void setQueenBeds(int queenBeds) {
        this.queenBeds = queenBeds;
    }

    public int getKingBeds() {
        return kingBeds;
    }

    public void setKingBeds(int kingBeds) {
        this.kingBeds = kingBeds;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> reservations) {
        this.bookings = reservations;
    }

}
