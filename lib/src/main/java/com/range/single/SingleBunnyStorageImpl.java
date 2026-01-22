package com.range.single;

import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.single.config.SingleBunnyNetConfig;
import com.range.single.downloader.SingleBunnyDownloader;
import com.range.single.dto.SingleDownloadObjectRequest;
import com.range.single.upload.SingleBunnyUploader;

import java.io.InputStream;

class SingleBunnyStorageImpl implements SingleBunnyStorage{
    private final SingleBunnyDownloader singleBunnyDownloader;
    private final SingleBunnyUploader singleBunnyUploader;
    public SingleBunnyStorageImpl(SingleBunnyNetConfig singleBunnyNetConfig){
        singleBunnyUploader= SingleBunnyUploader.create(singleBunnyNetConfig);
        singleBunnyDownloader = SingleBunnyDownloader.create(singleBunnyNetConfig);
    }
    @Override
    public InputStream download(SingleDownloadObjectRequest singleDownloadObjectRequest) {
        return  singleBunnyDownloader.download(singleDownloadObjectRequest);
    }

    @Override
    public PutObjectResponse uploadFile(PutObjectRequest putObjectRequest) {
        return singleBunnyUploader.uploadFile(putObjectRequest);
    }


}
