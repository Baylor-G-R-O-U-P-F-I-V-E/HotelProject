package edu.baylor.GroupFive.util.exceptions;

import java.lang.Exception;

/**
 * Base class for all exceptions in this project.
 *
 * @author Icko
 */
public abstract class HotelProjectException extends Exception {
    /**
     * Constructs a new HotelProjectException with the specified message.
     *
     * @param msg Error message.
     * @author Icko
     */
    public HotelProjectException(String msg) {
        super(msg);
    }
}
