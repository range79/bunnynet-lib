package com.range.single.downloader;

import com.range.common.enums.Region;
import com.range.single.config.SingleBunnyNetConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SingleBunnyDownloaderImplTest {

    @Test
    void constructor_shouldThrowWhenConfigIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new SingleBunnyDownloaderImpl(null, 1000, 1000));
    }

    @Test
    void download_shouldThrowWhenKeyIsNull() {
        SingleBunnyNetConfig config =
                new SingleBunnyNetConfig(
                        "api-key",
                        Region.FRANKFURT_DE,
                        "zone1"
                );

        SingleBunnyDownloaderImpl downloader =
                new SingleBunnyDownloaderImpl(config, 1000, 1000);

        assertThrows(IllegalArgumentException.class,
                () -> downloader.download(null));
    }

    @Test
    void download_shouldThrowWhenKeyIsBlank() {
        SingleBunnyNetConfig config =
                new SingleBunnyNetConfig(
                        "api-key",
                        Region.FRANKFURT_DE,
                        "zone1"
                );

        SingleBunnyDownloaderImpl downloader =
                new SingleBunnyDownloaderImpl(config, 1000, 1000);

        assertThrows(IllegalArgumentException.class,
                () -> downloader.download("   "));
    }
}