package edu.baylor.GroupFive.models;

/**
 * The QualityDescription class maps each quality with a price per day.
 *
 * @author Afraz
 * @author Icko
 * */
public class QualityDescription {
    private String description; // TODO What is this for. Should this be the enum? -Icko
    private double pricePerDay;

    /**
     * Constructs a QualityDescription object with the specified attributes.
     *
     * @param description Description of the quality level.
     * @param pricePerDay Price per day for this quality level.
     * */
    public QualityDescription(String description, double pricePerDay) {
        this.setDescription(description);
        this.setPricePerDay(pricePerDay);
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
