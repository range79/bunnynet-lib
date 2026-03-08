package com.range.bunnynet.core.storage.multi;

import com.range.bunnynet.core.model.PutObjectRequest;
import com.range.bunnynet.core.model.PutObjectResponse;
import com.range.bunnynet.core.region.Region;
import com.range.bunnynet.core.http.BunnyHttpClient;
import com.range.bunnynet.core.http.AbstractBunnyUploader;

final class MultiBunnyUploaderImpl extends AbstractBunnyUploader implements MultiBunnyUploader {

    public MultiBunnyUploaderImpl(MultiBunnyNetConfig config, int connectionTimeout) {
        super(new BunnyHttpClient(config.apiKey(), connectionTimeout));
    }

    @Override
    public PutObjectResponse upload(PutObjectRequest request, String storageZone, Region region) {
;
        return internalUpload(request, storageZone, region.getEndpoint());
    }

}