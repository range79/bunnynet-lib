package com.range.single.delete;


import com.range.single.config.SingleBunnyNetConfig;

public interface SingleBunnyDeleter {

    static SingleBunnyDeleter create(
            SingleBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        if (config == null) {
            throw new IllegalArgumentException("SingleBunnyNetConfig cannot be null");
        }
        return new SingleBunnyDeleterImpl(config, connectionTimeout, readTimeout);
    }

    static SingleBunnyDeleter create(SingleBunnyNetConfig config) {
        return create(config, 15_000, 45_000);
    }

    void delete(String key);
}