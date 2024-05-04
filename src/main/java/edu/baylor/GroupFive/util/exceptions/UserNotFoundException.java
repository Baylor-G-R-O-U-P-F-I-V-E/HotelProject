package edu.baylor.GroupFive.util.exceptions;

/**
 * Thrown when a User cannot be found in our database.
 *
 * Extends the {@link edu.baylor.GroupFive.util.exceptions.HotelProjectException} abstract exception class.
 *
 * @author Icko
 */
public class UserNotFoundException extends HotelProjectException {

    private static final int UNFE_CODE = 4;

    /**
     * Constructs a new UserNotFoundException object.
     */
    public UserNotFoundException() {
        super("UserNotFoundException encountered", UNFE_CODE);
    }

    /**
     * Constructs a new UserNotFoundException with the specified message.
     *
     * @param message Error message
     * @param code Specific exception code
     * */
    public UserNotFoundException(String message, int code) {
        super(message, UNFE_CODE);
    }

    /**
     * Construct a new UserNotFoundException with the given cause.
     *
     * @param cause Cause of exception
     * @param code Specific exception code
     * */
    public UserNotFoundException(Throwable cause, int code) {
        super(cause, UNFE_CODE);
    }

    /**
     * Construct a new UserNotFoundException with a specified message
     * and given cause.
     *
     * @param message Error message
     * @param cause Cause of exception
     * @param code Specific exception code
     * */
    public UserNotFoundException(String message, Throwable cause, int code) {
        super(message, cause, UNFE_CODE);
    }

    /**
     * Construct a new UserNotFoundException with a specified message
     * and given cause, with extra flags for designating suppression
     * and writable stack trace status.
     *
     * @param message Error message
     * @param cause Cause of exception
     * @param enableSuppression Flag for enable/disable suppression
     * @param writableStackTrace Flag for writable stack trace
     * @param code Specific exception code
     * */
    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace, UNFE_CODE);
    }

}