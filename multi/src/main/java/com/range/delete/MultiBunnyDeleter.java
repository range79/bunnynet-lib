package com.range.delete;

import com.range.common.enums.Region;
import com.range.config.MultiBunnyNetConfig;
import com.range.validator.MultiStorageValidator;


public interface MultiBunnyDeleter {

    static MultiBunnyDeleter create(
            MultiBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        MultiStorageValidator.validateConfig(config);
        return new MultiBunnyDeleterImpl(config, connectionTimeout, readTimeout);
    }

    static MultiBunnyDeleter create(MultiBunnyNetConfig config) {
        MultiStorageValidator.validateConfig(config);
        return create(config, 15_000, 45_000);
    }

    void delete(String storageZoneName, String key, Region region);
}