package com.range.common.download;

import com.range.common.dto.GetObjectResponse;
import com.range.common.exception.BunnyFileDownloadFailedException;
import com.range.common.exception.BunnyInvalidCredentialsException;
import com.range.common.http.BunnyHttpClient;

public abstract class AbstractBunnyDownloader {

    protected final BunnyHttpClient httpClient;

    protected AbstractBunnyDownloader(BunnyHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    protected GetObjectResponse internalDownload(
            String storageZone,
            String endpoint,
            String key
    ) {

        GetObjectResponse response =
                httpClient.downloadObject(storageZone, endpoint, key);

        int status = response.getHttpStatus();

        if (status == 401) {
            response.close();
            throw new BunnyInvalidCredentialsException(
                    "Invalid AccessKey or credentials."
            );
        }

        if (status == 404) {
            response.close();
            throw new BunnyFileDownloadFailedException(
                    "Object not found: " + key
            );
        }

        if (status >= 400 && status < 500) {
            response.close();
            throw new BunnyFileDownloadFailedException(
                    "Client error during download. HTTP " + status
            );
        }

        if (status >= 500) {
            response.close();
            throw new BunnyFileDownloadFailedException(
                    "Server error during download. HTTP " + status
            );
        }

        if (status != 200 && status != 206) {
            response.close();
            throw new BunnyFileDownloadFailedException(
                    "Unexpected download response. HTTP " + status
            );
        }

        return response;
    }
}