package edu.baylor.GroupFive.models.enums;

/**
 *
 */
public enum BedType {
    SINGLE, DOUBLE, QUEEN, KING;

    /**
     *
     * @param text
     * @return
     */
    public static BedType fromString(String text) {
        for (BedType b : BedType.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
};
