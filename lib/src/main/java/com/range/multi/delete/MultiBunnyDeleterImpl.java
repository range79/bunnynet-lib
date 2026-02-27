package com.range.multi.delete;

import com.range.common.delete.AbstractBunnyDeleter;
import com.range.common.enums.Region;
import com.range.common.http.BunnyHttpClient;
import com.range.multi.config.MultiBunnyNetConfig;


public class MultiBunnyDeleterImpl
        extends AbstractBunnyDeleter
        implements MultiBunnyDeleter {

    public MultiBunnyDeleterImpl(
            MultiBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        super(new BunnyHttpClient(
                requireConfig(config).apiKey(),
                connectionTimeout,
                readTimeout
        ));
    }

    @Override
    public void delete(String storageZoneName, String key, Region region) {
        validate(storageZoneName, key, region);

        internalDelete(
                storageZoneName,
                region.getEndpoint(),
                key
        );
    }

    private static MultiBunnyNetConfig requireConfig(MultiBunnyNetConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("MultiBunnyNetConfig cannot be null");
        }
        return config;
    }

    private void validate(String storageZoneName, String key, Region region) {
        if (storageZoneName == null || storageZoneName.isBlank()) {
            throw new IllegalArgumentException("Storage zone cannot be null or empty");
        }
        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }
        if (region == null) {
            throw new IllegalArgumentException("Region cannot be null");
        }
    }
}