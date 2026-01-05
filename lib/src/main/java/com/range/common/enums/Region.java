package com.range.common.enums;

/**
 * Represents the available BunnyCDN storage regions.
 * <p>
 * Each enum constant maps to its corresponding storage endpoint.
 * For example, selecting {@link Region#LONDON_UK} will use:
 * <pre>uk.storage.bunnycdn.com</pre>
 * </p>
 *
 * <p>
 * If you need to define a custom storage endpoint that is not listed,
 * use {@link Region#CUSTOM} and set the endpoint manually via:
 * <pre>Region.CUSTOM.setCustomEndpoint("my.custom.host");</pre>
 * </p>
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

    CUSTOM(null);

    private String endpoint;

    Region(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setCustomEndpoint(String endpoint) {
        if (this != CUSTOM) {
            throw new UnsupportedOperationException("Only CUSTOM region can set endpoint manually.");
        }
        this.endpoint = endpoint;
    }
}
