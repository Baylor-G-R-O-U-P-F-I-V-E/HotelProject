package edu.baylor.GroupFive.util.exceptions;

/**
 * Indicates a connection could not be made to the database.
 *
 * Extends the {@link edu.baylor.GroupFive.util.exception.HotelProjectException} abstract exception class.
 *
 * @author Icko
 */
public class BadConnectionException extends HotelProjectException {
    /**
     * Constructs a new BadConnectionException object.
     *
     * @author Icko
     */
    public BadConnectionException() {
        super("3");
    }
}
