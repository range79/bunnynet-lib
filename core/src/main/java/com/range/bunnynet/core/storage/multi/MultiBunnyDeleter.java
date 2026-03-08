package com.range.bunnynet.core.storage.multi;

import com.range.bunnynet.core.region.Region;

/**
 * Abstraction for deleting objects from multiple Bunny.net storage zones.
 *
 * <p>This interface is intended for multi-zone scenarios where the
 * storage zone name and region are provided at operation time.</p>
 *
 * <p>Instances can be created using the provided static factory methods.</p>
 *
 * @since 2.1.0
 */
sealed interface MultiBunnyDeleter permits MultiBunnyDeleterImpl {

    /**
     * Creates a {@code MultiBunnyDeleter} with custom timeout settings.
     *
     * @param config            configuration containing the shared API key;
     *                          must not be null
     * @param connectionTimeout maximum time in milliseconds to establish a connection
     * @return a configured {@code MultiBunnyDeleter} instance
     * @throws IllegalArgumentException if configuration is invalid
     */
    static MultiBunnyDeleter create(
            MultiBunnyNetConfig config,
            int connectionTimeout
    ) {
        MultiStorageValidator.validateConfig(config);
        return new MultiBunnyDeleterImpl(config, connectionTimeout);
    }

    /**
     * Creates a {@code MultiBunnyDeleter} with default timeout settings.
     *
     * <p>Default values:</p>
     * <ul>
     *     <li>Connection timeout: 15,000 ms</li>
     * </ul>
     *
     * @param config configuration containing the shared API key;
     *               must not be null
     * @return a configured {@code MultiBunnyDeleter} instance
     * @throws IllegalArgumentException if configuration is invalid
     */
    static MultiBunnyDeleter create(MultiBunnyNetConfig config) {
        MultiStorageValidator.validateConfig(config);
        return create(config, 15_000);
    }

    /**
     * Deletes an object from the specified storage zone and region.
     *
     * @param storageZoneName the name of the storage zone;
     *                        must not be null or blank
     * @param key             the object key (path) inside the storage zone;
     *                        must not be null or blank
     * @param region          the target storage region; must not be null
     */
    void delete(String storageZoneName, String key, Region region);
}