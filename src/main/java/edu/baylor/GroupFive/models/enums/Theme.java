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

    public static String[] getThemes() {
        String[] themes = new String[Theme.values().length];
        for (int i = 0; i < Theme.values().length; i++) {
            themes[i] = Theme.values()[i].toString();
        }
        return themes;
    }

    public static String formatTheme(Theme theme) {
        return theme.toString().replace(" ", "_");
    }
}
