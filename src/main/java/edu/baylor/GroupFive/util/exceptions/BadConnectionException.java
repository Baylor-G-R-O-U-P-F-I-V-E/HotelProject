package edu.baylor.GroupFive.util.exceptions;

/**
 * Indicates a connection could not be made to the database.
 *
 * Extends the {@link edu.baylor.GroupFive.util.exceptions.HotelProjectException} abstract exception class.
 *
 * @author Icko
 */
public class BadConnectionException extends HotelProjectException {

    private static final int BCE_CODE = 3;

    /**
     * Constructs a new BadConnectionException object.
     */
    public BadConnectionException() {
        super("BadConnectionException encountered", BCE_CODE);
    }

    /**
     * Constructs a new BadConnectionException with the specified message.
     *
     * @param message Error message
     * @param code Specific exception code
     * */
    public BadConnectionException(String message, int code) {
        super(message, BCE_CODE);
    }

    /**
     * Construct a new BadConnectionException with the given cause.
     *
     * @param cause Cause of exception
     * @param code Specific exception code
     * */
    public BadConnectionException(Throwable cause, int code) {
        super(cause, BCE_CODE);
    }

    /**
     * Construct a new BadConnectionException with a specified message
     * and given cause.
     *
     * @param message Error message
     * @param cause Cause of exception
     * @param code Specific exception code
     * */
    public BadConnectionException(String message, Throwable cause, int code) {
        super(message, cause, BCE_CODE);
    }

    /**
     * Construct a new BadConnectionException with a specified message
     * and given cause, with extra flags for designating suppression
     * and writable stack trace status.
     *
     * @param message Error message
     * @param cause Cause of exception
     * @param enableSuppression Flag for enable/disable suppression
     * @param writableStackTrace Flag for writable stack trace
     * @param code Specific exception code
     * */
    public BadConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace, BCE_CODE);
    }

}
