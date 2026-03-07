package com.range.bunnynet.multi;

import com.range.bunnynet.core.model.PutObjectRequest;
import com.range.bunnynet.core.model.PutObjectResponse;
import com.range.bunnynet.core.region.Region;
import com.range.bunnynet.core.http.BunnyHttpClient;
import com.range.bunnynet.core.AbstractBunnyUploader;

final class MultiBunnyUploaderImpl extends AbstractBunnyUploader implements MultiBunnyUploader {

    public MultiBunnyUploaderImpl(MultiBunnyNetConfig config, int connectionTimeout, int readTimeout) {
        super(new BunnyHttpClient(config.apiKey(), connectionTimeout, readTimeout));
    }

    @Override
    public PutObjectResponse upload(PutObjectRequest request, String storageZone, Region region) {
;
        return internalUpload(request, storageZone, region.getEndpoint());
    }

    @Override
    public PutObjectResponse uploadFileBunnyWithDefaultRegion(PutObjectRequest putObjectRequest, String storageZoneName) {

        return upload(putObjectRequest, storageZoneName, Region.FRANKFURT_DE);
    }
}