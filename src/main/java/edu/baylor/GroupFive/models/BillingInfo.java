package edu.baylor.GroupFive.models;

import java.util.Date;

public class BillingInfo {
    private int creditCardInfo;
    private Date expDate;
    private int securityCode;

    public BillingInfo() {
        creditCardInfo = -1;
        expDate = null;
        securityCode = -1;
    }

    public int getCreditCardInfo() {
        return creditCardInfo;
    }

    public void setCreditCardInfo(int creditCardInfo) {
        this.creditCardInfo = creditCardInfo;
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
