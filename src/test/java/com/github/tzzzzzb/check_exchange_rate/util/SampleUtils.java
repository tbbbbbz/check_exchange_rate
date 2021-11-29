package com.github.tzzzzzb.check_exchange_rate.util;

import com.github.tzzzzzb.check_exchange_rate.model.ExchangeRates;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

public class SampleUtils {
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


    public static void assertDeepCopied(ExchangeRates er1, ExchangeRates er2) {
        assertEquals(er1.getBase(), er2.getBase());
        assertEquals(er1.getTimestamp(), er2.getTimestamp());
        assertNotSame(er1, er2);
        assertEquals(er1.getRates(), er2.getRates());
        if (er1.getRates() != null && er2.getRates() != null) {
            assertNotSame(er1.getRates(), er2.getRates());
        }
    }
}
