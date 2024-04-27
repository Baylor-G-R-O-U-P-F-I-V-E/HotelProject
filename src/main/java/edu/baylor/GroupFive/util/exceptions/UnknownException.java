package edu.baylor.GroupFive.util.exceptions;

/**
 * Not sure why this class is here if I'm being completely honest.
 *
 * Extends the {@link edu.baylor.GroupFive.util.exceptions.HotelProjectException} abstract exception class.
 *
 * @author Icko
 */
public class UnknownException extends HotelProjectException {
    /**
     * Constructs a new UnknownException object.
     */
    public UnknownException() {
        super("2");
    }
}
