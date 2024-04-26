package edu.baylor.GroupFive.models;

/**
 * The Address class represents an address.
 *
 * @author Afraz
 * */
public class Address {
    private int buildingNumber;
    private int zipCode;
    private String streetName;
    private int aptNumber;
    private String state;
    private String country;
    private Account account;
    // TODO Why do we have this here?
    // I think this should be tied to an account, which should a list
    // of BillingInfo objects -Icko
    private BillingInfo billingInfo;

    /**
     * Constructs an Address object with the specified attributes
     *
     * @param buildingNumber Building number of this address.
     * @param zipCode Zip code of this address.
     * @param streetName Street name of this address.
     * @param aptNumber Apartment number of this address, if applicable.
     * @param state State this address is in.
     * @param country Country this address is in.
     * @param account Account tied to this address.
     * @param billingInfo Billing information.
     * @author Afraz
     * */
    public Address(
            int buildingNumber,
            int zipCode,
            String streetName,
            int aptNumber,
            String state,
            String country,
            Account account,
            BillingInfo billingInfo // TODO why are we taking in a billing info -Icko
            ) {
        this.setBuildingNumber(buildingNumber);
        this.setZipCode(zipCode);
        this.setStreetName(streetName);
        this.setAptNumber(aptNumber);
        this.setState(state);
        this.setCountry(country);
        this.setAccount(account);
        this.setBillingInfo(billingInfo);
    }

    // >>>> Getters >>>>
    public Account getAccount() { return account; }
    public int getBuildingNumber() { return buildingNumber; }
    public int getZipCode() { return zipCode; }
    public String getStreetName() { return streetName; }
    public int getAptNumber() { return aptNumber; }
    public String getState() { return state; }
    public String getCountry() { return country; }
    public BillingInfo getBillingInfo() { return billingInfo; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setAccount(Account account) { this.account = account; }
    public void setBuildingNumber(int buildingNumber) { this.buildingNumber = buildingNumber; }
    public void setZipCode(int zipCode) { this.zipCode = zipCode; }
    public void setStreetName(String streetName) { this.streetName = streetName; }
    public void setAptNumber(int aptNumber) { this.aptNumber = aptNumber; }
    public void setState(String state) { this.state = state; }
    public void setCountry(String country) { this.country = country; }
    public void setBillingInfo(BillingInfo billingInfo) { this.billingInfo = billingInfo; }
    // <<<< Setters <<<<

}
