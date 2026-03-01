package com.range.common.dto;

import okhttp3.MediaType;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

/**
 * Represents a request to upload an object to Bunny.net storage.
 *
 * <p>This record is immutable in structure. However, note that the provided
 * {@link InputStream} itself may be mutable and is not managed by this class.</p>
 *
 * <h3>Default Behavior</h3>
 * <ul>
 *     <li>If {@code contentType} is {@code null}, it defaults to
 *     {@code application/octet-stream}.</li>
 *     <li>If {@code metadata} is {@code null}, an empty unmodifiable map is used.</li>
 * </ul>
 *
 * <h3>Important</h3>
 * <p>The caller is responsible for closing the provided {@link InputStream}
 * after the upload operation completes.</p>
 *
 * <p>Basic usage example:</p>
 * <pre>{@code
 * PutObjectRequest request =
 *     new PutObjectRequest("images/photo.png", inputStream);
 * }</pre>
 *
 * @param key         the object key (path) inside the storage zone;
 *                    must not be null or blank
 * @param contentType the MIME type of the object;
 *                    may be null (defaults to application/octet-stream)
 * @param metadata    optional metadata key-value pairs;
 *                    may be null (defaults to empty map)
 * @param inputStream the input stream containing object data;
 *                    must not be null
 *
 * @since 2.1.0
 */
public record PutObjectRequest(
        String key,
        MediaType contentType,
        Map<String, String> metadata,
        InputStream inputStream
) {

    /**
     * Default content type used when none is explicitly provided.
     */
    private static final MediaType DEFAULT_CONTENT_TYPE =
            MediaType.parse("application/octet-stream");

    /**
     * Canonical constructor with validation and default handling.
     *
     * @throws IllegalArgumentException if {@code key} is null/blank
     *                                  or {@code inputStream} is null
     */
    public PutObjectRequest {
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }

        if (inputStream == null) {
            throw new IllegalArgumentException("InputStream cannot be null");
        }

        contentType = contentType != null
                ? contentType
                : DEFAULT_CONTENT_TYPE;

        metadata = metadata != null
                ? Collections.unmodifiableMap(metadata)
                : Collections.emptyMap();
    }

    /**
     * Creates a request with default content type
     * ({@code application/octet-stream}).
     *
     * @param key         the object key (must not be null or blank)
     * @param metadata    optional metadata (nullable)
     * @param inputStream object data stream (must not be null)
     */
    public PutObjectRequest(String key,
                            Map<String, String> metadata,
                            InputStream inputStream) {
        this(key, DEFAULT_CONTENT_TYPE, metadata, inputStream);
    }

    /**
     * Creates a request using only required fields.
     *
     * <p>Content type defaults to {@code application/octet-stream}
     * and metadata defaults to an empty map.</p>
     *
     * @param key         the object key (must not be null or blank)
     * @param inputStream object data stream (must not be null)
     */
    public PutObjectRequest(String key,
                            InputStream inputStream) {
        this(key, DEFAULT_CONTENT_TYPE, null, inputStream);
    }
}