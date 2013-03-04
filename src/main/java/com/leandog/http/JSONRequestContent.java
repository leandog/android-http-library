package com.leandog.http;

import java.net.URI;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;

public class JSONRequestContent implements RequestContent {
    
    private final String json;
    
    public JSONRequestContent(final String json) {
        this.json = json;
    }

    @Override
    public URI uriForGET(URI uri) {
        throw new RuntimeException("Not supported");
    }

    @Override
    public void updateRequestForPOST(HttpPost post) {
        post.setHeader("Content-Type", "application/json");
        post.setEntity(new ByteArrayEntity(json.getBytes()));
    }

    public String getJSON() {
        return json;
    }

}
