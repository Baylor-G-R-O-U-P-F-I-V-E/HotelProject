package edu.baylor.GroupFive.models.enums;

/**
 * The BedType enum represents the types of beds available.
 *
 * @author Afraz
 */
public enum BedType {
    SINGLE, DOUBLE, QUEEN, KING;

    /**
     * Converts a string representation of a bed type to the corresponding BedType enum.
     *
     * @param text The string representation of the bed type
     * @return The BedType enumvalue corresponding to the input string. {@code null} if no match.
     */
    public static BedType fromString(String text) {
        for (BedType b : BedType.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

    public static String[] getBedTypes() {
        String[] bedTypes = new String[BedType.values().length];
        for (int i = 0; i < BedType.values().length; i++) {
            bedTypes[i] = BedType.values()[i].toString();
        }
        return bedTypes;
    }

    public static String formatBedType(BedType bedType) {
        return bedType.toString().toLowerCase();
    }
};
