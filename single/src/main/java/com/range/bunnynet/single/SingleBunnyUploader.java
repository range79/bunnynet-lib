package com.range.bunnynet.single;

import com.range.bunnynet.core.model.PutObjectRequest;
import com.range.bunnynet.core.model.PutObjectResponse;

/**
 * Abstraction for uploading objects to a single Bunny.net storage zone.
 *
 * <p>Instances can be created using the provided static factory methods.</p>
 *
 * @since 2.1.0
 */
interface SingleBunnyUploader {

    /**
     * Creates a {@code SingleBunnyUploader} with default timeout settings.
     *
     * <p>Default values:</p>
     * <ul>
     *     <li>Connection timeout: 15,000 ms</li>
     *     <li>Read timeout: 45,000 ms</li>
     * </ul>
     *
     * @param singleBunnyNetConfig configuration containing API key,
     *                             storage zone, and region; must not be null
     * @return a configured {@code SingleBunnyUploader} instance
     * @throws IllegalArgumentException if {@code singleBunnyNetConfig} is null
     */
    static SingleBunnyUploader create(SingleBunnyNetConfig singleBunnyNetConfig) {
        if (singleBunnyNetConfig == null) {
            throw new IllegalArgumentException("SingleBunnyNetConfig cannot be null");
        }
        return new SingleBunnyUploaderImpl(singleBunnyNetConfig, 15_000, 45_000);
    }

    /**
     * Creates a {@code SingleBunnyUploader} with custom timeout settings.
     *
     * @param singleBunnyNetConfig configuration containing API key,
     *                             storage zone, and region; must not be null
     * @param connectionTimeout    maximum time in milliseconds to establish a connection;
     *                             must be positive
     * @param readTimeout          maximum time in milliseconds to read data;
     *                             must be positive
     * @return a configured {@code SingleBunnyUploader} instance
     * @throws IllegalArgumentException if configuration is null
     *                                  or timeout values are non-positive
     */
    static SingleBunnyUploader create(
            SingleBunnyNetConfig singleBunnyNetConfig,
            int connectionTimeout,
            int readTimeout
    ) {
        if (singleBunnyNetConfig == null) {
            throw new IllegalArgumentException("SingleBunnyNetConfig cannot be null");
        }
        if (connectionTimeout <= 0) {
            throw new IllegalArgumentException("connectionTimeout must be positive");
        }
        if (readTimeout <= 0) {
            throw new IllegalArgumentException("readTimeout must be positive");
        }

        return new SingleBunnyUploaderImpl(
                singleBunnyNetConfig,
                connectionTimeout,
                readTimeout
        );
    }

    /**
     * Uploads an object to the configured storage zone.
     *
     * @param putObjectRequest the upload request containing object data and metadata;
     *                         must not be null
     * @return a {@link PutObjectResponse} describing the uploaded object
     */
    PutObjectResponse uploadFile(PutObjectRequest putObjectRequest);
}