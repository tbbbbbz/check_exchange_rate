package com.github.tzzzzzb.check_exchange_rate.configuration;

import com.github.tzzzzzb.check_exchange_rate.testconfig.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
class RestTemplateConfigTest {
    RestTemplateConfig config;

    @Autowired
    RestTemplateBuilder builder;

    @Test
    void contextLoads() {
        config = new RestTemplateConfig();
        assertNotNull(builder);
        assertNotNull(config.restTemplate(builder));
    }
}