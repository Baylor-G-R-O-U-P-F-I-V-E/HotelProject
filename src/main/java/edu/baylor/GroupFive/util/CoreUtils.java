package edu.baylor.GroupFive.util;

import edu.baylor.GroupFive.util.exceptions.SkillIssueException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.ParseException;

/**
 * Basic functions and constants useful across our project
 */
public class CoreUtils {

    /**
     * No need to instantiate this class.
     */
    private CoreUtils() {}

    public static final String DATE_FORMAT = "MM/dd/yyyy";

     /**
      * Converts a `java.sql.Date` into a `java.util.Date`.
      *
      * @author Icko
      * @return Returns a `java.sql.Date` object
      * */
    public static java.util.Date getUtilDate(java.sql.Date date) {
        return new java.util.Date(date.getTime());
    }

     /**
      * Converts a `java.util.Date` into a `java.sql.Date`.
      *
      * @author Icko
      * @return Returns a `java.util.Date` object
      * */
    public static java.sql.Date getSqlDate(java.util.Date date) {
        return new java.sql.Date(date.getTime());
    }

    /**
     *
     * @param ex
     * @return
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
      * Takes in a clear-test password and hashes it.
      *
      * @author Icko
      * @author ChatGPT
      * @return Returns the hash of a password
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
     *
     * @param myDate
     * @return
     */
    public static String formatDate(java.util.Date myDate) {
        DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        return dateFormat.format(myDate.getTime());
    }

    /**
     *
     * @param dateStr
     * @return
     * @throws ParseException
     */
    public static java.sql.Date getSqlDate(String dateStr) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        java.util.Date date = sdf.parse(dateStr);
        return getSqlDate(date);
    }

}
