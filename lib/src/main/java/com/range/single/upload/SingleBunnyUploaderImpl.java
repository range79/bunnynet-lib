package com.range.single.upload;

import com.range.common.http.BunnyHttpClient;
import com.range.single.config.SingleBunnyNetClient;
import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;

class SingleBunnyUploaderImpl implements SingleBunnyUploader {
    private final SingleBunnyNetClient singleBunnyNetClient;
    private final BunnyHttpClient httpClient;

    public SingleBunnyUploaderImpl(SingleBunnyNetClient singleBunnyNetClient,BunnyHttpClient httpClient) {
        this.singleBunnyNetClient = singleBunnyNetClient;
        this.httpClient=httpClient;
    }

    @Override
    public PutObjectResponse uploadFile(PutObjectRequest putObjectRequest) {
        String url = singleBunnyNetClient.getRegion().getEndpoint()+"/"
                +singleBunnyNetClient.getStorageZone()+"/"+putObjectRequest.getKey();
        return null;
    }
}
