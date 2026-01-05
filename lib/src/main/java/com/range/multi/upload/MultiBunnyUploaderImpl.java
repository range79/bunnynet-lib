package com.range.multi.upload;

import com.range.common.http.BunnyHttpClient;
import com.range.multi.config.MultiBunnyNetClient;
import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.common.enums.Region;
import com.range.common.exception.BunnyFileUploadFailedException;
import com.range.common.exception.BunnyInvalidCredentialsException;

class MultiBunnyUploaderImpl implements MultiBunnyUploader {
    private final BunnyHttpClient httpClient;


    public MultiBunnyUploaderImpl(MultiBunnyNetClient multiBunnyNetClient, int connectionTimeout, int readTimeout) {
        this.httpClient = new BunnyHttpClient(multiBunnyNetClient.getApiKey(), connectionTimeout, readTimeout);
    }


    @Override
    public PutObjectResponse uploadFileBunny(PutObjectRequest request, String storageZone, Region region) {
        String url = region.getEndpoint() + "/" + storageZone + "/" + request.getKey();
        var connection = httpClient.createPutConnection(url, request.getContentType(), request.getMetadata());

        httpClient.uploadData(connection, request);
        int code = httpClient.getResponseCode(connection);

        if (code == 401) throw new BunnyInvalidCredentialsException(null);

        if (code != 200 && code != 201) {
            throw new BunnyFileUploadFailedException("BunnyCDN upload failed: HTTP " + code);
        }
        return new PutObjectResponse(storageZone, request.getKey(), "https://" + storageZone + ".b-cdn.net/" + request.getKey());
    }


    @Override
    public PutObjectResponse uploadFileBunnyWithDefaultRegion(PutObjectRequest putObjectRequest, String storageZoneName) {
        try {
            return uploadFileBunny(putObjectRequest, storageZoneName, Region.FRANKFURT_DE);
        } catch (BunnyFileUploadFailedException ex) {
            throw new BunnyFileUploadFailedException("Upload failed", ex);
        }
    }
}
