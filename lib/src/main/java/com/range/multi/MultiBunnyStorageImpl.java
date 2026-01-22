package com.range.multi;

import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.common.enums.Region;
import com.range.multi.config.MultiBunnyNetConfig;
import com.range.multi.downloader.MultiBunnyDownloader;
import com.range.multi.upload.MultiBunnyUploader;

import java.io.InputStream;

public class MultiBunnyStorageImpl implements MultiBunnyStorage {
    private final MultiBunnyUploader uploader;
    private final MultiBunnyDownloader downloader;

    //private final MultiBunnyDelete delete;
    public MultiBunnyStorageImpl(MultiBunnyNetConfig config) {
        this.uploader = MultiBunnyUploader.create(config);
        this.downloader = MultiBunnyDownloader.create(config);
        //    this.delete=MultiBunnyDelete;
    }

    public PutObjectResponse uploadFile(PutObjectRequest putObjectRequest, String storageZoneName,
                                        Region storageRegion) {
        return uploader.uploadFileBunny(putObjectRequest, storageZoneName, storageRegion);
    }

    @Override
    public void deleteFile(String storageZoneName, String key, Region storageRegion) {

    }

    @Override
    public InputStream downloadFile(String storageZoneName, String key, Region storageRegion) {

        return downloader.download(storageZoneName,key,storageRegion) ;
    }

}
