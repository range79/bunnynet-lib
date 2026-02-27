package com.range.single.downloader;

import com.range.common.download.AbstractBunnyDownloader;
import com.range.common.dto.GetObjectResponse;
import com.range.common.http.BunnyHttpClient;
import com.range.single.config.SingleBunnyNetConfig;

class SingleBunnyDownloaderImpl
        extends AbstractBunnyDownloader
        implements SingleBunnyDownloader {

    private final SingleBunnyNetConfig config;

    public SingleBunnyDownloaderImpl(
            SingleBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        super(new BunnyHttpClient(
                requireConfig(config).apiKey(),
                connectionTimeout,
                readTimeout
        ));
        this.config = config;
    }

    @Override
    public GetObjectResponse download(String key) {

        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }

        return internalDownload(
                config.storageZone(),
                config.region().getEndpoint(),
                key
        );
    }

    private static SingleBunnyNetConfig requireConfig(SingleBunnyNetConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("SingleBunnyNetConfig cannot be null");
        }
        return config;
    }
}