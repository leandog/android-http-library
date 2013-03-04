package com.leandog.http;

import java.net.URI;

import org.apache.http.client.methods.HttpPost;

public interface RequestContent {
    
    public URI uriForGET(URI uri);
    public void updateRequestForPOST(HttpPost post);

}
