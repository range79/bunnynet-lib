package com.range.single.config;

import com.range.common.enums.Region;

/**
 * SingleBunnyNetConfig is a configuration holder for the BunnyCDN Storage API.
 * It stores a single API key, a storage zone, and a specific region.
 * Use this class when:
 * You always upload to the same region and storage zone.
 */
public record SingleBunnyNetConfig(String apiKey, Region region, String storageZone) {

    /**
     * Creates a new SingleBunnyNetConfig instance with all configuration parameters.
     *
     * @param apiKey      Your BunnyCDN API key used for authentication.
     * @param region      The target BunnyCDN storage region.
     * @param storageZone The name of the storage zone.
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

    /**
     * @return The configured BunnyCDN storage region.
     */
    @Override
    public Region region() {
        return region;
    }

    /**
     * @return The BunnyCDN API key.
     */
    @Override
    public String apiKey() {
        return apiKey;
    }

    /**
     * @return The name of the configured BunnyCDN storage zone.
     */
    @Override
    public String storageZone() {
        return storageZone;
    }

}
