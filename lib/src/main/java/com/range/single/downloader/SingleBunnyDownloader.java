package com.range.single.downloader;

import com.range.common.dto.GetObjectResponse;
import com.range.single.config.SingleBunnyNetConfig;

public interface SingleBunnyDownloader {

    static SingleBunnyDownloader create(
            SingleBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        if (config == null) {
            throw new IllegalArgumentException("SingleBunnyNetConfig cannot be null");
        }
        return new SingleBunnyDownloaderImpl(config, connectionTimeout, readTimeout);
    }

    static SingleBunnyDownloader create(SingleBunnyNetConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("SingleBunnyNetConfig cannot be null");
        }
        return create(config, 15_000, 45_000);
    }

    GetObjectResponse download(String key);
}