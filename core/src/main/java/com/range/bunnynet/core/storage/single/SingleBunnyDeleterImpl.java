package com.range.bunnynet.core.storage.single;


import com.range.bunnynet.core.http.AbstractBunnyDeleter;
import com.range.bunnynet.core.http.BunnyHttpClient;


final class SingleBunnyDeleterImpl
        extends AbstractBunnyDeleter
        implements SingleBunnyDeleter {

    private final SingleBunnyNetConfig config;

    public SingleBunnyDeleterImpl(
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