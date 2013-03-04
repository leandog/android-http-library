package com.leandog.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpEntity;

public class HttpEntityHelpers {

    public static String contentAsString(HttpEntity urlEncodedFormEntity) throws IOException {
        final ByteArrayOutputStream output = new ByteArrayOutputStream();
        urlEncodedFormEntity.writeTo(output);
        return new String(output.toByteArray());
    }

}
