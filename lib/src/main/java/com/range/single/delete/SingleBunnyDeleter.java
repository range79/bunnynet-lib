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
        if (connectionTimeout <= 0) {
            throw new IllegalArgumentException("connectionTimeout must be positive");
        }

        if (readTimeout <= 0) {
            throw new IllegalArgumentException("readTimeout must be positive");
        }
        return new SingleBunnyDeleterImpl(config, connectionTimeout, readTimeout);
    }

    static SingleBunnyDeleter create(SingleBunnyNetConfig config) {
        return create(config, 15_000, 45_000);
    }

    void delete(String key);
}