package com.range.autoconfig;

import com.range.SingleBunnyStorage;
import com.range.properties.SingleBunnyNetConfig;
import com.range.properties.SingleBunnyProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(SingleBunnyProperties.class)
@ConditionalOnClass(SingleBunnyStorage.class)
@ConditionalOnProperty(
        prefix = "bunny.client",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class SingleBunnyStorageConfig {

    private final SingleBunnyProperties properties;

    public SingleBunnyStorageConfig(SingleBunnyProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public SingleBunnyNetConfig bunnyNetConfig() {
        return new SingleBunnyNetConfig(
                properties.apiKey(),
                properties.region(),
                properties.storageZone()
        );
    }

    @Bean
    @ConditionalOnMissingBean
    public SingleBunnyStorage singleBunnyStorage(SingleBunnyNetConfig config) {
        return SingleBunnyStorage.create(config);
    }
}