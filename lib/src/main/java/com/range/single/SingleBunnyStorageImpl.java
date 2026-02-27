package com.range.single;

import com.range.common.dto.GetObjectResponse;
import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.single.config.SingleBunnyNetConfig;
import com.range.single.downloader.SingleBunnyDownloader;

import com.range.single.upload.SingleBunnyUploader;

class SingleBunnyStorageImpl implements SingleBunnyStorage{
    private final SingleBunnyDownloader singleBunnyDownloader;
    private final SingleBunnyUploader singleBunnyUploader;
    public SingleBunnyStorageImpl(SingleBunnyNetConfig singleBunnyNetConfig){
        singleBunnyUploader= SingleBunnyUploader.create(singleBunnyNetConfig);
        singleBunnyDownloader = SingleBunnyDownloader.create(singleBunnyNetConfig);
    }
    @Override
    public GetObjectResponse download(String key) {
        return  singleBunnyDownloader.download(key);
    }

    @Override
    public PutObjectResponse uploadFile(PutObjectRequest putObjectRequest) {
        return singleBunnyUploader.uploadFile(putObjectRequest);
    }

    @Override
    public void delete() {

    }


}
