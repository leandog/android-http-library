package com.leandog.http;

public class UnauthorizedUserException extends ServicesException {
    private static final long serialVersionUID = 1L;

    public UnauthorizedUserException() {
        super("Unauthorized");
    }

}
