package edu.baylor.GroupFive.models.enums;

/**
 * The Theme enum represents the types of themes available.
 */
public enum Theme {
    NatureRetreat, UrbanElegance, VintageCharm;

    /**
     * Converts a string representation of a theme to the corresponding Theme enum.
     *
     * @param text The string representation of the theme.
     * @return The Theme enum value corresponding to {@code text}, or {@code null} if no match
     * @author Afraz
     */
    public static Theme fromString(String text) {
        for (Theme b : Theme.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
};
