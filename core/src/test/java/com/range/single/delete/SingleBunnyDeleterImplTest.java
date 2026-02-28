package com.range.single.delete;

import com.range.common.enums.Region;
import com.range.single.config.SingleBunnyNetConfig;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class SingleBunnyDeleterImplTest {

    @Test
    void constructor_shouldThrowWhenConfigIsNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new SingleBunnyDeleterImpl(null, 1000, 1000));
    }

    @Test
    void delete_shouldThrowWhenKeyIsNull() {
        SingleBunnyNetConfig config =
                new SingleBunnyNetConfig(
                        "api-key",
                        Region.FRANKFURT_DE,
                        "zone1"
                );

        SingleBunnyDeleterImpl deleter =
                new SingleBunnyDeleterImpl(config, 1000, 1000);

        assertThrows(IllegalArgumentException.class,
                () -> deleter.delete(null));
    }

    @Test
    void delete_shouldThrowWhenKeyIsBlank() {
        SingleBunnyNetConfig config =
                new SingleBunnyNetConfig(
                        "api-key",
                        Region.FRANKFURT_DE,
                        "zone1"
                );

        SingleBunnyDeleterImpl deleter =
                new SingleBunnyDeleterImpl(config, 1000, 1000);

        assertThrows(IllegalArgumentException.class,
                () -> deleter.delete("   "));
    }
}