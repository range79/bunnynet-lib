package com.range.bunnynet.core.single;

import com.range.bunnynet.core.model.GetObjectResponse;

/**
 * Abstraction for downloading objects from a single Bunny.net storage zone.
 *
 * <p>Instances can be created using the provided static factory methods.</p>
 *
 * @since 2.1.0
 */
interface SingleBunnyDownloader {

    /**
     * Creates a {@code SingleBunnyDownloader} with custom timeout settings.
     *
     * @param config            configuration containing API key, storage zone, and region;
     *                          must not be null
     * @param connectionTimeout maximum time in milliseconds to establish a connection;
     *                          must be positive
     * @param readTimeout       maximum time in milliseconds to read data;
     *                          must be positive
     * @return a configured {@code SingleBunnyDownloader} instance
     * @throws IllegalArgumentException if configuration is null
     *                                  or timeout values are non-positive
     */
    static SingleBunnyDownloader create(
            SingleBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        if (config == null) {
            throw new IllegalArgumentException("SingleBunnyNetConfig cannot be null");
        }
        if (connectionTimeout <= 0) {
            throw new IllegalArgumentException("connectionTimeout must be positive");
        }
        if (readTimeout <= 0) {
            throw new IllegalArgumentException("readTimeout must be positive");
        }
        return new SingleBunnyDownloaderImpl(config, connectionTimeout, readTimeout);
    }

    /**
     * Creates a {@code SingleBunnyDownloader} with default timeout settings.
     *
     * <p>Default values:</p>
     * <ul>
     *     <li>Connection timeout: 15,000 ms</li>
     *     <li>Read timeout: 45,000 ms</li>
     * </ul>
     *
     * @param config configuration containing API key, storage zone, and region;
     *               must not be null
     * @return a configured {@code SingleBunnyDownloader} instance
     * @throws IllegalArgumentException if configuration is null
     */
    static SingleBunnyDownloader create(SingleBunnyNetConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("SingleBunnyNetConfig cannot be null");
        }
        return create(config, 15_000, 45_000);
    }

    /**
     * Downloads an object from the configured storage zone.
     *
     * <p>The returned {@link GetObjectResponse} must be closed
     * after use to release underlying HTTP resources.</p>
     *
     * @param key the object key (path) inside the storage zone;
     *            must not be null or blank
     * @return a {@link GetObjectResponse} containing object data and metadata
     */
    GetObjectResponse download(String key);
}