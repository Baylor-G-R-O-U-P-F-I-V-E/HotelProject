package edu.baylor.GroupFive.models;

import java.util.Date;
import edu.baylor.GroupFive.models.enums.DiscountType;

public class Promotions {
    private double discountAmount;
    private DiscountType discountType; //true for amount, false for percentage
    private Date expirationDate;
    private StayBill bill;

    public Promotions(double discountAmount_,
                      DiscountType discountType_,
                      Date expirationDate_,
                      StayBill bill_) {
        discountAmount = discountAmount_;
        discountType = discountType_;
        expirationDate = expirationDate_;
        bill = bill_;
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
