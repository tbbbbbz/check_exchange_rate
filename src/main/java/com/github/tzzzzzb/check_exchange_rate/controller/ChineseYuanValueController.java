package com.github.tzzzzzb.check_exchange_rate.controller;

import com.github.tzzzzzb.check_exchange_rate.model.ChineseYuanValue;
import com.github.tzzzzzb.check_exchange_rate.model.ExchangeRates;
import com.github.tzzzzzb.check_exchange_rate.sourcefetch.SourceFetcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ChineseYuanValueController {
    SourceFetcher sourceFetcher;

    ChineseYuanValueController(SourceFetcher sourceFetcher) {
        this.sourceFetcher = sourceFetcher;
    }

    @GetMapping("/{usd}")
    ChineseYuanValue convertedValueFromUSDToCNY(@PathVariable double usd) {
        ExchangeRates currentRate = sourceFetcher.getCurrentRates();
        assert currentRate.getRates().containsKey("USD") : "USD is not in rates";
        assert currentRate.getRates().containsKey("CNY") : "CNY is not in rates";
        ChineseYuanValue res = new ChineseYuanValue();
        res.setValue(usd * currentRate.getRates().get("CNY") / currentRate.getRates().get("USD"));
        return res;
    }
}
