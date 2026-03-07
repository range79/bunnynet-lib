package com.range.bunnynet.multi;

import com.range.bunnynet.core.region.Region;

public final class MultiStorageValidator {


    public static void validateConfig(MultiBunnyNetConfig config) {
        if (config == null) {
            throw new IllegalArgumentException("MultiBunnyNetConfig cannot be null");
        }
    }

    public static void validate(String storageZoneName, String key, Region region) {

        if (storageZoneName == null || storageZoneName.isBlank()) {
            throw new IllegalArgumentException("Storage zone cannot be null or empty");
        }

        if (key == null || key.isBlank()) {
            throw new IllegalArgumentException("Key cannot be null or empty");
        }

        if (region == null) {
            throw new IllegalArgumentException("Region cannot be null");
        }
    }
}