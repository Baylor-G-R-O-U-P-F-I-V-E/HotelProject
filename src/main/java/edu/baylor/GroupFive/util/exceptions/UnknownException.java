package edu.baylor.GroupFive.util.exceptions;

/**
 * Not sure why this class is here if I'm being completely honest.
 *
 * Extends the {@link edu.baylor.GroupFive.util.exceptions.HotelProjectException} abstract exception class.
 *
 * @author Icko
 */
public class UnknownException extends HotelProjectException {

    private static final int UEE_CODE = 2;

    /**
     * Constructs a new UnknownException object.
     */
    public UnknownException() {
        super("UnknownException encountered", UEE_CODE);
    }

    /**
     * Constructs a new UnknownException with the specified message.
     *
     * @param message Error message
     * @param code Specific exception code
     * */
    public UnknownException(String message, int code) {
        super(message, UEE_CODE);
    }

    /**
     * Construct a new UnknownException with the given cause.
     *
     * @param cause Cause of exception
     * @param code Specific exception code
     * */
    public UnknownException(Throwable cause, int code) {
        super(cause, UEE_CODE);
    }

    /**
     * Construct a new UnknownException with a specified message
     * and given cause.
     *
     * @param message Error message
     * @param cause Cause of exception
     * @param code Specific exception code
     * */
    public UnknownException(String message, Throwable cause, int code) {
        super(message, cause, UEE_CODE);
    }

    /**
     * Construct a new UnknownException with a specified message
     * and given cause, with extra flags for designating suppression
     * and writable stack trace status.
     *
     * @param message Error message
     * @param cause Cause of exception
     * @param enableSuppression Flag for enable/disable suppression
     * @param writableStackTrace Flag for writable stack trace
     * @param code Specific exception code
     * */
    public UnknownException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace, UEE_CODE);
    }

}