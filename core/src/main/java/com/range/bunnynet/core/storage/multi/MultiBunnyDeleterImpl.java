package com.range.bunnynet.core.storage.multi;

import com.range.bunnynet.core.http.AbstractBunnyDeleter;
import com.range.bunnynet.core.region.Region;
import com.range.bunnynet.core.http.BunnyHttpClient;


 final class MultiBunnyDeleterImpl
        extends AbstractBunnyDeleter
        implements MultiBunnyDeleter {

    public MultiBunnyDeleterImpl(
            MultiBunnyNetConfig config,
            int connectionTimeout
    ) {
        super(new BunnyHttpClient(
                config.apiKey(),
                connectionTimeout
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