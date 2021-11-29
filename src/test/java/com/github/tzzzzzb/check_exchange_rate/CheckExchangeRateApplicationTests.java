package com.github.tzzzzzb.check_exchange_rate;

import com.github.tzzzzzb.check_exchange_rate.testconfig.TestConfig;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
class CheckExchangeRateApplicationTests {

    @Test
    void contextLoads() {
    }

}
