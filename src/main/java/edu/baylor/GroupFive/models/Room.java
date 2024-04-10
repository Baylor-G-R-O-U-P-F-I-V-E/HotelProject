package edu.baylor.GroupFive.models;

import edu.baylor.GroupFive.models.enums.Theme;
import edu.baylor.GroupFive.models.enums.BedType;
import edu.baylor.GroupFive.models.enums.Quality;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {

    private Integer roomNumber;
    // TODO change int to enum maybe but also maybe not because
    // that would really fuck up the database
    // private QualityDescription quality;
    private int quality;
    private Theme theme;
    private Boolean smoking;
    private BedType bedType;
    private Integer numBeds;
    private Double dailyPrice;

    private List<Booking> bookings;

    public Room(){
        this.roomNumber = -1;
        this.bookings = new ArrayList<>();
    }

    public Room(int roomNumber, int quality, Theme theme, boolean smoking, int numBeds, BedType bedType, double dailyPrice) {
        this.roomNumber = roomNumber;
        this.quality = quality;
        this.theme = theme;
        this.smoking = smoking;
        this.numBeds = numBeds;
        this.bedType = bedType;
        this.dailyPrice = dailyPrice;
    }

    // >>>> Getters >>>>
    public BedType getBedType() { return bedType; }
    public Integer getNumBeds() { return numBeds; }
    public Integer getRoomNumber() { return roomNumber; }
    public int getQuality() { return quality; }
    public Theme getTheme() { return theme; }
    public Boolean isSmoking() { return smoking; }
    public Double getDailyPrice() { return dailyPrice; }
    public List<Booking> getBookings() { return bookings; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setBedType(BedType bedType) { this.bedType = bedType; }
    public void setNumBeds(int numBeds) { this.numBeds = numBeds; }
    public void setRoomNumber(int roomNumber) { this.roomNumber = roomNumber; }
    public void setQuality(int quality) { this.quality = quality; }
    public void setTheme(Theme theme) { this.theme = theme; }
    public void setSmoking(boolean smoking) { this.smoking = smoking; }
    public void setDailyPrice(double dailyPrice) { this.dailyPrice = dailyPrice; }
    public void setBookings(List<Booking> reservations) { this.bookings = reservations; }
    // <<<< Setters <<<<

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(getRoomNumber(), room.getRoomNumber()) && Objects.equals(getQuality(), room.getQuality()) && getTheme() == room.getTheme() && Objects.equals(isSmoking(), room.isSmoking()) && getBedType() == room.getBedType() && Objects.equals(getNumBeds(), room.getNumBeds()) && Objects.equals(getDailyPrice(), room.getDailyPrice()) && Objects.equals(getBookings(), room.getBookings());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber(), getQuality(), getTheme(), isSmoking(), getBedType(), getNumBeds(), getDailyPrice(), getBookings());
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
