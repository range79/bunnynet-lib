package com.range.common.enums;

/**
 * Represents the available BunnyCDN storage regions.
 *
 * <p>Each region maps to its corresponding Bunny Storage API endpoint.</p>
 *
 * <p>Example:</p>
 *
 * <pre>
 * Region.LONDON_UK.getEndpoint()
 * // returns: uk.storage.bunnycdn.com
 * </pre>
 *
 * <p>If a custom storage endpoint is required, use {@link Region#CUSTOM}
 * and configure it via {@link #setCustomEndpoint(String)}.</p>
 */
public enum Region {

    FRANKFURT_DE("storage.bunnycdn.com"),
    LONDON_UK("uk.storage.bunnycdn.com"),
    NEW_YORK_US("ny.storage.bunnycdn.com"),
    LOS_ANGELES_US("la.storage.bunnycdn.com"),
    SINGAPORE_SG("sg.storage.bunnycdn.com"),
    STOCKHOLM_SE("se.storage.bunnycdn.com"),
    SAO_PAULO_BR("br.storage.bunnycdn.com"),
    JOHANNESBURG_SA("jh.storage.bunnycdn.com"),

    /**
     * Custom storage endpoint.
     *
     * <p>This region allows users to specify a custom Bunny Storage endpoint.</p>
     */
    CUSTOM(null);

    private volatile String endpoint;

    Region(String endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * Returns the storage endpoint for the region.
     *
     * @return Bunny Storage API endpoint
     * @throws IllegalStateException if CUSTOM region endpoint is not set
     */
    public String getEndpoint() {
        if (this == CUSTOM && endpoint == null) {
            throw new IllegalStateException(
                    "Custom endpoint not configured. Call setCustomEndpoint() first."
            );
        }
        return endpoint;
    }

    /**
     * Sets the endpoint for {@link Region#CUSTOM}.
     *
     * @param endpoint custom Bunny Storage endpoint
     */
    public void setCustomEndpoint(String endpoint) {
        if (this != CUSTOM) {
            throw new UnsupportedOperationException(
                    "Only CUSTOM region allows manual endpoint configuration."
            );
        }

        if (endpoint == null || endpoint.isBlank()) {
            throw new IllegalArgumentException("Endpoint cannot be null or empty.");
        }

        this.endpoint = endpoint;
    }
}