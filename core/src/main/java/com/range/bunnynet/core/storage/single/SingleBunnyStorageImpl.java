package com.range.bunnynet.core.storage.single;

import com.range.bunnynet.core.model.GetObjectResponse;
import com.range.bunnynet.core.model.PutObjectRequest;
import com.range.bunnynet.core.model.PutObjectResponse;

final class SingleBunnyStorageImpl implements SingleBunnyStorage{
    private final SingleBunnyDownloader singleBunnyDownloader;
    private final SingleBunnyUploader singleBunnyUploader;
    private final SingleBunnyDeleter singleBunnyDeleter;
    SingleBunnyStorageImpl(SingleBunnyNetConfig singleBunnyNetConfig){
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
