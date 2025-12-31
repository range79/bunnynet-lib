package com.range;

public class BunnyNetConfig {
    private final String apiKey;
    private final String storageZone;
    private final String storageRegion;

    public BunnyNetConfig(
            String apiKey,
            String storageZone,
            String storageRegion
    ) {
        this.apiKey = apiKey;
        this.storageZone = storageZone;
        this.storageRegion = storageRegion;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getStorageZone() {
        return storageZone;
    }

    public String getStorageRegion() {
        return storageRegion;
    }
}
