package com.range.delete;

import com.range.common.delete.AbstractBunnyDeleter;
import com.range.common.region.Region;
import com.range.common.http.BunnyHttpClient;
import com.range.properties.MultiBunnyNetConfig;
import com.range.validator.MultiStorageValidator;


class MultiBunnyDeleterImpl
        extends AbstractBunnyDeleter
        implements MultiBunnyDeleter {

    public MultiBunnyDeleterImpl(
            MultiBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        super(new BunnyHttpClient(
                config.apiKey(),
                connectionTimeout,
                readTimeout
        ));
    }

    @Override
    public void delete(String storageZoneName, String key, Region region) {
        MultiStorageValidator.validate(storageZoneName, key, region);
        internalDelete(
                storageZoneName,
                region.getEndpoint(),
                key
        );
    }

}