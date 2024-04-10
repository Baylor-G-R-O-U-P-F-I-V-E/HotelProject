package edu.baylor.GroupFive.util;

public class CoreUtils {
    
    private CoreUtils() {}

    public static final String DATE_FORMAT = "dd/MM/yyyy";

    public static java.util.Date getUtilDate(java.sql.Date date) {
        return new java.util.Date(date.getTime());
    }

    public static java.sql.Date getSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static String stackTraceToString(Throwable ex) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n" + ex.getMessage() + "\n");
        for(StackTraceElement element : ex.getStackTrace()) {
            builder.append("    " + element.toString() + "\n");
        }
        return builder.toString();
    }

}
