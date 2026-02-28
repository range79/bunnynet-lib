package com.range.multi.downloader;

import com.range.common.dto.GetObjectResponse;
import com.range.common.enums.Region;
import com.range.multi.config.MultiBunnyNetConfig;

public interface MultiBunnyDownloader {

    static MultiBunnyDownloader create(
            MultiBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        if (config == null) {
            throw new IllegalArgumentException("MultiBunnyNetConfig cannot be null");
        }


        return new MultiBunnyDownloaderImpl(
                config,
                connectionTimeout,
                readTimeout
        );
    }

    static MultiBunnyDownloader create(MultiBunnyNetConfig config) {
        return create(config, 15_000, 45_000);
    }

    GetObjectResponse download(
            String storageZoneName,
            String key,
            Region storageRegion
    );
}