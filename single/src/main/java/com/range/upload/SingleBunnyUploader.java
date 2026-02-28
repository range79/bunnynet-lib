package com.range.upload;

import com.range.config.SingleBunnyNetConfig;
import org.example.config.SingleBunnyNetConfig;
import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;

public interface SingleBunnyUploader {

    /**
     * a default constructor for SingleBunnyUploader
     * @param singleBunnyNetConfig for config
     */
    static SingleBunnyUploader create(SingleBunnyNetConfig singleBunnyNetConfig) {
        if (singleBunnyNetConfig == null) {
            throw new IllegalArgumentException("SingleBunnyNetConfig cannot be null");
        }
        return new SingleBunnyUploaderImpl(singleBunnyNetConfig, 15_000, 45_000);
    }
    /**
     * Creates a SingleBunnyUploader with custom timeout settings.
     *
     * @param singleBunnyNetConfig The configuration containing API key, storage zone, and region.
     * @param connectionTimeout    Maximum time in milliseconds to wait for establishing the connection.
     * @param readTimeout          Maximum time in milliseconds to wait for reading data from the connection.
     */
    static SingleBunnyUploader create(SingleBunnyNetConfig singleBunnyNetConfig, int connectionTimeout, int readTimeout ){
        if (singleBunnyNetConfig == null) {
            throw new IllegalArgumentException("SingleBunnyNetConfig cannot be null");
        }
        if (connectionTimeout <= 0) {
            throw new IllegalArgumentException("connectionTimeout must be positive");
        }

        if (readTimeout <= 0) {
            throw new IllegalArgumentException("readTimeout must be positive");
        }

        return new SingleBunnyUploaderImpl(singleBunnyNetConfig,connectionTimeout,readTimeout);
    }

    PutObjectResponse uploadFile(PutObjectRequest putObjectRequest);
}
