package com.leandog.http;

import java.net.URI;

public interface Requestor {
    
    Response request(RequestType requestType, URI uri, RequestContent requestContent);
}