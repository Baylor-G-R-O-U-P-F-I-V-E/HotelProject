package edu.baylor.GroupFive.database.services;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.Date;
import org.junit.jupiter.api.Test;

/**
 * Tests the isOverlap function of {@link edu.baylor.GroupFive.database.services.ReservationServices}.
 *
 * @author Icko
 * */
public class ReservationServicesIsOverlapTest {

    /**
     *  Tests isOverlap with start2 and end2 both before start1.
     *              s1  e1
     *  s2  e2
     * */
    @Test
    public void testIsOverlapWithNoOverlap1() {
        Date s1 = new Date("05/01/2024");
        Date e1 = new Date("05/06/2024");
        Date s2 = new Date("04/24/2024");
        Date e2 = new Date("04/30/2024");
        assertFalse(ReservationServicesExt.isOverlap(s1, e1, s2, e2));
    }

    /**
     *  Tests isOverlap with start2 and end2 both after end2.
     *  s1   e1
     *              s2  e2
     * */
    @Test
    public void testIsOverlapWithNoOverlap2() {
        Date s1 = new Date("05/01/2024");
        Date e1 = new Date("05/06/2024");
        Date s2 = new Date("05/24/2024");
        Date e2 = new Date("05/30/2024");
        assertFalse(ReservationServicesExt.isOverlap(s1, e1, s2, e2));
    }

    /**
     *  Tests isOverlap with start2 before start1 and end2 after start1 but before end1.
     *          s1      e1
     *      s2      e2
     * */
    @Test
    public void testIsOverlapWithOverlap1() {
        Date s1 = new Date("05/01/2024");
        Date e1 = new Date("05/06/2024");
        Date s2 = new Date("04/24/2024");
        Date e2 = new Date("05/03/2024");
        assertTrue(ReservationServicesExt.isOverlap(s1, e1, s2, e2));
    }

    /**
     *  Tests isOverlap with start2 after start1 but before end1 and end2 after end1.
     *          s1      e1
     *              s2      e2
     * */
    @Test
    public void testIsOverlapWithOverlap2() {
        Date s1 = new Date("05/01/2024");
        Date e1 = new Date("05/06/2024");
        Date s2 = new Date("05/03/2024");
        Date e2 = new Date("05/09/2024");
        assertTrue(ReservationServicesExt.isOverlap(s1, e1, s2, e2));
    }

    /**
     *  Tests isOverlap with start2 and end2 between start1 and end1.
     *          s1          e1
     *              s2  e2
     * */
    @Test
    public void testIsOverlapWithOverlap3() {
        Date s1 = new Date("05/01/2024");
        Date e1 = new Date("05/06/2024");
        Date s2 = new Date("05/02/2024");
        Date e2 = new Date("05/05/2024");
        assertTrue(ReservationServicesExt.isOverlap(s1, e1, s2, e2));
    }

    /**
     * Tests isOverlap with start2 before start1 and end2 after end1.
     *              s1  e1
     *          s2          e2
     * */
    @Test
    public void testIsOverlapWithOverlap4() {
        Date s1 = new Date("05/01/2024");
        Date e1 = new Date("05/06/2024");
        Date s2 = new Date("04/24/2024");
        Date e2 = new Date("05/10/2024");
        assertTrue(ReservationServicesExt.isOverlap(s1, e1, s2, e2));
    }
}
