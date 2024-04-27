package edu.baylor.GroupFive.models;

import edu.baylor.GroupFive.models.enums.Theme;
import edu.baylor.GroupFive.models.enums.BedType;
import edu.baylor.GroupFive.models.enums.Quality;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The Room object represents a physical room.
 *
 * @author Afraz
 * @author Icko
 * */
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

    /**
     * Constructs a basic room object.
     * */
    public Room(){
        this.roomNumber = -1;
        this.bookings = new ArrayList<>();
    }

    /**
     * Constructs a Room object with the specified attributes.
     *
     * @param roomNumber Room number of this room.
     * @param quality Quality level of this room.
     * @param theme Theme of this room.
     * @param smoking Smoking status.
     * @param numBeds Number of beds in this room.
     * @param bedType Type of beds in the room.
     * @param dailyPrice Daily prics of this room.
     * */
    public Room(int roomNumber, int quality, Theme theme, boolean smoking, int numBeds, BedType bedType, double dailyPrice) {
        this.setRoomNumber(roomNumber);
        this.setQuality(quality);
        this.setTheme(theme);
        this.setSmoking(smoking);
        this.setNumBeds(numBeds);
        this.setBedType(bedType);
        this.setDailyPrice(dailyPrice);
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

    /**
     * Compares two Room objects.
     *
     * @return {@code true} if the objects are equivalent. {@code false} otherwise.
     * */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return Objects.equals(getRoomNumber(), room.getRoomNumber()) && Objects.equals(getQuality(), room.getQuality()) && getTheme() == room.getTheme() && Objects.equals(isSmoking(), room.isSmoking()) && getBedType() == room.getBedType() && Objects.equals(getNumBeds(), room.getNumBeds()) && Objects.equals(getDailyPrice(), room.getDailyPrice()) && Objects.equals(getBookings(), room.getBookings());
    }

    /**
     * Generates a hash of the current room object.
     *
     * @return This objects hash.
     * */
    @Override
    public int hashCode() {
        return Objects.hash(getRoomNumber(), getQuality(), getTheme(), isSmoking(), getBedType(), getNumBeds(), getDailyPrice(), getBookings());
    }

    /**
     * Returns String representation of this object.
     * 
     * @return String representation of this object.
     * */
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
