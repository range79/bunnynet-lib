package com.range.multi;

import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.common.enums.Region;
import com.range.multi.config.MultiBunnyNetConfig;

import java.io.InputStream;

public interface MultiBunnyStorage {

    /**
     * Factory method to create a new instance of MultiBunnyStorage.
     */
    static MultiBunnyStorage create(MultiBunnyNetConfig config) {
        return new MultiBunnyStorageImpl(config);
    }

    /**
     * Uploads a file to a specific storage zone and region.
     */
    PutObjectResponse uploadFile(PutObjectRequest putObjectRequest, String storageZoneName, Region storageRegion);

    /**
     * Deletes a file from a specific storage zone and region.
     */
    void deleteFile(String storageZoneName, String key, Region storageRegion);

    /**
     * Downloads a file and returns it as an InputStream.
     */
    InputStream downloadFile(String storageZoneName, String key, Region storageRegion);
}