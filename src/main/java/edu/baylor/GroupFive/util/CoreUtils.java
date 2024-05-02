package edu.baylor.GroupFive.util;

import edu.baylor.GroupFive.util.exceptions.SkillIssueException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Basic functions and constants useful across our project
 *
 * @author Icko
 */
public class CoreUtils {

    /**
     * No need to instantiate this class.
     */
    private CoreUtils() {}

    /**
     * Dates in our project are printed in the form MM/dd/yyyy.
     * */
    public static final String DATE_FORMAT = "MM/dd/yyyy";
    /**
     * Number of milliseconds in 24 hours.
     * */
    public static final int MILLISECONDS_IN_24_HR = 24 * 60 * 60 * 1000;

     /**
      * Converts a {@code java.sql.Date} into a {@code java.util.Date}.
      *
      * @param date {@code java.sql.Date} object to convert.
      * @return Converted {@code date} object.
      * */
    public static java.util.Date getUtilDate(java.sql.Date date) {
        return new java.util.Date(date.getTime());
    }

     /**
      * Converts a {@code java.util.Date} into a {@code java.sql.Date}.
      *
      * @param date {@code java.util.Date} object to convert.
      * @return Converted {@code date} object.
      * */
    public static java.sql.Date getSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     * Takes in a Throwable object and returns the entire stack trace
     * as a single String.
     *
     * @param ex Throwable to parse stack trace from.
     * @return String representation of {@code ex}'s stack trace.
     */
    public static String stackTraceToString(Throwable ex) {
        StringBuilder builder = new StringBuilder();
        builder.append("\n" + ex.getMessage() + "\n");
        for(StackTraceElement element : ex.getStackTrace()) {
            builder.append("    " + element.toString() + "\n");
        }
        return builder.toString();
    }

     /**
      * Takes in a clear-text password and hashes it.
      *
      * @param password Password to hash.
      * @return Returns a SHA-256 hash of {@code password}.
      * */
    public static String hashPassword(String password) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            // Get an instance of MessageDigest with SHA-256 algorithm
            MessageDigest digest = MessageDigest.getInstance("SHA-256");

            // Perform the hashing
            byte[] hashedBytes = digest.digest(password.getBytes());

            // Convert the hashed bytes to a hexadecimal string
            for (byte b : hashedBytes) {
                stringBuilder.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("Check algorithm");
        }

        return stringBuilder.toString();
    }

    /**
     * Returns a String representation of a {@code java.util.Date} object.
     *
     * @param myDate Date object to convert.
     * @return String representation of {@code myDate}.
     */
    public static String formatDate(java.util.Date myDate) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        System.out.println(dateFormat.format(myDate.getTime()));
        return dateFormat.format(myDate.getTime());
    }

    /**
     * Get a {@code java.sql.Date} object from a String.
     *
     * @param dateStr String to convert.
     * @return Date object initialized from {@code dateStr}.
     * @throws ParseException If error occurs during date parsing.
     */
    public static java.sql.Date getSqlDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        java.util.Date date = sdf.parse(dateStr);
        return getSqlDate(date);
    }

    /**
     * Gets the absolute millisecond difference between two dates.
     *
     * @param date1 First date.
     * @param date2 Second date.
     * @return Returns date2 - date1. >0 if date2 is after date1. <0 if date1 is after date2.
     * */
    public static long getMillisecondDifference(java.util.Date date1, java.util.Date date2) {
        return date2.getTime() - date1.getTime();
    }

}
