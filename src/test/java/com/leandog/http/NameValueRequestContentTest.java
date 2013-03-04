package com.leandog.http;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.leandog.util.HttpEntityHelpers;

public class NameValueRequestContentTest {
    
    @Test
    public void updateForPOSTSetsContentUsingURLEncoding() throws Exception {
        final ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("foo", "bar"));
        final HttpPost mockPost = mock(HttpPost.class);
        
        new NameValueRequestContent(parameters).updateRequestForPOST(mockPost);
        
        final ArgumentCaptor<UrlEncodedFormEntity> urlEncodedFormEntity = ArgumentCaptor.forClass(UrlEncodedFormEntity.class);
        verify(mockPost).setEntity(urlEncodedFormEntity.capture());
        assertThat(HttpEntityHelpers.contentAsString(urlEncodedFormEntity.getValue()), is(equalTo("foo=bar")));
    }

}
