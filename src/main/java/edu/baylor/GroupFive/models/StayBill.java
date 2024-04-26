package edu.baylor.GroupFive.models;

import java.util.ArrayList;
import java.util.List;

/**
 * The StayBill class represents the bill for a reservation.
 *
 * @author Afraz
 * */
public class StayBill {
    private List<Payment> payments;
    private List<Purchase> purchases;
    private Room room;
    private Booking booking;

    /**
     * workaround for now because I am instantiating a new StayBill in models/Booking.java
     * but it is currently 3:14 am and I am tired
     *
     * @author Icko
     * */
    public StayBill() {
        payments = null;
        purchases = null;
        room = null;
        booking = null;
    }

    /**
     * Constructs a StayBill object with the specified attributes.
     *
     * @param payments A List of payments associated with this bill.
     * @param purchases A List of purchases associated with this bill.
     * @param room The room associated with this booking.
     * @param booking The booking tied to this bill.
     * @author Afraz
     * */
    public StayBill(List<Payment> payments,
                    List<Purchase> purchases,
                    Room room,
                    Booking booking) {
        this.setPayments(payments);
        this.setPurchases(purchases);
        this.setRoom(room);
        this.setBooking(booking);
    }

    /**
     * Adds a payment to our bill.
     *
     * @param pay Payment to add.
     * @author Afraz
     * */
    public void addPayment(Payment pay) {
        payments.add(pay);
    }

    /**
     * Adds a purchase to our bill.
     * 
     * @param purchase Purchase to add.
     * @author Afraz
     * */
    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    /**
     * Remove a payment from our bill.
     *
     * @param pay Payment to remove.
     * @author Afraz
     * */
    public void removePayment(Payment pay) {
        if(!payments.isEmpty()) {
            payments.remove(pay);
        }
    }

    /**
     * Remove a purchase from our bill.
     *
     * @param purchase Purchase to remove.
     * @author Afraz
     * */
    public void removePurchase(Purchase purchase) {
        if(!purchases.isEmpty()) {
            purchases.remove(purchase);
        }
    }

    public Purchase getPurchase(int purchase) {
        return purchases.get(purchase);
    }

    /**
     * Just return 0 apparently.
     *
     * @return 0
     * @author Afraz
     * */
    public double getPaymentsBill() {
        if(!payments.isEmpty()) {
            double bill = 0;
            for(Payment payment: payments) {
                bill += payment.getAmount();
            }
            return bill;
        }
        return 0;
    }

    /**
     * Just return 0 apparently.
     *
     * @return 0
     * @author Afraz
     * */
    public double getPurchaseBill() {
        if(!purchases.isEmpty()) {
            double bill = 0;
            for(Purchase purchase: purchases) {
                bill += purchase.getAmount();
            }
            return bill;
        }
        return 0;
    }

    /**
     * Just return 0 apparently.
     *
     * @return 0
     * @author Afraz
     * */
    public double getStaySubtotal() {
        double bill = 0;
        bill += getPaymentsBill();
        bill += getPurchaseBill();
        return bill;
    }

    public List<Payment> getPayments() { return payments; }

    public void setPayments(List<Payment> payments) { this.payments = payments; }

    public List<Purchase> getPurchases() { return purchases; }

    public void setPurchases(List<Purchase> purchases) { this.purchases = purchases; }

    public Room getRoom() { return room; }

    public void setRoom(Room room) { this.room = room; }

    public Booking getBooking() { return booking; }

    public void setBooking(Booking booking) { this.booking = booking; }
}
