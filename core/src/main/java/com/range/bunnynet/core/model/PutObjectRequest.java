package com.range.bunnynet.core.model;

import java.io.InputStream;
import java.util.Collections;
import java.util.Map;

/**
 * Represents a request to upload an object to Bunny.net storage.
 *
 * <p>This record is immutable in structure. However, note that the provided
 * {@link InputStream} itself may be mutable and is not managed by this class.</p>
 *
 * <p><strong>Default behavior:</strong></p>
 * <ul>
 *     <li>If {@code contentType} is {@code null}, it defaults to
 *     {@code application/octet-stream}.</li>
 *     <li>If {@code metadata} is {@code null}, an empty unmodifiable map is used.</li>
 * </ul>
 *
 * <p><strong>Important:</strong>
 * The caller is responsible for closing the provided {@link InputStream}
 * after the upload operation completes.</p>
 *
 * @since 2.1.0
 */
public record PutObjectRequest(
        String key,
        String contentType,
        Map<String, String> metadata,
        InputStream inputStream
) {

    private static final String DEFAULT_CONTENT_TYPE =
            "application/octet-stream";

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

    public PutObjectRequest(
            String key,
            Map<String, String> metadata,
            InputStream inputStream
    ) {
        this(key, DEFAULT_CONTENT_TYPE, metadata, inputStream);
    }

    public PutObjectRequest(
            String key,
            InputStream inputStream
    ) {
        this(key, DEFAULT_CONTENT_TYPE, null, inputStream);
    }
}