package com.range.single.upload;

import com.range.single.config.SingleBunnyNetConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SingleBunnyUploaderTest {

    @Test
    void create_shouldThrowWhenConfigIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> SingleBunnyUploader.create(null));
    }

    @Test
    void createWithTimeout_shouldThrowWhenConfigIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> SingleBunnyUploader.create(null, 1000, 1000));
    }

    @Test
    void createWithTimeout_shouldThrowWhenConnectionTimeoutIsZeroOrNegative() {
        SingleBunnyNetConfig config = dummyConfig();
        assertThrows(IllegalArgumentException.class,
                () -> SingleBunnyUploader.create(config, 0, 1000));
        assertThrows(IllegalArgumentException.class,
                () -> SingleBunnyUploader.create(config, -1, 1000));
    }

    @Test
    void createWithTimeout_shouldThrowWhenReadTimeoutIsZeroOrNegative() {
        SingleBunnyNetConfig config = dummyConfig();
        assertThrows(IllegalArgumentException.class,
                () -> SingleBunnyUploader.create(config, 1000, 0));
        assertThrows(IllegalArgumentException.class,
                () -> SingleBunnyUploader.create(config, 1000, -1));
    }

    private SingleBunnyNetConfig dummyConfig() {
        return new SingleBunnyNetConfig(
                "api-key",
                com.range.common.enums.Region.FRANKFURT_DE,
                "zone1"
        );
    }
}