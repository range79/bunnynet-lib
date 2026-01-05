package com.range.single.config;

import com.range.common.enums.Region;

/**
 * SingleBunnyNetClient is a configuration holder for the BunnyCDN Storage API.
 * It stores a single API key, a storage zone, and a specific region.
 * Use this class when:
 * You always upload to the same region and storage zone.
 */
public class SingleBunnyNetClient {

    private final String apiKey;
    private final Region region;
    private final String storageZone;

    /**
     * Creates a new SingleBunnyNetClient instance with all configuration parameters.
     *
     * @param apiKey      Your BunnyCDN API key used for authentication.
     * @param region      The target BunnyCDN storage region.
     * @param storageZone The name of the storage zone.
     */
    public SingleBunnyNetClient(String apiKey, Region region, String storageZone) {
        this.apiKey = apiKey;
        this.region = region;
        this.storageZone = storageZone;
    }

    /**
     * @return The configured BunnyCDN storage region.
     */
    public Region getRegion() {
        return region;
    }

    /**
     * @return The BunnyCDN API key.
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * @return The name of the configured BunnyCDN storage zone.
     */
    public String getStorageZone() {
        return storageZone;
    }

}
