package com.github.tzzzzzb.check_exchange_rate.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document("exchange_rates")
@Data
public class ExchangeRates implements Cloneable{
    @Id
    private String date;
    private Map<String, Float> currencyPriceMapping;


    @Override
    public Object clone() {
        ExchangeRates rates;
        try {
            rates = (ExchangeRates) super.clone();
        } catch (CloneNotSupportedException e) {
            rates = new ExchangeRates();
            rates.setDate(this.date);
            rates.setCurrencyPriceMapping(new HashMap<>(currencyPriceMapping));
        }
        return rates;
    }
}
