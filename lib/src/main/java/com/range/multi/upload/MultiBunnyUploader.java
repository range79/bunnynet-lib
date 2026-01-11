package com.range.multi.upload;

import com.range.multi.config.MultiBunnyNetConfig;
import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.common.enums.Region;
import com.range.common.exception.BunnyFileUploadFailedException;
import com.range.common.exception.BunnyInvalidCredentialsException;


public interface MultiBunnyUploader {
    /**
     * Creates a SingleBunnyUploader with custom timeout settings.
     * @param multiBunnyNetConfig The configuration containing API key.
     * @param connectionTimeout    Maximum time in milliseconds to wait for establishing the connection.
     * @param connectionReadTimeout          Maximum time in milliseconds to wait for reading data from the connection.
     */

    static MultiBunnyUploader create(MultiBunnyNetConfig multiBunnyNetConfig, int connectionTimeout, int connectionReadTimeout) {
        return new MultiBunnyUploaderImpl(multiBunnyNetConfig, connectionTimeout, connectionReadTimeout);
    }


    /**
     * Creates a SingleBunnyUploader with custom timeout settings.\
     * @param multiBunnyNetConfig The configuration containing API key, storage zone, and region.
     */

    static MultiBunnyUploader create(MultiBunnyNetConfig multiBunnyNetConfig) {
        return new MultiBunnyUploaderImpl(multiBunnyNetConfig, 15_000, 60_000);
    }

    /**
     * Uploads a file to a specific Bunny.net Storage Zone.
     *
     * @param putObjectRequest The request object containing the file data (InputStream),
     *                         target path (key), and content type.
     * @param storageZoneName  The unique name of your Storage Zone (acts as the username).
     * @param storageRegion    The geographical region where the storage zone is located.
     * @return {@link PutObjectResponse} containing the upload metadata and the public file URL.
     * @throws BunnyFileUploadFailedException if the upload fails due to network issues, or server errors.
     */
    PutObjectResponse uploadFileBunny(PutObjectRequest putObjectRequest,
                                      String storageZoneName,
                                      Region storageRegion)
            throws BunnyFileUploadFailedException;

    /**
     * Uploads a file to a specific Bunny.net Storage Zone.
     *
     * @param putObjectRequest The request object containing the file data (InputStream),
     *                         target path (key), and content type.
     * @param storageZoneName  The unique name of your Storage Zone (acts as the username).
     *                         storageRegion  is default selected.FRANKFURT_DE
     * @return {@link PutObjectResponse} containing the upload metadata and the public file URL.
     * @throws BunnyFileUploadFailedException   if the upload fails due to network issues, or server errors.
     * @throws BunnyInvalidCredentialsException if invalid credentials
     */
    PutObjectResponse uploadFileBunnyWithDefaultRegion(PutObjectRequest putObjectRequest,
                                                       String storageZoneName)
            throws BunnyFileUploadFailedException;
}
