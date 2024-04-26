package edu.baylor.GroupFive.util.exceptions;

/**
 * Thrown when a User cannot be found in our database.
 *
 * Extends the {@link edu.baylor.GroupFive.util.exception.HotelProjectException} abstract exception class.
 *
 * @author Icko
 */
public class UserNotFoundException extends HotelProjectException {
    /**
     * Constructs a new UserNotFoundException object.
     *
     * @author Icko
     */
    public UserNotFoundException() {
        super("4");
    }
}
