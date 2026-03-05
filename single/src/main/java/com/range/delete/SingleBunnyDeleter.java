package com.range.delete;

import com.range.properties.SingleBunnyNetConfig;

/**
 * Abstraction for deleting objects from a single Bunny.net storage zone.
 *
 * <p>Instances can be created using the provided static factory methods.</p>
 *
 * @since 2.1.0
 */
public interface SingleBunnyDeleter {

    /**
     * Creates a {@code SingleBunnyDeleter} with custom timeout settings.
     *
     * @param config            configuration containing API key, storage zone, and region;
     *                          must not be null
     * @param connectionTimeout maximum time in milliseconds to establish a connection;
     *                          must be positive
     * @param readTimeout       maximum time in milliseconds to read data;
     *                          must be positive
     * @return a configured {@code SingleBunnyDeleter} instance
     * @throws IllegalArgumentException if configuration is null
     *                                  or timeout values are non-positive
     */
    static SingleBunnyDeleter create(
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
        return new SingleBunnyDeleterImpl(config, connectionTimeout, readTimeout);
    }

    /**
     * Creates a {@code SingleBunnyDeleter} with default timeout settings.
     *
     * <p>Default values:</p>
     * <ul>
     *     <li>Connection timeout: 15,000 ms</li>
     *     <li>Read timeout: 45,000 ms</li>
     * </ul>
     *
     * @param config configuration containing API key, storage zone, and region;
     *               must not be null
     * @return a configured {@code SingleBunnyDeleter} instance
     * @throws IllegalArgumentException if configuration is null
     */
    static SingleBunnyDeleter create(SingleBunnyNetConfig config) {
        return create(config, 15_000, 45_000);
    }

    /**
     * Deletes an object from the configured storage zone.
     *
     * @param key the object key (path) inside the storage zone;
     *            must not be null or blank
     */
    void delete(String key);
}