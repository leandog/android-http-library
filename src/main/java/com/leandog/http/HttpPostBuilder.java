package com.leandog.http;

import java.net.URI;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

public class HttpPostBuilder implements RequestBuilder {

    @Override
    public HttpUriRequest build(URI uri, RequestContent requestContent) {
        HttpPost post = new HttpPost(uri);
        requestContent.updateRequestForPOST(post);
        return post;
    }

}