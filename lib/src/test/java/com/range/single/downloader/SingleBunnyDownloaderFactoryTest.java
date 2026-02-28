package com.range.single.downloader;

import com.range.single.config.SingleBunnyNetConfig;
import com.range.common.enums.Region;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SingleBunnyDownloaderFactoryTest {

    @Test
    void create_shouldThrowWhenConfigIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> SingleBunnyDownloader.create(null));
    }

    @Test
    void createWithTimeout_shouldThrowWhenConfigIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> SingleBunnyDownloader.create(null, 1000, 1000));
    }

    @Test
    void createWithTimeout_shouldThrowWhenConnectionTimeoutIsZeroOrNegative() {
        SingleBunnyNetConfig config = dummyConfig();

        assertThrows(IllegalArgumentException.class,
                () -> SingleBunnyDownloader.create(config, 0, 1000));

        assertThrows(IllegalArgumentException.class,
                () -> SingleBunnyDownloader.create(config, -1, 1000));
    }

    @Test
    void createWithTimeout_shouldThrowWhenReadTimeoutIsZeroOrNegative() {
        SingleBunnyNetConfig config = dummyConfig();

        assertThrows(IllegalArgumentException.class,
                () -> SingleBunnyDownloader.create(config, 1000, 0));

        assertThrows(IllegalArgumentException.class,
                () -> SingleBunnyDownloader.create(config, 1000, -1));
    }

    private SingleBunnyNetConfig dummyConfig() {
        return new SingleBunnyNetConfig(
                "api-key",
                Region.FRANKFURT_DE,
                "zone1"
        );
    }
}