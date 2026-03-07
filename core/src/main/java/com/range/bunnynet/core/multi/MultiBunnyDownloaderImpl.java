package com.range.bunnynet.core.multi;

import com.range.bunnynet.core.AbstractBunnyDownloader;
import com.range.bunnynet.core.model.GetObjectResponse;
import com.range.bunnynet.core.region.Region;
import com.range.bunnynet.core.http.BunnyHttpClient;

final class MultiBunnyDownloaderImpl
        extends AbstractBunnyDownloader
        implements MultiBunnyDownloader {

    public MultiBunnyDownloaderImpl(
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
    public GetObjectResponse download(
            String storageZoneName,
            String key,
            Region storageRegion
    ) {

        MultiStorageValidator.validate(storageZoneName, key, storageRegion);

        return internalDownload(
                storageZoneName,
                storageRegion.getEndpoint(),
                key
        );
    }


}