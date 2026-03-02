package com.range.delete;

import com.range.common.enums.Region;
import com.range.config.MultiBunnyNetConfig;
import com.range.validator.MultiStorageValidator;

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
public interface MultiBunnyDeleter {

    /**
     * Creates a {@code MultiBunnyDeleter} with custom timeout settings.
     *
     * @param config            configuration containing the shared API key;
     *                          must not be null
     * @param connectionTimeout maximum time in milliseconds to establish a connection
     * @param readTimeout       maximum time in milliseconds to read data
     * @return a configured {@code MultiBunnyDeleter} instance
     * @throws IllegalArgumentException if configuration is invalid
     */
    static MultiBunnyDeleter create(
            MultiBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        MultiStorageValidator.validateConfig(config);
        return new MultiBunnyDeleterImpl(config, connectionTimeout, readTimeout);
    }

    /**
     * Creates a {@code MultiBunnyDeleter} with default timeout settings.
     *
     * <p>Default values:</p>
     * <ul>
     *     <li>Connection timeout: 15,000 ms</li>
     *     <li>Read timeout: 45,000 ms</li>
     * </ul>
     *
     * @param config configuration containing the shared API key;
     *               must not be null
     * @return a configured {@code MultiBunnyDeleter} instance
     * @throws IllegalArgumentException if configuration is invalid
     */
    static MultiBunnyDeleter create(MultiBunnyNetConfig config) {
        MultiStorageValidator.validateConfig(config);
        return create(config, 15_000, 45_000);
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