package com.range.single.delete;


import com.range.common.delete.AbstractBunnyDeleter;
import com.range.common.http.BunnyHttpClient;
import com.range.single.config.SingleBunnyNetConfig;
import com.range.single.delete.SingleBunnyDeleter;

public class SingleBunnyDeleterImpl
        extends AbstractBunnyDeleter
        implements SingleBunnyDeleter {

    private final SingleBunnyNetConfig config;

    public SingleBunnyDeleterImpl(
            SingleBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        super(new BunnyHttpClient(
                config.apiKey(),
                connectionTimeout,
                readTimeout
        ));
        this.config = config;
    }

    @Override
    public void delete(String key) {

        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }

        internalDelete(
                config.storageZone(),
                config.region().getEndpoint(),
                key
        );
    }
}