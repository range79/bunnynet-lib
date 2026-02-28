package com.range.multi.downloader;

import com.range.common.dto.GetObjectResponse;
import com.range.common.enums.Region;
import com.range.multi.config.MultiBunnyNetConfig;
import com.range.multi.validator.MultiStorageValidator;

public interface MultiBunnyDownloader {

    static MultiBunnyDownloader create(
            MultiBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        MultiStorageValidator.validateConfig(config);

        return new MultiBunnyDownloaderImpl(
                config,
                connectionTimeout,
                readTimeout
        );
    }

    static MultiBunnyDownloader create(MultiBunnyNetConfig config) {

        MultiStorageValidator.validateConfig(config);
        return create(config, 15_000, 45_000);
    }

    GetObjectResponse download(
            String storageZoneName,
            String key,
            Region storageRegion
    );
}