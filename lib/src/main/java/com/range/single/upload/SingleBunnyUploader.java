package com.range.single.upload;

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
     * a customConstructor for SingleBunnyUploader
     * @param singleBunnyNetClient for config
     * @param connectionTimeOut for connection time out (don't make it so long )
     * @param connectionReadTimeOut for connection read time out (also don't make it )
     */
    static SingleBunnyUploader create(SingleBunnyNetClient singleBunnyNetClient,int connectionTimeOut,int connectionReadTimeOut){
        return new SingleBunnyUploaderImpl(singleBunnyNetClient,connectionTimeOut,connectionReadTimeOut);
    }

    PutObjectResponse uploadFile(PutObjectRequest putObjectRequest);
}
