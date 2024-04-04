package edu.baylor.GroupFive.models;

import java.util.Date;

public class Promotions {
    private double discountAmount;
    private boolean discountType; //true for amount, false for percentage
    private Date expirationDate;
    private StayBill bill;

    public Promotions(
            double discountAmount_,
            boolean discountType_,
            Date expirationDate_,
            StayBill bill_
            ) {
        discountAmount = discountAmount_;
        discountType = discountType_;
        expirationDate = expirationDate_;
        bill = bill_;
    }

    public double getDiscountAmount() { return discountAmount; }

    public void setDiscountAmount(double discountAmount) { this.discountAmount = discountAmount; }

    public void setAmountDiscount() { discountType = true; }

    public void setPercentageDiscount() { discountType = false; }

    public Date getExpirationDate() { return expirationDate; }

    public void setExpirationDate(Date expirationDate) { this.expirationDate = expirationDate; }

    public StayBill getBill() { return bill; }

    public void setBill(StayBill bill) {this.bill = bill;}
}
