package com.leandog.http;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;

public class HttpRequestor implements Requestor {

    final private HttpClient httpClient;

    public HttpRequestor(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    @Override
    public Response request(RequestType type, URI uri, RequestContent requestContent) {
        return executeRequest(type.createRequestBuilder().build(uri, requestContent));
    }

    
    private Response executeRequest(HttpUriRequest post) {
        try {
            HttpResponse httpResponse = httpClient.execute(post);
            return Response.build(httpResponse);
        } catch (Exception e) {
            return HttpExceptionHandler.generateBadResponse(e);
        }
    }
}
