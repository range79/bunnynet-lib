package com.range.bunnynet.spring.single;

import com.range.bunnynet.core.region.Region;
import org.springframework.boot.context.properties.ConfigurationProperties;



@ConfigurationProperties(prefix = "single.bunny")
public record SingleBunnyProperties (
    String apiKey,
    Region region,
    String storageZone
){

}
