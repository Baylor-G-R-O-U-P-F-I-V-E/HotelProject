package edu.baylor.GroupFive.exceptions;

public class UserNotFoundException extends HotelProjectException {
    public UserNotFoundException() {
        super("404");
    }
}
