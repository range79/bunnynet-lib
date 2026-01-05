package com.range.common.dto;

import java.io.InputStream;
import java.util.Map;

public class PutObjectRequest {
    private final String bucket;
    private final String key;
    private final String contentType;
    private final Map<String, String> metadata;


    private final InputStream inputStream;

    public PutObjectRequest(String bucket, String key, String contentType, Map<String, String> metadata, InputStream inputStream) {
        this.bucket = bucket;
        this.key = key;
        this.contentType = contentType;
        this.metadata = metadata;
        this.inputStream = inputStream;
    }

    public String getBucket() { return bucket; }
    public String getKey() { return key; }
    public String getContentType() { return contentType; }
    public Map<String, String> getMetadata() { return metadata; }

    public InputStream getInputStream() {return inputStream; }
}