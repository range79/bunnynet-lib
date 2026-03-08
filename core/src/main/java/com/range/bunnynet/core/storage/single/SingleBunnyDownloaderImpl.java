package com.range.bunnynet.core.storage.single;

import com.range.bunnynet.core.http.AbstractBunnyDownloader;
import com.range.bunnynet.core.model.GetObjectResponse;
import com.range.bunnynet.core.http.BunnyHttpClient;

final class SingleBunnyDownloaderImpl
        extends AbstractBunnyDownloader
        implements SingleBunnyDownloader {

    private final SingleBunnyNetConfig config;

    SingleBunnyDownloaderImpl(
            SingleBunnyNetConfig config,
            int connectionTimeout
    ) {
        super(new BunnyHttpClient(
                config.apiKey(),
                connectionTimeout
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

}