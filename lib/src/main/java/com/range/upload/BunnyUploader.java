package com.range.upload;

import com.range.dto.PutObjectRequest;
import com.range.dto.PutObjectResponse;
import com.range.enums.Region;
import com.range.exception.BunnyFileUploadFailedException;
import com.range.exception.BunnyInvalidCredentialsException;

public interface BunnyUploader {
    /**
     * Uploads a file to a specific Bunny.net Storage Zone.
     *
     * @param putObjectRequest The request object containing the file data (InputStream),
     *                         target path (key), and content type.
     * @param storageZoneName  The unique name of your Storage Zone (acts as the username).
     * @param storageRegion    The geographical region where the storage zone is located.
     * @return {@link PutObjectResponse} containing the upload metadata and the public file URL.
     * @throws BunnyFileUploadFailedException if the upload fails due to network issues, or server errors.
     * @throws BunnyInvalidCredentialsException if invalid credentials
     */
    PutObjectResponse uploadFileBunny(PutObjectRequest putObjectRequest,
                                      String storageZoneName,
                                      Region storageRegion)
            throws BunnyFileUploadFailedException, BunnyInvalidCredentialsException;
}
