package com.range.bunnynet.multi;

import com.range.bunnynet.core.model.GetObjectResponse;
import com.range.bunnynet.core.model.PutObjectRequest;
import com.range.bunnynet.core.model.PutObjectResponse;
import com.range.bunnynet.core.region.Region;

final class MultiBunnyStorageImpl implements MultiBunnyStorage {
    private final MultiBunnyUploader uploader;
    private final MultiBunnyDownloader downloader;

  private final MultiBunnyDeleter deleter;
    public MultiBunnyStorageImpl(MultiBunnyNetConfig config) {
        this.uploader = MultiBunnyUploader.create(config);
        this.downloader = MultiBunnyDownloader.create(config);
        this.deleter =MultiBunnyDeleter.create(config);
    }

    public PutObjectResponse uploadFile(PutObjectRequest putObjectRequest, String storageZoneName,
                                        Region storageRegion) {
        return uploader.upload(putObjectRequest, storageZoneName, storageRegion);
    }

    @Override
    public void deleteFile(String storageZoneName, String key, Region storageRegion) {
        deleter.delete(storageZoneName, key, storageRegion);

    }

    @Override
    public GetObjectResponse downloadFile(String storageZoneName, String key, Region storageRegion) {

        return downloader.download(storageZoneName,key,storageRegion) ;
    }

}
