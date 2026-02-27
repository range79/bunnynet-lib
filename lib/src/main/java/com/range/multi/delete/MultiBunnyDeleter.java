package com.range.multi.delete;

import com.range.common.enums.Region;
import com.range.multi.config.MultiBunnyNetConfig;
import com.range.multi.deleter.impl.MultiBunnyDeleterImpl;


public interface MultiBunnyDeleter {

    static MultiBunnyDeleter create(
            MultiBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        return new MultiBunnyDeleterImpl(config, connectionTimeout, readTimeout);
    }

    static MultiBunnyDeleter create(MultiBunnyNetConfig config) {
        return create(config, 15_000, 45_000);
    }

    void delete(String storageZoneName, String key, Region region);
}