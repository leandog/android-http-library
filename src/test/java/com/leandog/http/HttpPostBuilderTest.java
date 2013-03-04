package com.leandog.http;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.Test;

import com.leandog.http.HttpPostBuilder;
import com.leandog.http.NameValueRequestContent;
import com.leandog.http.RequestContent;

public class HttpPostBuilderTest {

    HttpPostBuilder builder = new HttpPostBuilder();
    List<NameValuePair> parameters = new ArrayList<NameValuePair>();
    URI uri;
    String json;

    @Before
    public void setUp() throws Exception {
        uri = new URI("http://example.com");
        json = "{\"foo\":\"bar\"}";
    }
    
    @Test
    public void createsAnHttpPostObject() throws Exception {
        final HttpUriRequest result = builder.build(uri, new NameValueRequestContent(parameters));
        assertThat(result, is(instanceOf(HttpPost.class)));
    }
    
    @Test
    public void buildSetsPostForURI() throws Exception {
        HttpPost post = (HttpPost)builder.build(new URI("http://example.com"), new NameValueRequestContent(parameters));
        assertThat(post.getURI(), equalTo(new URI("http://example.com")));
    }
    
    @Test
    public void updatesThePostUsingTheRequestContent() throws Exception {
        final RequestContent mockContent = mock(RequestContent.class);
        builder.build(uri, mockContent);
        verify(mockContent).updateRequestForPOST(any(HttpPost.class));
    }

}
