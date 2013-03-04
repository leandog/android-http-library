package com.leandog.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

public class Response {
    private final ResponseType responseType;
    private final byte[] content;

    public Response(ResponseType responseType, byte[] content) {
        this.responseType = responseType;
        this.content = content;
    }

    public static Response build(HttpResponse httpResponse) {
        byte[] content = getContentFrom(httpResponse);
        ResponseType type = getTypeFrom(httpResponse);
        return new Response(type, content);
    }

    private static byte[] getContentFrom(HttpResponse httpResponse) {
        byte[] content = new byte[0];
        if (httpResponse != null) {
            try {
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    content = EntityUtils.toByteArray(entity);
                }
            } catch (IllegalStateException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return content;
    }

    private static ResponseType getTypeFrom(HttpResponse httpResponse) {
        ResponseType type = ResponseType.forStatusCode(httpResponse.getStatusLine().getStatusCode());
        return type;
    }

    public ResponseType getType() {
        return responseType;
    }

    public byte[] getRawContent() {
        return content;
    }

    public String getMessage() {
        return new String(content);
    }
}
