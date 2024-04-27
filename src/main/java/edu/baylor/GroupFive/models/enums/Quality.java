package edu.baylor.GroupFive.models.enums;

/**
 * The Quality enum represents the types of quality levels available.
 *
 * @author Afraz
 */
public enum Quality {
    EXECUTIVE, BUSINESS, COMFORT, ECONOMY;

    /**
     * Converts a string representation of a quality level to the corresponding Quality enum.
     *
     * @param text The string representation of the quality level.
     * @return The Quality enum value corresponding to {@code text}, or {@code null} if no match
     */
    public static Quality fromString(String text) {
        for (Quality b : Quality.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
};
