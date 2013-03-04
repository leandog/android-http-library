package com.leandog.http;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

public class HttpClientFactory {
	private static final int FIVE_SECONDS_IN_MILLISECONDS = 5000;
	
	public static HttpClient create() throws Exception {
		return new DefaultHttpClient(createHttpParams());
	}

	private static HttpParams createHttpParams() {
		HttpParams params = new BasicHttpParams();
		HttpConnectionParams.setConnectionTimeout(params,
				FIVE_SECONDS_IN_MILLISECONDS);
		HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
		HttpProtocolParams.setContentCharset(params,
				HTTP.DEFAULT_CONTENT_CHARSET);
		HttpProtocolParams.setUseExpectContinue(params, false);
		return params;
	}
}