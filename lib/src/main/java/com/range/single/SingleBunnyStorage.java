package com.range.single;

import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.single.config.SingleBunnyNetConfig;
import com.range.single.dto.SingleDownloadObjectRequest;

import java.io.InputStream;

public interface SingleBunnyStorage {
    static SingleBunnyStorage create(SingleBunnyNetConfig singleBunnyNetConfig){
        return new SingleBunnyStorageImpl(singleBunnyNetConfig);
    }

    InputStream download(SingleDownloadObjectRequest singleDownloadObjectRequest);
    PutObjectResponse uploadFile(PutObjectRequest putObjectRequest);
   void delete();



}
