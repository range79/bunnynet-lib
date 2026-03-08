package com.range.bunnynet.spring.single;

import com.range.bunnynet.core.region.Region;
import org.springframework.boot.context.properties.ConfigurationProperties;


@ConfigurationProperties(prefix = "bunnynet.single")
public record SingleBunnyProperties(
        boolean enabled,
        String apiKey,
        Region region,
        String storageZone
) {
}