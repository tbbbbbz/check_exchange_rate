package com.github.tzzzzzb.check_exchange_rate.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeRatesTest {
    private ExchangeRates oldER;

    @BeforeEach
    public void setupOldER() {
        oldER = new ExchangeRates();
        oldER.setBase("USD");
        oldER.setTimestamp("1111");
        Map<String, Float> oldMap = new HashMap<>();
        oldMap.put("AED", (float) 4.4);
        oldMap.put("AFN", (float) 95.889778);
        oldMap.put("BBD", (float) 2);
        oldER.setRates(oldMap);
    }

    @Test
    public void givenAnExchangeRatesObjectWithoutNullField_whenDeepCopy_thenReturnACorrectlyDeepCopiedObject() {
        assertDeepCopyCorrection();
    }

    private void assertDeepCopyCorrection() {
        assertDeepCopied(oldER.deepCopy(), oldER);
    }

    private void assertDeepCopied(ExchangeRates er1, ExchangeRates er2) {
        assertEquals(er1.getBase(), er2.getBase());
        assertEquals(er1.getTimestamp(), er2.getTimestamp());
        assertNotSame(er1, er2);
        assertEquals(er1.getRates(), er2.getRates());
        if (er1.getRates() != null && er2.getRates() != null) {
            assertNotSame(er1.getRates(), er2.getRates());
        }
    }

    @Test
    public void givenAnExchangeRatesObjectWithEmptyRatesMap_whenDeepCopy_thenReturnACorrectlyDeepCopiedObject() {
        oldER.setRates(new HashMap<>());
        assertDeepCopyCorrection();
    }

    @Test
    public void givenAnExchangeRatesObjectWithRatesBeingNull_whenDeepCopy_thenReturnACorrectlyDeepCopiedObject() {
        oldER.setRates(null);
        assertDeepCopyCorrection();
    }

    @Test
    public void givenAnExchangeRatesObjectWithTimestampBeingNull_whenDeepCopy_thenReturnACorrectlyDeepCopiedObject() {
        oldER.setTimestamp(null);
        assertDeepCopyCorrection();
    }

    @Test
    public void givenAnExchangeRatesObjectWithBaseBeingNull_whenDeepCopy_thenReturnACorrectlyDeepCopiedObject() {
        oldER.setBase(null);
        assertDeepCopyCorrection();
    }
}