package edu.baylor.GroupFive.util.exceptions;

/**
 * Thrown when a User cannot be found in our database.
 *
 * Extends the {@link edu.baylor.GroupFive.util.exceptions.HotelProjectException} abstract exception class.
 *
 * @author Icko
 */
public class UserNotFoundException extends HotelProjectException {
    /**
     * Constructs a new {@code UserNotFoundException} object.
     */
    public UserNotFoundException() {
        super("4");
    }
}
