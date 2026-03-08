package com.range.bunnynet.core.storage.single;

/**
 * Abstraction for deleting objects from a single Bunny.net storage zone.
 *
 * <p>Instances can be created using the provided static factory methods.</p>
 *
 * @since 2.1.0
 */
public sealed interface SingleBunnyDeleter permits SingleBunnyDeleterImpl {

    /**
     * Creates a {@code SingleBunnyDeleter} with custom timeout settings.
     *
     * @param config            configuration containing API key, storage zone, and region;
     *                          must not be null
     * @param connectionTimeout maximum time in milliseconds to establish a connection;
     *                          must be positive
     * @return a configured {@code SingleBunnyDeleter} instance
     * @throws IllegalArgumentException if configuration is null
     *                                  or timeout values are non-positive
     */
    static SingleBunnyDeleter create(
            SingleBunnyNetConfig config,
            int connectionTimeout
    ) {
        if (config == null) {
            throw new IllegalArgumentException("SingleBunnyNetConfig cannot be null");
        }
        if (connectionTimeout <= 0) {
            throw new IllegalArgumentException("connectionTimeout must be positive");
        }
        return new SingleBunnyDeleterImpl(config, connectionTimeout);
    }

    /**
     * Creates a {@code SingleBunnyDeleter} with default timeout settings.
     *
     * <p>Default value:</p>
     * <ul>
     *     <li>Connection timeout: 15,000 ms</li>
     * </ul>
     *
     * @param config configuration containing API key, storage zone, and region;
     *               must not be null
     * @return a configured {@code SingleBunnyDeleter} instance
     * @throws IllegalArgumentException if configuration is null
     */
    static SingleBunnyDeleter create(SingleBunnyNetConfig config) {
        return create(config, 15_000);
    }

    /**
     * Deletes an object from the configured storage zone.
     *
     * @param key the object key (path) inside the storage zone;
     *            must not be null or blank
     */
    void delete(String key);
}