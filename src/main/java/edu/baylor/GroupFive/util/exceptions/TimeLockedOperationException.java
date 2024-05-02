package edu.baylor.GroupFive.util.exceptions;

/**
 * The {@code TimeLockedOperationException} exception is thrown when
 * a system user attempts to perform an operation that has a time-limit
 * as to when that operation can be performed.
 *
 * For example, thrown when a system user attempts to check-in a guest
 * within 24 hours of a reservation's start date.
 *
 * @author Icko
 * */
public class TimeLockedOperationException extends HotelProjectException {
    /**
     * Constructs a new {@code TimeLockedOperationException} object.
     * */
    public TimeLockedOperationException() {
        super("5");
    }
}
