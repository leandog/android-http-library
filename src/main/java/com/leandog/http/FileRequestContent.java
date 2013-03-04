package com.leandog.http;

import java.io.File;
import java.net.URI;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;

public class FileRequestContent implements RequestContent {

    private final File file;
    private final String fileName;

    public FileRequestContent(final File file, final String fileName) {
        this.file = file;
        this.fileName = fileName;
    }

    @Override
    public URI uriForGET(URI uri) {
        throw new RuntimeException("Not supported");
    }

    @Override
    public void updateRequestForPOST(HttpPost post) {
        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
        configureEntity(multipartEntity);
        post.setEntity(multipartEntity);
    }

    void configureEntity(MultipartEntity multipartEntity) {
        multipartEntity.addPart(getPartName(), new FileBody(file, fileName, getContentType(), null));
    }

    private boolean isWave() {
        return fileHasExtension("wav");
    }

    private boolean isPhoto() {
        return fileHasExtension("jpg");
    }

    private boolean fileHasExtension(String extension) {
        return fileName.toLowerCase().endsWith(extension);
    }

    private String getPartName() {
        if (isPhoto()) {
            return "photo";
        }

        if (isWave()) {
            return "voiceMemo";
        }
        
        throw new IllegalArgumentException("unknown file type for " + fileName);
    }
    
    private String getContentType() {
        if (isPhoto())
            return "image/jpeg";
        else
            return "application/octet-stream";
    }

}
