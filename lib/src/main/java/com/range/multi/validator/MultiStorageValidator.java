package com.range.multi.validator;

import com.range.common.enums.Region;

public final class MultiStorageValidator {


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