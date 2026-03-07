package com.range.bunnynet.spring.multi;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "multi.bunny")
public record MultiBunnyProperties (
        String apiKey
){
}