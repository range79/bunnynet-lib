package com.range.multi.config;

/**
 * MultiBunnyNetClient is a lightweight configuration holder for the BunnyCDN Storage API.
 * <p>
 * It stores only the API key and is designed for scenarios where you need to upload
 * files to multiple storage zones or regions using the same API key.
 * <p>
 * Use this class when:
 * - You manage multiple BunnyCDN storage zones.
 * - You upload to different regions but share a single API key.
 * - You want a simple, reusable configuration object for various uploader implementations.
 */
public record MultiBunnyNetClient(String apiKey) {
    /**
     * Creates a new MultiBunnyNetClient instance.
     *
     * @param apiKey The BunnyCDN API key used for authentication.
     */
    public MultiBunnyNetClient {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("API key cannot be null or empty");
        }
    }

    /**
     * @return The stored BunnyCDN API key.
     */
    @Override
    public String apiKey() {
        return apiKey;
    }

}
