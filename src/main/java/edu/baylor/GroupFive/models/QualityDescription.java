package edu.baylor.GroupFive.models;

public class QualityDescription {
    private String description;
    private double pricePerDay;

    public QualityDescription(
            String description_,
            double pricePerDay_
            ) {
        description = description_;
        pricePerDay = pricePerDay_;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public double getPricePerDay() { return pricePerDay; }

    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }

}
