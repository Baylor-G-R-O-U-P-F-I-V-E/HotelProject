package edu.baylor.GroupFive.util.exceptions;

import java.lang.Exception;

/**
 * Base class for all exceptions in this project.
 *
 * @author Icko
 */
public abstract class HotelProjectException extends Exception {

    private int _code;

    /**
     * Construct a new HotelProjectException.
     *
     * @param code Specific exception code
     * */
    public HotelProjectException(int code) {
        super();
        _code = code;
    }

    /**
     * Constructs a new HotelProjectException with the specified message.
     *
     * @param message Error message
     * @param code Specific exception code
     * */
    public HotelProjectException(String message, int code) {
        super(message);
        _code = code;
    }

    /**
     * Construct a new HotelProjectException with the given cause.
     *
     * @param cause Cause of exception
     * @param code Specific exception code
     * */
    public HotelProjectException(Throwable cause, int code) {
        super(cause);
        _code = code;
    }

    /**
     * Construct a new HotelProjectException with a specified message
     * and given cause.
     *
     * @param message Error message
     * @param cause Cause of exception
     * @param code Specific exception code
     * */
    public HotelProjectException(String message, Throwable cause, int code) {
        super(message, cause);
        _code = code;
    }

    /**
     * Construct a new HotelProjectException with a specified message
     * and given cause, with extra flags for designating suppression
     * and writable stack trace status.
     *
     * @param message Error message
     * @param cause Cause of exception
     * @param enableSuppression Flag for enable/disable suppression
     * @param writableStackTrace Flag for writable stack trace
     * @param code Specific exception code
     * */
    public HotelProjectException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, int code) {
        super(message, cause, enableSuppression, writableStackTrace);
        _code = code;
    }

    @Override
    public String getMessage() {
        return "[" + _code + "] " + super.getMessage();
    }

}
