package edu.baylor.GroupFive.database.services;

import java.util.Date;

public class ReservationServicesExt extends ReservationServices {
    public static boolean isOverlapHelper(Date s1, Date e1, Date s2, Date e2) {
        return ReservationServices.isOverlap(s1, e1, s2, e1);
    }
}
