package com.leandog.http;


public class FailedConnectionException extends ServicesException {
    
    private static final long serialVersionUID = 1L;

    public FailedConnectionException() {
        super("Connection failure");
    }
}
