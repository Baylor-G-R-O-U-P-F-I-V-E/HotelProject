package edu.baylor.GroupFive.database.services;

import java.util.Date;

public class ReservationServicesExt extends ReservationServices {
    /**
     * Helper function for testing the protected {@link ReservationServices#isOverlap(Date s1, Date e1, Date s2, Date e2)}
     *
     * @param s1 Start date of reservation in "database".
     * @param e1 End date of reservation in "database".
     * @param s2 Start date of reservation to make.
     * @param e2 End date of reservation to make.
     * @return {@code true} If the new reservation overlaps. {@code false} otherwise.
     * */
    public static boolean isOverlapHelper(Date s1, Date e1, Date s2, Date e2) {
        return ReservationServices.isOverlap(s1, e1, s2, e1);
    }
}
