package edu.baylor.GroupFive.models;

import java.util.ArrayList;
import java.util.List;

public class StayBill {
    private List<Payment> payments;
    private List<Purchase> purchases;
    private Room room;
    private Booking booking;

    public StayBill() {
        room = null;
        booking = null;
        purchases = new ArrayList<>();
        payments = new ArrayList<>();
    }

    public void addPayment(Payment pay) {
        payments.add(pay);
    }
    public void addPurchase(Purchase purchase) {
        purchases.add(purchase);
    }

    public void removePayment(Payment pay) {
        if(!payments.isEmpty()) {
            payments.remove(pay);
        }
    }

    public void removePurchase(Purchase purchase) {
        if(!purchases.isEmpty()) {
            purchases.remove(purchase);
        }
    }

    public Payment getLatestPayment() {
        return payments.getLast();
    }

    public Payment getOldestPayment() {
        return payments.getFirst();
    }

    public Payment getPayment(int pay) {
        return payments.get(pay);
    }

    public Purchase getLatestPurchase() {
        return purchases.getLast();
    }

    public Purchase getOldestPurchase() {
        return purchases.getFirst();
    }

    public Purchase getPurchase(int purchase) {
        return purchases.get(purchase);
    }

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

    public double getStaySubtotal() {
        double bill = 0;
        bill += getPaymentsBill();
        bill += getPurchaseBill();
        return bill;
    }

    public Room getRoom() { return room; }

    public void setRoom(Room room) { this.room = room; }

    public Booking getBooking() { return booking; }

    public void setBooking(Booking booking) { this.booking = booking; }
}
