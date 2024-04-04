package edu.baylor.GroupFive.models.enums;

public enum Quality {
    EXECUTIVE, BUSINESS, COMFORT, ECONOMY;
    public static Quality fromString(String text) {
        for (Quality b : Quality.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
};
