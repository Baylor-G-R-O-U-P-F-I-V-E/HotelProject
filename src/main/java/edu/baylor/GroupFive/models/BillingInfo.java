package edu.baylor.GroupFive.models;

import java.util.Date;

/**
 * The BillingInfo class represents a Users billing information.
 *
 * @author Afraz
 * @author Icko
 * */
public class BillingInfo {
    private String name;
    private int creditCardNumber;
    private Date expDate;
    private int securityCode;

    /**
     * Constructs a BillingInfo object with the specified attributes.
     *
     * @param name Name of person tied to billing info.
     * @param creditCardNumber Users credit card number.
     * @param expDate Credit card's expiration date.
     * @param securityCode Credit card's security code.
     * */
    public BillingInfo(
            String name,
            int creditCardNumber,
            Date expDate,
            int securityCode) {
        this.setName(name);
        this.setCreditCardNumber(creditCardNumber);
        this.setExpDate(expDate);
        this.setSecurityCode(securityCode);
    }

    // >>>> Getters >>>>
    public String getName() { return name; }
    public int getCreditCardNumber() { return creditCardNumber; }
    public Date getExpDate() { return expDate; }
    public int getSecurityCode() { return securityCode; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setName(String name) { this.name = name; }
    public void setCreditCardNumber(int creditCardNumber) { this.creditCardNumber = creditCardNumber; }
    public void setExpDate(Date expDate) { this.expDate = expDate; }
    public void setSecurityCode(int securityCode) { this.securityCode = securityCode; }
    // <<<< Setters <<<<

}
