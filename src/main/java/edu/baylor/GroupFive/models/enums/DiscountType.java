package edu.baylor.GroupFive.models.enums;

/**
 * The DiscountType enum represents the available discount types.
 *
 * @author Afraz
 */
public enum DiscountType {
    NONE, AMOUNT, PERCENT;
    //Turn string into enum

    /**
     * Converts a string representation of a discount type to the corresponding DiscountType enum.
     *
     * @param text The string representation of the discount type.
     * @return The DiscountType enum value corresponding to {@code test}, or {@code null} if no match
     */
    public static Privilege fromString(String text) {
        for (Privilege b : Privilege.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
