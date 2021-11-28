package com.github.tzzzzzb.check_exchange_rate.sourcefetch;

import com.github.tzzzzzb.check_exchange_rate.model.ExchangeRates;
import org.springframework.stereotype.Component;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import static java.util.concurrent.TimeUnit.HOURS;

@Component
public class SourceFetcher {
    private final ExchangeRates currentRates;
    private final Lock writeLock;
    private final Lock readLock;

    public SourceFetcher() {
        currentRates = new ExchangeRates();
        ReadWriteLock lock = new ReentrantReadWriteLock();
        writeLock = lock.writeLock();
        readLock = lock.readLock();

        updateCurrentRates();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(this::updateCurrentRates, 1, 1, HOURS);
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
