package com.leandog.http;

import java.net.URI;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

public class HttpGetBuilder implements RequestBuilder {
    
    @Override
    public HttpUriRequest build(URI uri, RequestContent content) {
        return new HttpGet(content.uriForGET(uri));
    }

}
