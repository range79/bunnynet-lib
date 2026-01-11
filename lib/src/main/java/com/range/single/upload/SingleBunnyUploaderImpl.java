package com.range.single.upload;

import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.common.http.BunnyHttpClient;
import com.range.common.upload.AbstractBunnyUploader;
import com.range.single.config.SingleBunnyNetConfig;

public class SingleBunnyUploaderImpl extends AbstractBunnyUploader implements SingleBunnyUploader {
    private final SingleBunnyNetConfig config;

    public SingleBunnyUploaderImpl(SingleBunnyNetConfig config, int connectionTimeout, int readTimeout) {
        super(new BunnyHttpClient(config.apiKey(), connectionTimeout, readTimeout));
        this.config = config;
    }

    @Override
    public PutObjectResponse uploadFile(PutObjectRequest putObjectRequest) {
        return internalUpload(putObjectRequest, config.storageZone(), config.region().getEndpoint());
    }
}