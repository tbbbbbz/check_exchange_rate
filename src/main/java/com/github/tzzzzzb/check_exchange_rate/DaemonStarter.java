package com.github.tzzzzzb.check_exchange_rate;

import com.github.tzzzzzb.check_exchange_rate.sourcefetch.SourceFetcherConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaemonStarter implements CommandLineRunner {
    private final SourceFetcherConfig sourceFetcher;

    @Autowired
    public DaemonStarter(SourceFetcherConfig sourceFetcher) {
        this.sourceFetcher = sourceFetcher;
    }

    @Override
    public void run(String... args) throws Exception {
        sourceFetcher.updateCurrentRatesPeriodically();
    }
}
