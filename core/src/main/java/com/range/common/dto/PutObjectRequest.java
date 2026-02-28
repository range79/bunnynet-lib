package com.range.common.dto;

import java.io.InputStream;
import java.util.Map;

public class PutObjectRequest {
    private final String key;
    private final String contentType;
    private final Map<String, String> metadata;


    private final InputStream inputStream;

    public PutObjectRequest(String key, String contentType, Map<String, String> metadata, InputStream inputStream) {
        if (key == null || key.isBlank()) throw new IllegalArgumentException("Key cannot be empty");
        if (contentType == null || contentType.isBlank()) throw new IllegalArgumentException("ContentType cannot be empty");
        if (inputStream == null) throw new IllegalArgumentException("InputStream cannot be null");

        this.key = key;
        this.contentType = contentType;
        this.metadata = metadata;
        this.inputStream = inputStream;
    }
    public String getKey() { return key; }
    public String getContentType() { return contentType; }
    public Map<String, String> getMetadata() { return metadata; }

    public InputStream getInputStream() {return inputStream; }
}