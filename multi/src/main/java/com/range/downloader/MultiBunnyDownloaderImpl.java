package com.range.downloader;

import com.range.common.download.AbstractBunnyDownloader;
import com.range.common.dto.GetObjectResponse;
import com.range.common.region.Region;
import com.range.common.http.BunnyHttpClient;
import com.range.validator.MultiStorageValidator;
import com.range.properties.MultiBunnyNetConfig;

class MultiBunnyDownloaderImpl
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