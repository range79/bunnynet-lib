package com.range;

import com.range.common.dto.GetObjectResponse;
import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.common.enums.Region;
import com.range.config.MultiBunnyNetConfig;
import com.range.delete.MultiBunnyDeleter;
import com.range.downloader.MultiBunnyDownloader;
import com.range.upload.MultiBunnyUploader;

public class MultiBunnyStorageImpl implements MultiBunnyStorage {
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
        return uploader.uploadFileBunny(putObjectRequest, storageZoneName, storageRegion);
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
