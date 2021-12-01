package com.github.tzzzzzb.check_exchange_rate.testconfig;

import com.github.tzzzzzb.check_exchange_rate.util.SampleFactory;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@TestConfiguration
@Profile("test")
public class TestConfig {
    @Bean
    RestTemplate restTemplate() {
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        when(restTemplate.getForObject(anyString(), any())).thenReturn(SampleFactory.getSample());
        return restTemplate;
    }
}
