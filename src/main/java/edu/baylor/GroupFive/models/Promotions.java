package edu.baylor.GroupFive.models;

import java.util.Date;
import edu.baylor.GroupFive.models.enums.DiscountType;

/**
 * The Promotions class keeps track of all discounts and promotions for
 * a specific StayBill (?)
 *
 * @author Afraz
 * @author Icko
 * */
public class Promotions {
    private double discountAmount;
    private DiscountType discountType; //true for amount, false for percentage
    private Date expirationDate;
    private StayBill bill; // FIXME Why does a promotion need to know about a bill? -Icko

    /**
     * Constructs a Promotions object with the specified attributes
     *
     * @param discountAmount Discount amount. Can be percentage or fixed amount.
     * @param discountType Discount type. i.e. is this a percentage or fixed discount
     * @param expirationDate Expiration date of the promotion
     * @param bill Why is this here
     * */
    public Promotions(double discountAmount,
                      DiscountType discountType,
                      Date expirationDate,
                      StayBill bill) {
        this.setDiscountAmount(discountAmount);
        this.setDiscountType(discountType);
        this.setExpirationDate(expirationDate);
        this.setBill(bill);
    }

    // >>>> Getters >>>>
    public double getDiscountAmount() { return discountAmount; }
    public Date getExpirationDate() { return expirationDate; }
    public StayBill getBill() { return bill; }
    public DiscountType getDiscountType() { return discountType; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }
    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }
    public void setDiscountType(DiscountType discountType) { this.discountType = discountType; }
    public void setBill(StayBill bill) {this.bill = bill;}
    // <<<< Setters <<<<

}
