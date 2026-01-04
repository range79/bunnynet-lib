package com.range.upload;

import com.range.config.MultiBunnyNetClient;
import com.range.dto.PutObjectRequest;
import com.range.dto.PutObjectResponse;
import com.range.enums.Region;
import com.range.exception.BunnyFileUploadFailedException;
import com.range.exception.BunnyInvalidCredentialsException;


public interface MultiBunnyUploader {

    static MultiBunnyUploader create(MultiBunnyNetClient multiBunnyNetClient, int connectionTimeOut, int connectionReadTimeOut) {
        return new MultiBunnyUploaderImpl(multiBunnyNetClient, connectionTimeOut, connectionReadTimeOut);
    }

    static MultiBunnyUploader create(MultiBunnyNetClient multiBunnyNetClient) {
        return new MultiBunnyUploaderImpl(multiBunnyNetClient, 15_000, 60_000);
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
