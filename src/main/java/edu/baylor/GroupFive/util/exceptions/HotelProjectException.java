package edu.baylor.GroupFive.util.exceptions;

import java.lang.Exception;

/**
 * Base class for all exceptions in this project.
 *
 * @author Icko
 */
public abstract class HotelProjectException extends Exception {
    /**
     * Constructs a new {@code HotelProjectException} with the specified message.
     *
     * @param msg Error message.
     */
    public HotelProjectException(String msg) {
        super(msg);
    }
}
