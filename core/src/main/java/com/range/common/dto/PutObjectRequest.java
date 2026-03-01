package com.range.common.dto;

import okhttp3.MediaType;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;

public final class PutObjectRequest {

    private static final MediaType DEFAULT_CONTENT_TYPE =
            MediaType.parse("application/octet-stream");

    private final String key;
    private final MediaType contentType;
    private final Map<String, String> metadata;
    private final InputStream inputStream;

    public PutObjectRequest(
            String key,
            MediaType contentType,
            Map<String, String> metadata,
            InputStream inputStream
    ) {

        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }

        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }

        this.key = key;
        this.contentType = contentType != null
                ? contentType
                : DEFAULT_CONTENT_TYPE;

        this.metadata = metadata != null
                ? Collections.unmodifiableMap(metadata)
                : Collections.emptyMap();

        this.inputStream = inputStream;
    }

    public String getKey() {
        return key;
    }

    public MediaType getContentType() {
        return contentType;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public InputStream getInputStream() {
        return inputStream;
    }
}