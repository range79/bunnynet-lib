package com.range.multi.downloader;

import com.range.common.download.AbstractBunnyDownloader;
import com.range.common.dto.GetObjectResponse;
import com.range.common.enums.Region;
import com.range.common.http.BunnyHttpClient;
import com.range.multi.config.MultiBunnyNetConfig;

class MultiBunnyDownloaderImpl
        extends AbstractBunnyDownloader
        implements MultiBunnyDownloader {

    public MultiBunnyDownloaderImpl(
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
    public GetObjectResponse download(
            String storageZoneName,
            String key,
            Region storageRegion
    ) {

        validate(storageZoneName, key, storageRegion);

        return internalDownload(
                storageZoneName,
                storageRegion.getEndpoint(),
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