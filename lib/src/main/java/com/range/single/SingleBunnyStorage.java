package com.range.single;

import com.range.common.dto.GetObjectResponse;
import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.single.config.SingleBunnyNetConfig;

public interface SingleBunnyStorage {
    static SingleBunnyStorage create(SingleBunnyNetConfig singleBunnyNetConfig){
        return new SingleBunnyStorageImpl(singleBunnyNetConfig);
    }

    GetObjectResponse download(String key);
    PutObjectResponse uploadFile(PutObjectRequest putObjectRequest);
   void delete(String key);



}
