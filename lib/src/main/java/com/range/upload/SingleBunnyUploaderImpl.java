package com.range.upload;

import com.range.config.SingleBunnyNetClient;
import com.range.dto.PutObjectRequest;
import com.range.dto.PutObjectResponse;

class SingleBunnyUploaderImpl implements SingleBunnyUploader {
    private final SingleBunnyNetClient singleBunnyNetClient;
    private final int connectionTimeOut;
    private final int connectionReadTimeOut;

    public SingleBunnyUploaderImpl(SingleBunnyNetClient singleBunnyNetClient, int connectionTimeOut, int connectionReadTimeOut) {
        this.connectionReadTimeOut = connectionReadTimeOut;
        this.connectionTimeOut = connectionTimeOut;
        this.singleBunnyNetClient = singleBunnyNetClient;
    }

    @Override
    public PutObjectResponse uploadFile(PutObjectRequest putObjectRequest) {
        return null;
    }
}
