package edu.baylor.GroupFive.models;

public class QualityDescription {
    private String description;

    private double pricePerDay;

    public QualityDescription() {
        description = "";
        pricePerDay = -1;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public double getPricePerDay() { return pricePerDay; }

    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }

    private float pricePerDay;

}
