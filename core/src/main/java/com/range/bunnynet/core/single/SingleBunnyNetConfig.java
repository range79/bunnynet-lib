package com.range.bunnynet.core.single;

import com.range.bunnynet.core.region.Region;

/**
 * Configuration holder for a single Bunny.net storage zone.
 *
 * <p>This configuration is intended for use cases where all operations
 * target the same storage zone and region.</p>
 *
 * @param apiKey      the Bunny.net API key used for authentication;
 *                    must not be null or blank
 * @param region      the target storage region; must not be null
 * @param storageZone the name of the storage zone;
 *                    must not be null or blank
 *
 * @since 2.1.0
 */
public record SingleBunnyNetConfig(
        String apiKey,
        Region region,
        String storageZone
) {

    /**
     * Canonical constructor with validation.
     *
     * @throws IllegalArgumentException if any required value is null or blank
     */
    public SingleBunnyNetConfig {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("API key cannot be null or empty");
        }
        if (region == null) {
            throw new IllegalArgumentException("Region cannot be null");
        }
        if (storageZone == null || storageZone.isBlank()) {
            throw new IllegalArgumentException("Storage zone cannot be null or empty");
        }
    }
}