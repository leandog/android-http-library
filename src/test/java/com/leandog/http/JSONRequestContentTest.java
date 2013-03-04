package com.leandog.http;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.leandog.util.HttpEntityHelpers;

public class JSONRequestContentTest {
    
    @Test
    public void setsJSONContentType() {
        HttpPost mockPost = mock(HttpPost.class);
        new JSONRequestContent("").updateRequestForPOST(mockPost);
        verify(mockPost).setHeader(eq("Content-Type"), eq("application/json"));
    }
    
    @Test
    public void setsEntityToSuppliedJSON() throws Exception {
        HttpPost mockPost = mock(HttpPost.class);
        new JSONRequestContent("Hello, World!!").updateRequestForPOST(mockPost);
        
        final ArgumentCaptor<ByteArrayEntity> entity = ArgumentCaptor.forClass(ByteArrayEntity.class);
        verify(mockPost).setEntity(entity.capture());
        assertThat(HttpEntityHelpers.contentAsString(entity.getValue()), is(equalTo("Hello, World!!")));
    }

}
