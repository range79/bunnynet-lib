package com.range.bunnynet.multi;

import com.range.bunnynet.core.model.PutObjectRequest;
import com.range.bunnynet.core.model.PutObjectResponse;
import com.range.bunnynet.core.region.Region;
import com.range.bunnynet.core.exception.BunnyFileUploadFailedException;

/**
 * Abstraction for uploading objects to multiple Bunny.net storage zones.
 *
 * <p>This interface is intended for multi-zone scenarios where the
 * storage zone and region are provided per upload operation.</p>
 *
 * @since 2.1.0
 */
interface MultiBunnyUploader {

    /**
     * Creates a {@code MultiBunnyUploader} with custom timeout settings.
     *
     * @param config                configuration containing the shared API key;
     *                              must not be null
     * @param connectionTimeout     maximum time in milliseconds to establish a connection
     * @param connectionReadTimeout maximum time in milliseconds to read data
     * @return a configured {@code MultiBunnyUploader} instance
     * @throws IllegalArgumentException if configuration is invalid
     */
    static MultiBunnyUploader create(
            MultiBunnyNetConfig config,
            int connectionTimeout,
            int connectionReadTimeout
    ) {
        MultiStorageValidator.validateConfig(config);
        return new MultiBunnyUploaderImpl(config, connectionTimeout, connectionReadTimeout);
    }

    /**
     * Creates a {@code MultiBunnyUploader} with default timeout settings.
     *
     * <p>Default values:</p>
     * <ul>
     *     <li>Connection timeout: 15,000 ms</li>
     *     <li>Read timeout: 60,000 ms</li>
     * </ul>
     *
     * @param config configuration containing the shared API key;
     *               must not be null
     * @return a configured {@code MultiBunnyUploader} instance
     * @throws IllegalArgumentException if configuration is invalid
     */
    static MultiBunnyUploader create(MultiBunnyNetConfig config) {
        MultiStorageValidator.validateConfig(config);
        return new MultiBunnyUploaderImpl(config, 15_000, 60_000);
    }

    /**
     * Uploads an object to the specified storage zone and region.
     *
     * @param putObjectRequest the upload request containing object data and metadata;
     *                         must not be null
     * @param storageZoneName  the name of the storage zone;
     *                         must not be null or blank
     * @param storageRegion    the target storage region; must not be null
     * @return a {@link PutObjectResponse} describing the uploaded object
     * @throws BunnyFileUploadFailedException if the upload operation fails
     */
    PutObjectResponse upload(
            PutObjectRequest putObjectRequest,
            String storageZoneName,
            Region storageRegion
    ) throws BunnyFileUploadFailedException;

    /**
     * Uploads an object to the specified storage zone using
     * the default region defined by the implementation.
     *
     * @param putObjectRequest the upload request containing object data and metadata;
     *                         must not be null
     * @param storageZoneName  the name of the storage zone;
     *                         must not be null or blank
     * @return a {@link PutObjectResponse} describing the uploaded object
     * @throws BunnyFileUploadFailedException if the upload operation fails
     */
    PutObjectResponse uploadFileBunnyWithDefaultRegion(
            PutObjectRequest putObjectRequest,
            String storageZoneName
    ) throws BunnyFileUploadFailedException;
}