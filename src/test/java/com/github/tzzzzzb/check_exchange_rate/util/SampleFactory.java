package com.github.tzzzzzb.check_exchange_rate.util;

import com.github.tzzzzzb.check_exchange_rate.model.ExchangeRates;

import java.util.HashMap;
import java.util.Map;


public class SampleFactory {
    public static ExchangeRates getSample() {
        ExchangeRates er = new ExchangeRates();
        er.setBase("USD");
        er.setTimestamp("1111");
        Map<String, Float> erMap = new HashMap<>();
        erMap.put("AED", 4.4f);
        erMap.put("AFN", 95.889778f);
        erMap.put("BBD", 2f);
        erMap.put("CNY", 6f);
        erMap.put("USD", 1f);
        er.setRates(erMap);
        return er;
    }
}
