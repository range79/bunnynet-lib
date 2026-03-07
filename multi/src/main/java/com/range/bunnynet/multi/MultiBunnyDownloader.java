package com.range.bunnynet.multi;

import com.range.bunnynet.core.model.GetObjectResponse;
import com.range.bunnynet.core.region.Region;

/**
 * Abstraction for downloading objects from multiple Bunny.net storage zones.
 *
 * <p>This interface is intended for multi-zone scenarios where
 * the storage zone name and region are provided per operation.</p>
 *
 * <p><strong>Resource management:</strong>
 * The returned {@link GetObjectResponse} must be closed after use
 * to release underlying HTTP resources.</p>
 *
 * @since 2.1.0
 */
 interface MultiBunnyDownloader {

    /**
     * Creates a {@code MultiBunnyDownloader} with custom timeout settings.
     *
     * @param config            configuration containing the shared API key;
     *                          must not be null
     * @param connectionTimeout maximum time in milliseconds to establish a connection
     * @param readTimeout       maximum time in milliseconds to read data
     * @return a configured {@code MultiBunnyDownloader} instance
     * @throws IllegalArgumentException if configuration is invalid
     */
    static MultiBunnyDownloader create(
            MultiBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        MultiStorageValidator.validateConfig(config);

        return new MultiBunnyDownloaderImpl(
                config,
                connectionTimeout,
                readTimeout
        );
    }

    /**
     * Creates a {@code MultiBunnyDownloader} with default timeout settings.
     *
     * <p>Default values:</p>
     * <ul>
     *     <li>Connection timeout: 15,000 ms</li>
     *     <li>Read timeout: 45,000 ms</li>
     * </ul>
     *
     * @param config configuration containing the shared API key;
     *               must not be null
     * @return a configured {@code MultiBunnyDownloader} instance
     * @throws IllegalArgumentException if configuration is invalid
     */
    static MultiBunnyDownloader create(MultiBunnyNetConfig config) {
        MultiStorageValidator.validateConfig(config);
        return create(config, 15_000, 45_000);
    }

    /**
     * Downloads an object from the specified storage zone and region.
     *
     * <pre>{@code
     * try (GetObjectResponse response =
     *          downloader.download("zone", "path/file.png", Region.DE)) {
     *     InputStream in = response.getStream();
     *     // read data
     * }
     * }</pre>
     *
     * @param storageZoneName the name of the storage zone;
     *                        must not be null or blank
     * @param key             the object key (path) inside the storage zone;
     *                        must not be null or blank
     * @param storageRegion   the target storage region; must not be null
     * @return a {@link GetObjectResponse} containing the object stream and metadata
     */
    GetObjectResponse download(
            String storageZoneName,
            String key,
            Region storageRegion
    );
}