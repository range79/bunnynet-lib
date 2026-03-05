package com.range.properties;

import com.range.common.enums.Region;
import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties(prefix = "single.bunny")
public record SingleBunnyProperties (
    String apiKey,
    Region region,
    String storageZone
){

}
