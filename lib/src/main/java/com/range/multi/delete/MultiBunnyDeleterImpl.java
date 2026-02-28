package com.range.multi.delete;

import com.range.common.delete.AbstractBunnyDeleter;
import com.range.common.enums.Region;
import com.range.common.http.BunnyHttpClient;
import com.range.multi.config.MultiBunnyNetConfig;
import com.range.multi.validator.MultiStorageValidator;


public class MultiBunnyDeleterImpl
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