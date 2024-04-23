package edu.baylor.GroupFive.models.enums;

public enum BedType {
    SINGLE, DOUBLE, QUEEN, KING;
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
