package com.range.common.region;

import java.util.Objects;

/**
 * Represents a BunnyCDN storage region.
 *
 * <p>This class is extensible, allowing users to define custom regions.</p>
 *
 * <p>Example:</p>
 *
 * <pre>
 * Region region = Region.LONDON_UK;
 * region.getEndpoint(); // uk.storage.bunnycdn.com
 *
 * Region custom = new Region("my.storage.endpoint");
 * </pre>
 */
public final class Region {

    public static final Region FRANKFURT_DE =
            new Region("storage.bunnycdn.com");

    public static final Region LONDON_UK =
            new Region("uk.storage.bunnycdn.com");

    public static final Region NEW_YORK_US =
            new Region("ny.storage.bunnycdn.com");

    public static final Region LOS_ANGELES_US =
            new Region("la.storage.bunnycdn.com");

    public static final Region SINGAPORE_SG =
            new Region("sg.storage.bunnycdn.com");

    public static final Region STOCKHOLM_SE =
            new Region("se.storage.bunnycdn.com");

    public static final Region SAO_PAULO_BR =
            new Region("br.storage.bunnycdn.com");

    public static final Region JOHANNESBURG_SA =
            new Region("jh.storage.bunnycdn.com");

    private final String endpoint;

    public Region(String endpoint) {
        if (endpoint == null || endpoint.isBlank()) {
            throw new IllegalArgumentException("Endpoint cannot be null or empty.");
        }
        this.endpoint = endpoint;
    }

    /**
     * Returns the Bunny Storage API endpoint.
     */
    public String getEndpoint() {
        return endpoint;
    }

    @Override
    public String toString() {
        return endpoint;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region)) return false;
        Region region = (Region) o;
        return endpoint.equals(region.endpoint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(endpoint);
    }
}