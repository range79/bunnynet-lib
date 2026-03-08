package com.range.bunnynet.core.storage.single;

import com.range.bunnynet.core.model.GetObjectResponse;
import com.range.bunnynet.core.model.PutObjectRequest;
import com.range.bunnynet.core.model.PutObjectResponse;

/**
 * High-level abstraction for interacting with a single Bunny.net storage zone.
 *
 * <p>This interface provides basic object operations such as upload,
 * download, and delete.</p>
 *
 * <p>Instances can be created using the static {@link #create(SingleBunnyNetConfig)}
 * factory method.</p>
 *
 * @since 2.1.0
 */
public sealed interface SingleBunnyStorage permits SingleBunnyStorageImpl{

    /**
     * Creates a new {@code SingleBunnyStorage} instance
     * using the provided configuration.
     *
     * @param singleBunnyNetConfig the configuration for the storage zone
     * @return a new {@code SingleBunnyStorage} instance
     */
    static SingleBunnyStorage create(SingleBunnyNetConfig singleBunnyNetConfig) {
        return new SingleBunnyStorageImpl(singleBunnyNetConfig);
    }

    /**
     * Downloads an object from the configured storage zone.
     *
     * <p>The returned {@link GetObjectResponse} must be closed
     * after use to release underlying HTTP resources.</p>
     *
     * @param key the object key (path) inside the storage zone
     * @return a {@link GetObjectResponse} containing the object data
     */
    GetObjectResponse download(String key);

    /**
     * Uploads an object to the configured storage zone.
     *
     * @param putObjectRequest the upload request containing
     *                         object metadata and data stream
     * @return a {@link PutObjectResponse} describing the uploaded object
     */
    PutObjectResponse uploadFile(PutObjectRequest putObjectRequest);

    /**
     * Deletes an object from the configured storage zone.
     *
     * @param key the object key (path) inside the storage zone
     */
    void delete(String key);
}