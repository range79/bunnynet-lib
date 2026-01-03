package com.range.config;

/**
 * BunnyNetClient is a simple configuration holder for BunnyCDN Storage API.
 * It contains the API key, storage zone name and selected storage region.
 */
public class BunnyNetClient {
    private final String apiKey;

    /**
     * BunnyNetClient constructor with all parameters.
     * @param apiKey Your BunnyCDN API key used for authentication.
     */

    public BunnyNetClient(
            String apiKey
    ) {
        this.apiKey = apiKey;
    }


    public String getApiKey() {
        return apiKey;
    }

}
