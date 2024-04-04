package edu.baylor.GroupFive.models.enums;

public enum Theme {
    NatureRetreat, UrbanElegance, VintageCharm;
    public static Theme fromString(String text) {
        for (Theme b : Theme.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
};
