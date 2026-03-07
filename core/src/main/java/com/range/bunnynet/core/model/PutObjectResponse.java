package com.range.bunnynet.core.model;

/**
 * Represents the result of a successful upload operation to Bunny.net storage.
 *
 * <p>This record contains information about the uploaded object,
 * including the storage zone, object key, and its accessible URL.</p>
 *
 * @param bucket the storage bucket (zone) where the object was uploaded
 * @param key    the object key (path) inside the bucket
 * @param url    the public or CDN URL of the uploaded object
 *
 * @since 2.1.0
 */
public record PutObjectResponse(
        String bucket,
        String key,
        String url
) {
}