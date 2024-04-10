package edu.baylor.GroupFive.models;

public class QualityDescription {
    private String description;
    private double pricePerDay;

    public QualityDescription(String description_, double pricePerDay_) {
        description = description_;
        pricePerDay = pricePerDay_;
    }

    // >>>> Getters >>>>
    public String getDescription() { return description; }
    public double getPricePerDay() { return pricePerDay; }
    // <<<< Getters <<<<

    // >>>> Setters >>>>
    public void setDescription(String description) { this.description = description; }
    public void setPricePerDay(double pricePerDay) { this.pricePerDay = pricePerDay; }
    // <<<< Setters <<<<

}
