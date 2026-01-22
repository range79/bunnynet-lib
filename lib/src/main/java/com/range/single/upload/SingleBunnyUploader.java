package com.range.single.upload;

import com.range.single.config.SingleBunnyNetConfig;
import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;

public interface SingleBunnyUploader {

    /**
     * a default constructor for SingleBunnyUploader
     * @param singleBunnyNetConfig for config
     */
    static SingleBunnyUploader create(SingleBunnyNetConfig singleBunnyNetConfig){
        return new SingleBunnyUploaderImpl(singleBunnyNetConfig,15_000,45_000);
    }

    /**
     * Creates a SingleBunnyUploader with custom timeout settings.
     *
     * @param singleBunnyNetConfig The configuration containing API key, storage zone, and region.
     * @param connectionTimeout    Maximum time in milliseconds to wait for establishing the connection.
     * @param readTimeout          Maximum time in milliseconds to wait for reading data from the connection.
     */
    static SingleBunnyUploader create(SingleBunnyNetConfig singleBunnyNetConfig, int connectionTimeout, int readTimeout ){
        return new SingleBunnyUploaderImpl(singleBunnyNetConfig,connectionTimeout,readTimeout);
    }

    PutObjectResponse uploadFile(PutObjectRequest putObjectRequest);
}
