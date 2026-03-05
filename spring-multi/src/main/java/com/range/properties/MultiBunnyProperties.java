package com.range.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "multi.bunny")
public record MultiBunnyProperties (
        String apiKey
){
}