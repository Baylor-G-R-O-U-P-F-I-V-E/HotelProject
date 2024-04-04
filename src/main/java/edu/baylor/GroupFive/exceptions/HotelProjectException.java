package edu.baylor.GroupFive.exceptions;

import java.lang.Exception;

// Base class for all exceptions in this project
public class HotelProjectException extends Exception {
    public HotelProjectException(String msg) {
        super(msg);
    }
}
