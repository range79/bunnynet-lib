package com.range.multi.config;

/**
 * MultiBunnyNetClient is a lightweight configuration holder for the BunnyCDN Storage API.
 *
 * It stores only the API key and is designed for scenarios where you need to upload
 * files to multiple storage zones or regions using the same API key.
 *
 * Use this class when:
 *  - You manage multiple BunnyCDN storage zones.
 *  - You upload to different regions but share a single API key.
 *  - You want a simple, reusable configuration object for various uploader implementations.
 */
public class MultiBunnyNetClient {
    private final String apiKey;

    /**
     * Creates a new MultiBunnyNetClient instance.
     *
     * @param apiKey The BunnyCDN API key used for authentication.
     */
    public MultiBunnyNetClient(String apiKey) {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("API key cannot be null or empty");
        }
        this.apiKey = apiKey;
    }

    /**
     * @return The stored BunnyCDN API key.
     */
    public String getApiKey() {
        return apiKey;
    }

}
