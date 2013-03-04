package com.leandog.http;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

public class NameValueRequestContent implements RequestContent {
    
    private final List<NameValuePair> pairList;
    
    public NameValueRequestContent() {
        pairList = new ArrayList<NameValuePair>();
    }
    
    public NameValueRequestContent(final List<NameValuePair> pairList) {
        this.pairList = pairList;
    }
    
    public List<NameValuePair> getPairList() {
        return pairList;
    }
    
    public void add(final String name, final String value) {
        pairList.add(new BasicNameValuePair(name, value));
    }

    @Override
    public URI uriForGET(URI uri) {
        try {
            return new URI(String.format("%s%s", uri.toURL(), queryFor(pairList)));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private String queryFor(List<NameValuePair> pairList) {
        if (pairList.isEmpty())
            return "";
        
        return "?" + URLEncodedUtils.format(pairList, "UTF-8");
    }

    @Override
    public void updateRequestForPOST(HttpPost post) {
        try {
            post.setEntity(new UrlEncodedFormEntity(pairList));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}
