package com.github.tzzzzzb.check_exchange_rate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRates {
    private String timestamp;
    private String base;
    private Map<String, Float> rates;


    public ExchangeRates deepCopy() {
        ExchangeRates clone = new ExchangeRates();
        clone.setTimestamp(timestamp);
        clone.setBase(base);
        if (rates != null) {
            clone.setRates(new HashMap<>(rates));
        }
        return clone;
    }
}
