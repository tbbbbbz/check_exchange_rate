package com.github.tzzzzzb.check_exchange_rate.sourcefetch;

import com.github.tzzzzzb.check_exchange_rate.model.ExchangeRates;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.concurrent.TimeUnit.HOURS;

@Component
public class SourceFetcher {
    private ExchangeRates currentRates;
    private final Lock writeLock;
    private final Lock readLock;
    private final RestTemplate restTemplate;
    private final SourceFetchProperties properties;

    public SourceFetcher(RestTemplate restTemplate, SourceFetchProperties properties) {
        currentRates = new ExchangeRates();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        writeLock = lock.writeLock();
        readLock = lock.readLock();
        this.restTemplate = restTemplate;
        this.properties = properties;

        updateCurrentRates();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::updateCurrentRates, properties.getInterval(), properties.getInterval(), HOURS);
    }

    private void updateCurrentRates() {
        try {
            writeLock.lock();
            currentRates = restTemplate.getForObject(properties.getUrl()
                            + "latest.json?app_id=" + properties.getAppID(),
                    ExchangeRates.class);
        }
        finally {
            writeLock.unlock();
        }
    }

    public ExchangeRates getCurrentRates() {
        try {
            readLock.lock();
            return (ExchangeRates) currentRates.clone();
        }
        finally {
            readLock.unlock();
        }
    }
}
