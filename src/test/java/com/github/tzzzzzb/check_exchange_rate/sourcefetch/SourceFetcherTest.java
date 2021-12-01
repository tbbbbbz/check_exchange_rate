package com.github.tzzzzzb.check_exchange_rate.sourcefetch;

import com.github.tzzzzzb.check_exchange_rate.model.ExchangeRates;
import com.github.tzzzzzb.check_exchange_rate.testconfig.TestConfig;
import com.github.tzzzzzb.check_exchange_rate.util.SampleFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = TestConfig.class)
@ActiveProfiles("test")
class SourceFetcherTest {
    @Autowired
    SourceFetcher sourceFetcher;

    @Autowired
    RestTemplate restTemplate;

    @Value("${source.fetch.interval}")
    int interval;

    @Test
    void contextLoads() {
        assertNotNull(sourceFetcher);
    }

    @Test
    void givenSourceFetcher_whenGetCurrentRates_returnAnExchangeRateObject() {
        ExchangeRates er = sourceFetcher.getCurrentRates();
        assertEquals(SampleFactory.getSample(), er);
    }

    @Test
    void givenSourceUpdated_whenGetCurrentRates_returnTheUpdatedObject() throws InterruptedException {
        ExchangeRates oldER = SampleFactory.getSample();
        ExchangeRates updatedER = updateSourceExchangeRate(SampleFactory.getSample());
        assertTrue(isAbleToGetUpdatedSource(updatedER, oldER));
    }

    private ExchangeRates updateSourceExchangeRate(ExchangeRates old) {
        old.setBase("UPDATED_BASE");
        old.getRates().put("KKK", 4f);
        old.setTimestamp("4353453");
        when(restTemplate.getForObject(anyString(), any())).thenReturn(old);
        return old;
    }


    private boolean isAbleToGetUpdatedSource(ExchangeRates updatedER, ExchangeRates oldER) throws InterruptedException {
        ExchangeRates er;
        er = sourceFetcher.getCurrentRates();
        int i = 0;
        while(i < 100) {
            if (!er.equals(oldER)) {
                break;
            }
            Thread.sleep(interval* 10L);
            er = sourceFetcher.getCurrentRates();
            i++;
        }
        return updatedER.equals(er);
    }

    @Test
    void givenTwoRequests_whenTheyCallGetCurrentRates_thenTheyGotTheSameInfo() {
        ExchangeRates er1, er2;
        er1 = sourceFetcher.getCurrentRates();
        er2 = sourceFetcher.getCurrentRates();
        assertEquals(er1, er2);
    }

    @Test
    void givenMultipleReaders_whenGetCurrentRatesBeforeChange_thenAllGotRightInfo() {
        for (int repeat = 0; repeat < 10; repeat++) {
            AtomicBoolean pass = new AtomicBoolean(true);
            for (int i = 0; i < 20; i++) {
                new Thread(() -> {
                    if (!sourceFetcher.getCurrentRates().equals(sourceFetcher.getCurrentRates())) {
                        pass.set(false);
                    }
                }).start();
            }
            assertTrue(pass.get());
        }
    }


    @Test
    void givenMultipleReaders_whenGetCurrentRatesAfterChange_thenAllGotUpdatedInfo() {
        for (int repeat = 0; repeat < 10; repeat++) {
            resetRestTemplate();

            ExchangeRates oldER = SampleFactory.getSample();
            ExchangeRates updatedER = updateSourceExchangeRate(SampleFactory.getSample());

            AtomicBoolean pass = new AtomicBoolean(true);
            for (int i = 0; i < 20; i++) {
                new Thread(() -> {
                    try {
                        if (!isAbleToGetUpdatedSource(updatedER, oldER)) {
                            pass.set(false);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        pass.set(false);
                    }
                }).start();
            }
            assertTrue(pass.get());
        }
    }

    private void resetRestTemplate() {
        when(restTemplate.getForObject(anyString(), any())).thenReturn(SampleFactory.getSample());
    }

}