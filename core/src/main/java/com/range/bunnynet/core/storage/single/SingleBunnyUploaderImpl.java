package com.range.bunnynet.core.storage.single;

import com.range.bunnynet.core.model.PutObjectRequest;
import com.range.bunnynet.core.model.PutObjectResponse;
import com.range.bunnynet.core.http.BunnyHttpClient;
import com.range.bunnynet.core.http.AbstractBunnyUploader;

final class SingleBunnyUploaderImpl
        extends AbstractBunnyUploader
        implements SingleBunnyUploader {

    private final SingleBunnyNetConfig config;

    SingleBunnyUploaderImpl(
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
    public PutObjectResponse uploadFile(PutObjectRequest putObjectRequest) {

        if (putObjectRequest == null) {
            throw new IllegalArgumentException("PutObjectRequest cannot be null");
        }

        return internalUpload(
                putObjectRequest,
                config.storageZone(),
                config.region().getEndpoint()
        );
    }


}