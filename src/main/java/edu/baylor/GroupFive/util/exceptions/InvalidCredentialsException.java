package edu.baylor.GroupFive.util.exceptions;

/**
 * Thrown if invalid credentials are provided when verifying a user.
 *
 * Extends the {@link edu.baylor.GroupFive.util.exceptions.HotelProjectException} abstract exception class.
 *
 * @author Icko
 */
public class InvalidCredentialsException extends HotelProjectException {
    /**
     * Constructs a new {@code InvalidCredentialsException} object.
     */
    public InvalidCredentialsException() {
        super("1");
    }
}
