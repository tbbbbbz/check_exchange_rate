package com.github.tzzzzzb.check_exchange_rate.sourcefetch;

import com.github.tzzzzzb.check_exchange_rate.model.ExchangeRates;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@Configuration
@EnableAsync
public class SourceFetcherConfig {
    private final ExchangeRates currentRates;
    private final Lock writeLock;
    private final Lock readLock;

    public SourceFetcherConfig() {
        this.currentRates = new ExchangeRates();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        writeLock = lock.writeLock();
        readLock = lock.readLock();
    }

    @Async
    public void updateCurrentRatesPeriodically() {
        updateCurrentRates();
        //TODO: run updates repeatedly
    }

    private void updateCurrentRates() {
        try {
            writeLock.lock();
            //TODO:update currentRates
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
