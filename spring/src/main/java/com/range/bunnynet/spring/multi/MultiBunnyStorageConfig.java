package com.range.bunnynet.spring.multi;

import com.range.bunnynet.core.storage.multi.MultiBunnyStorage;
import com.range.bunnynet.core.storage.multi.MultiBunnyNetConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MultiBunnyProperties.class)
@ConditionalOnClass(MultiBunnyStorage.class)
@ConditionalOnProperty(
        prefix = "multi.bunny.client",
        name = "enabled",
        havingValue = "true",
        matchIfMissing = true
)
public class MultiBunnyStorageConfig {

    private final MultiBunnyProperties properties;

    public MultiBunnyStorageConfig(MultiBunnyProperties properties) {
        this.properties = properties;
    }

    @Bean
    @ConditionalOnMissingBean
    public MultiBunnyNetConfig multiBunnyNetConfig() {
        return new MultiBunnyNetConfig(properties.apiKey());
    }

    @Bean
    @ConditionalOnMissingBean
    public MultiBunnyStorage multiBunnyStorage(MultiBunnyNetConfig config) {
        return MultiBunnyStorage.create(config);
    }
}