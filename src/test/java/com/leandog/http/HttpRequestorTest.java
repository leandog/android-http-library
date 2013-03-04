package com.leandog.http;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Before;
import org.junit.Test;

public class HttpRequestorTest {

    public static final String url = "http://example.com";

    private HttpResponse response = mock(HttpResponse.class);
    private HttpClient httpClient = mock(HttpClient.class);
    private StatusLine statusLine = mock(StatusLine.class);
    private Requestor requestor = new HttpRequestor(httpClient);

    @Before
    public void setUp() throws Exception {
        when(httpClient.execute(any(HttpUriRequest.class))).thenReturn(response);
        when(response.getStatusLine()).thenReturn(statusLine);
        when(statusLine.getStatusCode()).thenReturn(200);
    }

    @Test
    public void postExecutesHttpClient() throws Exception {
        requestor.request(RequestType.POST, new URI(url), new NameValueRequestContent(new ArrayList<NameValuePair>()));
        verify(httpClient, times(1)).execute(any(HttpPost.class));
    }

    @Test
    public void postReturnsAResponseObject() throws Exception {
        final Response response = requestor.request(RequestType.POST, new URI(url), new NameValueRequestContent(new ArrayList<NameValuePair>()));
        assertNotNull(response);
    }

    @Test
    public void theResponseReturnsASuccessfulResponseTypeGiven200() throws Exception {
        final Response response = requestor.request(RequestType.POST, new URI(url), new NameValueRequestContent(new ArrayList<NameValuePair>()));
        assertEquals(ResponseType.SUCCESS, response.getType());
    }

    @Test
    public void theResponseReturnsAnUnauthorizedResponseTypeGivenA401() throws Exception {
        when(statusLine.getStatusCode()).thenReturn(401);
        final Response response = requestor.request(RequestType.POST, new URI(url), new NameValueRequestContent(new ArrayList<NameValuePair>()));
        assertEquals(ResponseType.UNAUTHORIZED, response.getType());
    }

    @Test
    public void handlesIOExceptionWithBadRequest() throws Exception {
        when(httpClient.execute(any(HttpUriRequest.class))).thenThrow(new IOException());
        Response response = requestor.request(RequestType.POST, new URI("http://example.com"), new NameValueRequestContent(new ArrayList<NameValuePair>()));
        assertThat(response.getType(), equalTo(ResponseType.CONNECTION_FAILURE));
    }

    @Test
    public void handlesProtocolExceptionWithBadRequest() throws Exception {
        when(httpClient.execute(any(HttpUriRequest.class))).thenThrow(new ClientProtocolException());
        Response response = requestor.request(RequestType.POST, new URI("http://example.com"), new NameValueRequestContent(new ArrayList<NameValuePair>()));
        assertThat(response.getType(), equalTo(ResponseType.CONNECTION_FAILURE));
    }
}