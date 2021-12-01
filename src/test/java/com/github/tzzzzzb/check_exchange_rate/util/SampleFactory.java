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
        erMap.put("AED", (float) 4.4);
        erMap.put("AFN", (float) 95.889778);
        erMap.put("BBD", (float) 2);
        er.setRates(erMap);
        return er;
    }
}
