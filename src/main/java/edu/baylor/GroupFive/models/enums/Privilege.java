package edu.baylor.GroupFive.models.enums;

/**
 * The Privilege enum represents the types of privilege levels available to users.
 *
 * @author Afraz
 */
public enum Privilege {
    ADMIN, CLERK, GUEST;

    /**
     * Converts a string representation of a privilege level to the corresponding Privilege enum.
     *
     * @param text The string representation of the privilege level.
     * @return The Privilege enum value corresponding to {@code text}, or {@code null} if no match
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
