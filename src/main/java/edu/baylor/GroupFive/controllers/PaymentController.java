package edu.baylor.GroupFive.controllers;

import edu.baylor.GroupFive.models.Purchase;
import edu.baylor.GroupFive.models.StayBill;

import java.util.List;

public class PaymentController {
    public List<Purchase> getPurchases() {
        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }



    List<Purchase> purchases;
    List<StayBill> stayBills;

}
