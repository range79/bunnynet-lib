package com.range.upload;

import com.range.dto.PutObjectRequest;
import com.range.dto.PutObjectResponse;
import com.range.enums.Region;

public interface BunnyUploader {

    /**
     * Uploads a file to BunnyCDN.
     *
     * @param putObjectRequest The request containing all required information:
     *                         bucket name, key (file path), content type, metadata, and file data as InputStream.
     * @return {@link PutObjectResponse} containing the bucket, key, and public URL of the uploaded file.
     * @throws RuntimeException if the upload fails due to network, authentication, or other errors.
     */
    PutObjectResponse uploadFileBunny(PutObjectRequest putObjectRequest);
}
