package edu.baylor.GroupFive.models.enums;

/**
 *
 */
public enum DiscountType {
    NONE, AMOUNT, PERCENT;
    //Turn string into enum

    /**
     *
     * @param text
     * @return
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
