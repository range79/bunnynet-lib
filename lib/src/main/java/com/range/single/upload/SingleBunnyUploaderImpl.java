package com.range.single.upload;

import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.common.http.BunnyHttpClient;
import com.range.common.upload.AbstractBunnyUploader;
import com.range.single.config.SingleBunnyNetConfig;

public class SingleBunnyUploaderImpl
        extends AbstractBunnyUploader
        implements SingleBunnyUploader {

    private final SingleBunnyNetConfig config;

    public SingleBunnyUploaderImpl(
            SingleBunnyNetConfig config,
            int connectionTimeout,
            int readTimeout
    ) {
        super(new BunnyHttpClient(
                requireConfig(config).apiKey(),
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

    private static SingleBunnyNetConfig requireConfig(SingleBunnyNetConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("SingleBunnyNetConfig cannot be null");
        }
        return config;
    }
}