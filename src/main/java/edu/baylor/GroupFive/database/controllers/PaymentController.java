package edu.baylor.GroupFive.database.controllers;

import edu.baylor.GroupFive.models.Purchase;
import edu.baylor.GroupFive.models.StayBill;

import java.util.List;

 /**
  * Was supposed to handle Payments
  *
  * @deprecated use {@link #TBD()} instead
  * */
@Deprecated
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
