package com.range.bunnynet.core.model;

import java.io.Closeable;
import java.io.InputStream;
import java.net.http.HttpHeaders;

/**
 * Represents the result of a download operation from Bunny.net storage.
 *
 * <p>This object provides access to the downloaded content stream
 * and HTTP metadata.</p>
 *
 * <p><strong>Resource management:</strong>
 * The underlying {@link InputStream} must be closed after use.</p>
 *
 * <pre>{@code
 * try (GetObjectResponse response = storage.download(...)) {
 *     InputStream stream = response.getStream();
 * }
 * }</pre>
 *
 * @since 2.1.0
 */
public class GetObjectResponse implements Closeable {

    private final String storageZone;
    private final String key;
    private final String publicUrl;

    private final String contentType;
    private final long contentLength;
    private final HttpHeaders headers;

    private final InputStream stream;
    private final int httpStatus;

    public GetObjectResponse(
            String storageZone,
            String key,
            String publicUrl,
            String contentType,
            long contentLength,
            HttpHeaders headers,
            InputStream stream,
            int httpStatus
    ) {
        this.storageZone = storageZone;
        this.key = key;
        this.publicUrl = publicUrl;
        this.contentType = contentType;
        this.contentLength = contentLength;
        this.headers = headers;
        this.stream = stream;
        this.httpStatus = httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
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

    public HttpHeaders getHeaders() {
        return headers;
    }

    public InputStream getStream() {
        return stream;
    }

    @Override
    public void close() {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (Exception ignored) {
        }
    }
}