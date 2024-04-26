package edu.baylor.GroupFive.util.exceptions;

import java.lang.Exception;

/**
 * Base class for all exceptions in this project
 */
public class HotelProjectException extends Exception {
    /**
     *
     * @param msg
     */
    public HotelProjectException(String msg) {
        super(msg);
    }
}
