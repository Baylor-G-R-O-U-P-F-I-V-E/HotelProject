package edu.baylor.GroupFive.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {
    public enum THEME { ThemeA, ThemeB, ThemeC };
    public enum BED_TYPE {SINGLE, DOUBLE, QUEEN, KING};

    private Integer roomNumber;
    //private QualityDescription quality;
    private Integer quality;
    private THEME theme;
    private Boolean smoking;
    private BED_TYPE bedType;
    private Integer numBeds;
    private Double dailyPrice;

    private List<Booking> bookings;

    Room(){
        this.roomNumber = -1;
        this.bookings = new ArrayList<>();
    }

    public Room(int roomNumber, int quality, THEME theme, boolean smoking, int numBeds, BED_TYPE bedType, double dailyPrice) {
        this.roomNumber = roomNumber;
        this.quality = quality;
        this.theme = theme;
        this.smoking = smoking;
        this.numBeds = numBeds;
        this.bedType = bedType;
        this.dailyPrice = dailyPrice;
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

    public Integer getNumBeds() {
        return numBeds;
    }

    public void setNumBeds(int numBeds) {
        this.numBeds = numBeds;
    }
    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Integer getQuality() {
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

    public Boolean isSmoking() {
        return smoking;
    }

    public void setSmoking(boolean smoking) {
        this.smoking = smoking;
    }

    public Double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public void setSmoking(Boolean canSmoke){
        smoking = canSmoke;
    }

    public Boolean getSmoking(){
        return smoking;
    }
    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> reservations) {
        this.bookings = reservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(getRoomNumber(), room.getRoomNumber()) && Objects.equals(getQuality(), room.getQuality()) && getTheme() == room.getTheme() && Objects.equals(getSmoking(), room.getSmoking()) && getBedType() == room.getBedType() && Objects.equals(getNumBeds(), room.getNumBeds()) && Objects.equals(getDailyPrice(), room.getDailyPrice()) && Objects.equals(getBookings(), room.getBookings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber(), getQuality(), getTheme(), getSmoking(), getBedType(), getNumBeds(), getDailyPrice(), getBookings());
    }

    @Override
    public String toString() {
        return "Room Number: " + roomNumber +
                "\nQuality: " + quality +
                "\nTheme: " + theme +
                "\nSmoking: " + smoking +
                "\nBed Type: " + bedType +
                "\nNumber of Beds: " + numBeds +
                "\nDaily Price: " + dailyPrice;
    }
}