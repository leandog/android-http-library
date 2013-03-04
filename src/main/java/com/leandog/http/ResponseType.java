package com.leandog.http;

import org.apache.http.HttpStatus;

public enum ResponseType {
    SUCCESS, UNAUTHORIZED, CONNECTION_FAILURE, BAD_REQUEST;

    public static ResponseType forStatusCode(int statusCode) {
        if (statusCode == HttpStatus.SC_OK) {
            return SUCCESS;
        } else if (statusCode == HttpStatus.SC_UNAUTHORIZED) {
            return UNAUTHORIZED;
        } else if (statusCode == HttpStatus.SC_BAD_REQUEST) {
            return CONNECTION_FAILURE;
        } else {
            return BAD_REQUEST;
        }
    }
}
