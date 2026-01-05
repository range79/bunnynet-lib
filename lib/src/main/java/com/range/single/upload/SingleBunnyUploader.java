package com.range.single.upload;

import com.range.common.http.BunnyHttpClient;
import com.range.single.config.SingleBunnyNetClient;
import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;

public interface SingleBunnyUploader {

    /**
     * a default constructor for SingleBunnyUploader
     * @param singleBunnyNetClient for config
     */
    static SingleBunnyUploader create(SingleBunnyNetClient singleBunnyNetClient){
        return new SingleBunnyUploaderImpl(singleBunnyNetClient,15_000,45_000);
    }

    /**
     * Creates a SingleBunnyUploader with custom timeout settings.
     *
     * @param singleBunnyNetClient The configuration containing API key, storage zone, and region.
     * @param connectionTimeout    Maximum time in milliseconds to wait for establishing the connection.
     * @param readTimeout          Maximum time in milliseconds to wait for reading data from the connection.
     */
    static SingleBunnyUploader create(SingleBunnyNetClient singleBunnyNetClient,int connectionTimeout,int readTimeout ){
        return new SingleBunnyUploaderImpl(singleBunnyNetClient,connectionTimeout,readTimeout);
    }

    PutObjectResponse uploadFile(PutObjectRequest putObjectRequest);
}
