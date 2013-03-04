package com.leandog.http;

public class StaleUserException extends ServicesException {
    private static final long serialVersionUID = 1L;

    public StaleUserException(String message) {
        super(message);
    }
}
