package com.github.tzzzzzb.check_exchange_rate.controller;

import com.github.tzzzzzb.check_exchange_rate.model.ChineseYuanValue;
import com.github.tzzzzzb.check_exchange_rate.sourcefetch.SourceFetcher;
import com.github.tzzzzzb.check_exchange_rate.testconfig.RestTemplateOverrider;
import com.github.tzzzzzb.check_exchange_rate.util.SampleFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class ChineseYuanValueControllerTest {

    @MockBean
    private SourceFetcher sourceFetcher;

    @Autowired
    private ChineseYuanValueController chineseYuanValueController;

    @Autowired
    private RestTemplate restTemplate;

    @LocalServerPort
    private int port;


    @BeforeEach
    void setup() {
        when(sourceFetcher.getCurrentRates()).thenReturn(SampleFactory.getSample());
    }

    @Test
    void contextLoads() {
        assertNotNull(chineseYuanValueController);
    }

    @Test
    void testHttpRequest1() {
        assertResponseIsCorrect(2);
    }

    private void assertResponseIsCorrect(double usd) {
        double expected = usd * SampleFactory.getSample().getRates().get("CNY");

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/" + usd, ChineseYuanValue.class).getValue())
                .isEqualTo(expected);
    }

    @Test
    void testHttpRequest2() {
        assertResponseIsCorrect(0);
    }


    @Test
    void testHttpRequest3() {
        assertResponseIsCorrect(0.01d);
    }


    @Test
    void testHttpRequest4() {
        assertResponseIsCorrect(0.000001d);
    }


    @Test
    void testHttpRequest5() {
        assertResponseIsCorrect(1e18);
    }


    @Test
    void testHttpRequest6() {
        assertResponseIsCorrect(1111111111111.1111654756576567345d);
    }
}