package com.range.common.dto;

import okhttp3.Headers;
import okhttp3.Response;

import java.io.Closeable;
import java.io.InputStream;

/**
 * Represents the result of a download operation from Bunny.net storage.
 *
 * <p>This object provides access to the downloaded content stream,
 * response metadata, and HTTP details.</p>
 *
 * <p><strong>Resource management:</strong>
 * This class holds an underlying {@link Response} and {@link InputStream}.
 * It must be closed after use to avoid resource leaks.</p>
 *
 * <pre>{@code
 * try (GetObjectResponse response = storage.download(...)) {
 *     InputStream stream = response.getStream();
 *     // read stream
 * }
 * }</pre>
 *
 * <p>Closing this object will close both the underlying HTTP response
 * and the content stream.</p>
 *
 * @since 2.1.0
 */
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

    /**
     * Creates a new download response container.
     *
     * @param storageZone  the storage zone from which the object was retrieved
     * @param key          the object key (path) inside the storage zone
     * @param publicUrl    the public or CDN URL of the object
     * @param contentType  the MIME type of the downloaded object
     * @param contentLength the size of the content in bytes
     * @param headers      HTTP response headers
     * @param response     the underlying OkHttp response
     * @param stream       the input stream containing object data
     * @param httpStatus   the HTTP status code returned by the server
     */
    public GetObjectResponse(
            String storageZone,
            String key,
            String publicUrl,
            String contentType,
            long contentLength,
            Headers headers,
            Response response,
            InputStream stream,
            int httpStatus
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

    /**
     * @return the HTTP status code of the download response
     */
    public int getHttpStatus() {
        return httpStatus;
    }

    /**
     * @return the storage zone name
     */
    public String getStorageZone() {
        return storageZone;
    }

    /**
     * @return the object key (path)
     */
    public String getKey() {
        return key;
    }

    /**
     * @return the public or CDN URL of the object
     */
    public String getPublicUrl() {
        return publicUrl;
    }

    /**
     * @return the MIME content type of the downloaded object
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * @return the content length in bytes
     */
    public long getContentLength() {
        return contentLength;
    }

    /**
     * @return HTTP response headers
     */
    public Headers getHeaders() {
        return headers;
    }

    /**
     * Returns the input stream containing the object data.
     *
     * <p>The caller should read the stream fully and close this
     * {@code GetObjectResponse} afterward.</p>
     *
     * @return the object data stream
     */
    public InputStream getStream() {
        return stream;
    }

    /**
     * Closes the underlying input stream and HTTP response.
     *
     * <p>This method is idempotent and safe to call multiple times.</p>
     */
    @Override
    public void close() {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (Exception ignored) {
        }
        if (response != null) {
            response.close();
        }
    }
}