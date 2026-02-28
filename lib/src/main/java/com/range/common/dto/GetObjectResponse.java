package com.range.common.dto;

import okhttp3.Headers;
import okhttp3.Response;

import java.io.Closeable;
import java.io.InputStream;


public class GetObjectResponse implements Closeable {

    private final String storageZone;
    private final String key;
    private final String publicUrl;

    private final String contentType;
    private final long contentLength;
    private final Headers headers;

    private final Response response;
    private final InputStream stream;
    private final int httpStatus;

    public int getHttpStatus() {
        return httpStatus;
    }

    public GetObjectResponse(
            String storageZone,
            String key,
            String publicUrl,
            String contentType,
            long contentLength,
            Headers headers,
            Response response,
            InputStream stream, int httpStatus
    ) {
        this.storageZone = storageZone;
        this.key = key;
        this.publicUrl = publicUrl;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.headers = headers;
        this.response = response;
        this.stream = stream;
        this.httpStatus = httpStatus;
    }

    public String getStorageZone() {
        return storageZone;
    }

    public String getKey() {
        return key;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public String getContentType() {
        return contentType;
    }

    public long getContentLength() {
        return contentLength;
    }

    public Headers getHeaders() {
        return headers;
    }

    public InputStream getStream() {
        return stream;
    }

    @Override
    public void close() {
        try {
            if (stream != null) stream.close();
        } catch (Exception ignored) {
        }
        if (response != null) {
            response.close();
        }
    }
}