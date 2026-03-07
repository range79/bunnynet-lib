package com.range.bunnynet.core.http;

import com.range.bunnynet.core.exception.BunnyFileDeleteFailedException;
import com.range.bunnynet.core.exception.BunnyInvalidCredentialsException;

public abstract class AbstractBunnyDeleter {

    protected final BunnyHttpClient httpClient;

    protected AbstractBunnyDeleter(BunnyHttpClient httpClient) {
        this.httpClient = httpClient;
    }

    protected void internalDelete(
            String storageZone,
            String endpoint,
            String key
    ) {

        int status = httpClient.deleteObject(storageZone, endpoint, key);

        if (status == 401) {
            throw new BunnyInvalidCredentialsException("Invalid AccessKey.");
        }

        if (status == 404) {
            throw new BunnyFileDeleteFailedException(
                    "Object not found: " + key
            );
        }

        if (status >= 400 && status < 500) {
            throw new BunnyFileDeleteFailedException(
                    "Client error during delete. HTTP " + status
            );
        }

        if (status >= 500) {
            throw new BunnyFileDeleteFailedException(
                    "Server error during delete. HTTP " + status
            );
        }

        if (status != 200 && status != 204) {
            throw new BunnyFileDeleteFailedException(
                    "Unexpected delete response. HTTP " + status
            );
        }
    }
}