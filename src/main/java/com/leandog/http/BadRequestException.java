package com.leandog.http;

public class BadRequestException extends ServicesException {
    
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
        super("Bad request");
    }

}
