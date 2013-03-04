package com.leandog.http;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpStatus;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public class HttpExceptionHandler {

    public static Response generateBadResponse(Exception e) {
        if (e instanceof URISyntaxException || e instanceof ClientProtocolException || e instanceof IOException) {
            ProtocolVersion version = new ProtocolVersion("", 1, 1);
            return Response.build(new BasicHttpResponse(new BasicStatusLine(version, HttpStatus.SC_BAD_REQUEST, "")));
        } else {
            throw new RuntimeException(e);
        }
    }

}
