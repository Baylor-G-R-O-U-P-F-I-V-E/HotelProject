package edu.baylor.GroupFive.database.controllers;

import edu.baylor.GroupFive.models.Purchase;
import edu.baylor.GroupFive.models.StayBill;

import java.util.List;

/**
 * This class represents a handler between our UI layer and our application
 * layer. Methods from this class are called from our UI layer. {@code PaymentController}
 * then determines which Service object in our application layer to continue 
 * operations with. The status of each operation is then returned to the UI layer.
 *
 * @author Chase
 * */
public class PaymentController {

    /**
     * This function returns a list of all purchases in our database.
     *
     * @return List of all purchases in our database
     * @author Chase
     * */
    public List<Purchase> getPurchases() {
        return purchases;
    }

    /**
     * This functions takes in a list of purchases and sets our database
     * to that list.
     *
     * @author Chase
     * */
    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }



    List<Purchase> purchases;
    List<StayBill> stayBills;

}
