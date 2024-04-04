package edu.baylor.GroupFive.exceptions;

// Thrown when a connection cannot be made to the database
public class BadConnectionException extends HotelProjectException {
    public BadConnectionException() {
        super("3");
    }
}
