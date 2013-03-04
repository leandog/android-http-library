package com.leandog.http;

import java.net.URI;

import org.apache.http.client.methods.HttpUriRequest;

public interface RequestBuilder {

    public abstract HttpUriRequest build(URI uri, RequestContent requestContent);

}