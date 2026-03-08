package com.range.bunnynet.spring.multi;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "bunnynet.multi")
public record MultiBunnyProperties(
        boolean enabled,
        String apiKey
) {
}