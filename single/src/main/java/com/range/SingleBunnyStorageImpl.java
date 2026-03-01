package com.range;

import com.range.common.dto.GetObjectResponse;
import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.config.SingleBunnyNetConfig;
import com.range.delete.SingleBunnyDeleter;
import com.range.downloader.SingleBunnyDownloader;
import com.range.upload.SingleBunnyUploader;

class SingleBunnyStorageImpl implements SingleBunnyStorage{
    private final SingleBunnyDownloader singleBunnyDownloader;
    private final SingleBunnyUploader singleBunnyUploader;
    private final SingleBunnyDeleter singleBunnyDeleter;
    public SingleBunnyStorageImpl(SingleBunnyNetConfig singleBunnyNetConfig){
        singleBunnyUploader= SingleBunnyUploader.create(singleBunnyNetConfig);
        singleBunnyDownloader = SingleBunnyDownloader.create(singleBunnyNetConfig);
        singleBunnyDeleter=SingleBunnyDeleter.create(singleBunnyNetConfig);
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
    public void delete(String key) {
        singleBunnyDeleter.delete(key);

    }


}
