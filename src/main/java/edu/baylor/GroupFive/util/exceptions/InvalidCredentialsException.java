package edu.baylor.GroupFive.util.exceptions;

/**
 * Thrown if invalid credentials are provided when verifying a user.
 *
 * Extends the {@link edu.baylor.GroupFive.util.exceptions.HotelProjectException} abstract exception class.
 *
 * @author Icko
 */
public class InvalidCredentialsException extends HotelProjectException {

    private static final int ICE_CODE = 1;

    /**
     * Constructs a new InvalidCredentialsException object.
     */
    public InvalidCredentialsException() {
        super("InvalidCredentialsException encountered", ICE_CODE);
    }

    /**
     * Constructs a new InvalidCredentialsException with the specified message.
     *
     * @param message Error message
     * @param code Specific exception code
     * */
    public InvalidCredentialsException(String message, int code) {
        super(message, ICE_CODE);
    }

    /**
     * Construct a new InvalidCredentialsException with the given cause.
     *
     * @param cause Cause of exception
     * @param code Specific exception code
     * */
    public InvalidCredentialsException(Throwable cause, int code) {
        super(cause, ICE_CODE);
    }

    /**
     * Construct a new InvalidCredentialsException with a specified message
     * and given cause.
     *
     * @param message Error message
     * @param cause Cause of exception
     * @param code Specific exception code
     * */
    public InvalidCredentialsException(String message, Throwable cause, int code) {
        super(message, cause, ICE_CODE);
    }

    /**
     * Construct a new InvalidCredentialsException with a specified message
     * and given cause, with extra flags for designating suppression
     * and writable stack trace status.
     *
     * @param message Error message
     * @param cause Cause of exception
     * @param enableSuppression Flag for enable/disable suppression
     * @param writableStackTrace Flag for writable stack trace
     * @param code Specific exception code
     * */
    public InvalidCredentialsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace, ICE_CODE);
    }

}
