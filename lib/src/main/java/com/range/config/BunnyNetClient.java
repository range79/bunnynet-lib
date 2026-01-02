package com.range.config;

import com.range.enums.Region;
/**
 * BunnyNetClient is a simple configuration holder for BunnyCDN Storage API.
 * It contains the API key, storage zone name and selected storage region.
*/
public class BunnyNetClient {
    private final String apiKey;
    private final String storageZone;
    private final Region storageRegion;

    /**
     * BunnyNetClient constructor with all parameters.
     *
     * @param apiKey        Your BunnyCDN API key used for authentication.
     * @param storageZone   The name of your BunnyCDN storage zone.
     * @param storageRegion The BunnyCDN storage region you want to use.
     */

    public BunnyNetClient(
            String apiKey,
            String storageZone,
            Region storageRegion
    ) {
        this.apiKey = apiKey;
        this.storageZone = storageZone;
        this.storageRegion = storageRegion;
    }

    /**
     * default BunnyNetClient Constructor
     *
     * @param apiKey        Your BunnyCDN API key used for authentication.
     * @param storageZone   The name of your BunnyCDN storage zone.
     *                    this constructor Automatic selects default FrankFurt_DE Region
     */
    public BunnyNetClient(
            String apiKey,
            String storageZone
    ) {
        this.apiKey = apiKey;
        this.storageZone = storageZone;
        storageRegion = Region.FRANKFURT_DE;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getStorageZone() {
        return storageZone;
    }

    public Region getStorageRegion() {
        return storageRegion;
    }
}
