package com.range.multi.upload;

import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.common.enums.Region;
import com.range.common.http.BunnyHttpClient;
import com.range.common.upload.AbstractBunnyUploader;
import com.range.multi.config.MultiBunnyNetConfig;

public class MultiBunnyUploaderImpl extends AbstractBunnyUploader implements MultiBunnyUploader {

    public MultiBunnyUploaderImpl(MultiBunnyNetConfig config, int connectionTimeout, int readTimeout) {
        super(new BunnyHttpClient(config.apiKey(), connectionTimeout, readTimeout));
    }

    @Override
    public PutObjectResponse uploadFileBunny(PutObjectRequest request, String storageZone, Region region) {
        return internalUpload(request, storageZone, region.getEndpoint());
    }

    @Override
    public PutObjectResponse uploadFileBunnyWithDefaultRegion(PutObjectRequest putObjectRequest, String storageZoneName) {
        return uploadFileBunny(putObjectRequest, storageZoneName, Region.FRANKFURT_DE);
    }
}