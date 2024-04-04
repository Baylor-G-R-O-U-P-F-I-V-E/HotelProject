package edu.baylor.GroupFive.models;

import java.util.Date;

public class BillingInfo {
    String name;
    private int creditCardNumber;
    private Date expDate;
    private int securityCode;

    public BillingInfo(
            String name_,
            int creditCardNumber_,
            Date expDate_,
            int securityCode_
            ) {
        name = name_;
        creditCardNumber = creditCardNumber_;
        expDate = expDate_;
        securityCode = securityCode_;
    }

    public String getName() {
        return name;
    }

    public void setName(String name_) {
        name = name_;
    }

    public int getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(int creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public Date getExpDate() {
        return expDate;
    }

    public void setExpDate(Date expDate) {
        this.expDate = expDate;
    }

    public int getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(int securityCode) {
        this.securityCode = securityCode;
    }
}
