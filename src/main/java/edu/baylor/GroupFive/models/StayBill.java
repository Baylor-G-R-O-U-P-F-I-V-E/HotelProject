package edu.baylor.GroupFive.models;

import java.util.List;

public class StayBill {
    private List<Payment> payments;
    private List<Purchase> purchases;
    private Room room;
    private Booking booking;

    public List<Payment> getPayments() { return payments; }

    public void setPayments(List<Payment> payments) { this.payments = payments; }

    public List<Purchase> getPurchases() { return purchases; }

    public void setPurchases(List<Purchase> purchases) { this.purchases = purchases; }

    public Room getRoom() { return room; }

    public void setRoom(Room room) { this.room = room; }

    public Booking getBooking() { return booking; }

    public void setBooking(Booking booking) { this.booking = booking; }
}
