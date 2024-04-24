package edu.baylor.GroupFive.models.enums;

public enum Quality {
    ECONOMY(1), COMFORT(2), BUSINESS(3), EXECUTIVE(4);
    public static Quality fromString(String text) {
        for (Quality b : Quality.values()) {
            if (b.toString().equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }

    private Quality(int i) {
    }

    public static String[] getQualities() {
        String[] qualities = new String[Quality.values().length];
        for (int i = 0; i < Quality.values().length; i++) {
            qualities[i] = Quality.values()[i].toString();
        }
        return qualities;
    }

    public static String formatQuality(Quality quality) {
        return quality.toString().toLowerCase();
    }

    @Override
    public String toString() {
        return super.toString().toLowerCase();
    }
};
