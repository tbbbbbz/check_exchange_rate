package com.github.tzzzzzb.check_exchange_rate.sourcefetch;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "source.fetch")
@Setter
@Getter
public class SourceFetchProperties {
    private int interval;
    private String url;
    private String appID;
}
