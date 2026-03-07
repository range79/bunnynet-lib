package com.range.bunnynet.core.http;

import com.range.bunnynet.core.model.PutObjectRequest;
import com.range.bunnynet.core.model.PutObjectResponse;
import com.range.bunnynet.core.exception.BunnyFileUploadFailedException;
import com.range.bunnynet.core.exception.BunnyInvalidCredentialsException;
import okhttp3.Request;

public abstract class AbstractBunnyUploader {
    protected final BunnyHttpClient httpClient;

    protected AbstractBunnyUploader(BunnyHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    protected PutObjectResponse internalUpload(PutObjectRequest request, String storageZone, String endpoint) {
        String url = String.format("%s/%s/%s",
                endpoint,
                storageZone,
                request.key()
        );
        Request httpRequest = httpClient.createPutRequest(
                url,
                request.contentType(),
                request.metadata(),
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
                request.key(),
                "https://" + storageZone + ".b-cdn.net/" + request.key()
        );
    }
}