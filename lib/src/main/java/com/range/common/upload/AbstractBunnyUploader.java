package com.range.common.upload;

import com.range.common.dto.PutObjectRequest;
import com.range.common.dto.PutObjectResponse;
import com.range.common.exception.BunnyFileUploadFailedException;
import com.range.common.exception.BunnyInvalidCredentialsException;
import com.range.common.http.BunnyHttpClient;
import okhttp3.Request;

public abstract class AbstractBunnyUploader {
    protected final BunnyHttpClient httpClient;

    protected AbstractBunnyUploader(BunnyHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    protected PutObjectResponse internalUpload(PutObjectRequest request, String storageZone, String endpoint) {
        String url = endpoint + "/" + storageZone + "/" + request.getKey();

        Request httpRequest = httpClient.createPutRequest(
                url,
                request.getContentType(),
                request.getMetadata(),
                request
        );

        int code = httpClient.executeUpload(httpRequest);

        if (code == 401) {
            throw new BunnyInvalidCredentialsException("Invalid AccessKey or credentials.");
        }
        if (code == 400) {
            throw new BunnyFileUploadFailedException("The file was uploaded unsuccessfully (Bad Request).");
        }
        if (code != 200 && code != 201) {
            throw new BunnyFileUploadFailedException("BunnyCDN upload failed: HTTP " + code);
        }

        return new PutObjectResponse(
                storageZone,
                request.getKey(),
                "https://" + storageZone + ".b-cdn.net/" + request.getKey()
        );
    }
}