package edu.baylor.GroupFive.models;

public enum Privilege {
    ADMIN, CLERK, GUEST;
    //Turn string into enum
    public static Privilege fromString(String text) {
        for (Privilege b : Privilege.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}