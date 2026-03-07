package com.range.bunnynet.multi;

/**
 * Configuration holder for multi-zone Bunny.net operations.
 *
 * <p>This configuration is intended for use cases where multiple storage zones
 * or regions are used with a single shared API key.</p>
 *
 * <p>Unlike the single-zone configuration, this class does not bind
 * a fixed storage zone or region. The storage zone and region must be
 * provided at operation time.</p>
 *
 * @param apiKey the Bunny.net API key used for authentication;
 *               must not be null or blank
 *
 * @since 2.1.0
 */
public record MultiBunnyNetConfig(String apiKey) {

    /**
     * Canonical constructor with validation.
     *
     * @throws IllegalArgumentException if {@code apiKey} is null or blank
     */
    public MultiBunnyNetConfig {
        if (apiKey == null || apiKey.isBlank()) {
            throw new IllegalArgumentException("API key cannot be null or empty");
        }
    }

    /**
     * Returns a masked string representation to prevent accidental
     * exposure of the API key in logs.
     *
     * @return a safe string representation of this configuration
     */
    @Override
    public String toString() {
        return "MultiBunnyNetConfig[apiKey=****]";
    }
}