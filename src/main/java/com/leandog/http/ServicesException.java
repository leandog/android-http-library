package com.leandog.http;

public class ServicesException extends Exception {
    private static final long serialVersionUID = 1L;

    public ServicesException(String requestedUrl, Response serviceResponse, Exception exception) {
        super("\n\n" + requestedUrl + " Failed!\n" + getMessageFrom(serviceResponse), exception);
    }

    private static String getMessageFrom(Response serviceResponse) {
        return (serviceResponse != null) ? "The Response was:\n" + serviceResponse.getMessage() + "\n\n" : "The Response from the Server was null!\n\n";
    }

    public ServicesException(String message) {
        super(message);
    }

    public ServicesException(Exception e) {
        super(e);
    }

}
