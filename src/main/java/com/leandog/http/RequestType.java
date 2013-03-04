package com.leandog.http;

public enum RequestType {
    GET(HttpGetBuilder.class),
    POST(HttpPostBuilder.class);
    
    private final Class<? extends RequestBuilder> requestBuilderClass;
    
    private RequestType(Class<? extends RequestBuilder> requestBuilderClass) {
        this.requestBuilderClass = requestBuilderClass;
    }
    
    public RequestBuilder createRequestBuilder() {
        try {
            return requestBuilderClass.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
}
