package com.range.multi.delete;

import com.range.common.enums.Region;
import com.range.multi.config.MultiBunnyNetConfig;



public interface MultiBunnyDeleter {

    static MultiBunnyDeleter create(
            MultiBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        if (config == null) {
            throw new IllegalArgumentException("MultiBunnyNetConfig cannot be null");
        }
        return new MultiBunnyDeleterImpl(config, connectionTimeout, readTimeout);
    }

    static MultiBunnyDeleter create(MultiBunnyNetConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("MultiBunnyNetConfig cannot be null");
        }
        return create(config, 15_000, 45_000);
    }

    void delete(String storageZoneName, String key, Region region);
}