package com.leandog.http;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

public class ResponseTest {
    HttpResponse httpResponse = mock(HttpResponse.class);
    StatusLine statusLine = mock(StatusLine.class);

    @Before
    public void SetUp() {
        when(statusLine.getStatusCode()).thenReturn(200);
        when(httpResponse.getStatusLine()).thenReturn(statusLine);
    }

    @Test
    public void httpResponseWithNullEntityShouldProduceEmptyMessage() {
        Response response = Response.build(httpResponse);
        assertThat(response.getMessage(), equalTo(""));
    }

    @Test
    public void containsStatusCode() throws Exception {
        Response response = Response.build(httpResponse);
        ResponseType type = response.getType();
        assertThat(type, equalTo(ResponseType.SUCCESS));
    }

    @Test
    public void containsMessage() throws Exception {
        HttpEntity httpEntity = encodeResponse("success");

        when(httpResponse.getEntity()).thenReturn(httpEntity);
        Response response = Response.build(httpResponse);
        assertThat(response.getMessage(), equalTo("success"));
    }

    @Test
    public void canConsumeAJsonResponse() throws Exception {
        HttpEntity jsonEntity = encodeResponse("{id: 1, name: 'Levi'}");
        when(httpResponse.getEntity()).thenReturn(jsonEntity);
        JSONObject json = new JSONObject(Response.build(httpResponse).getMessage());
        assertThat(json.getString("name"), equalTo("Levi"));
    }

    @Test
    public void canConsumeRawMessage() throws Exception {
        HttpEntity httpEntity = encodeResponse("success");
        when(httpResponse.getEntity()).thenReturn(httpEntity);
        Response response = Response.build(httpResponse);
        assertThat(new String(response.getRawContent()), equalTo("success"));
    }

    private HttpEntity encodeResponse(String responseString) throws IOException {
        HttpEntity httpEntity = mock(HttpEntity.class);
        InputStream inputStreamMock = new ByteArrayInputStream(responseString.getBytes());
        when(httpEntity.getContent()).thenReturn(inputStreamMock);
        when(httpEntity.getContentLength()).thenReturn((long) responseString.getBytes().length);
        return httpEntity;
    }

}