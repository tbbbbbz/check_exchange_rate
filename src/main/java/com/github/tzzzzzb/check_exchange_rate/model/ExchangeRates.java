package com.github.tzzzzzb.check_exchange_rate.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ExchangeRates implements Cloneable{
    private String timestamp;
    private String base;
    private Map<String, Float> rates;


    @Override
    public Object clone() {
        ExchangeRates rates;
        try {
            rates = (ExchangeRates) super.clone();
        } catch (CloneNotSupportedException e) {
            rates = new ExchangeRates();
            rates.setTimestamp(this.timestamp);
            rates.setBase(this.base);
            rates.setRates(new HashMap<>(this.rates));
        }
        return rates;
    }
}
