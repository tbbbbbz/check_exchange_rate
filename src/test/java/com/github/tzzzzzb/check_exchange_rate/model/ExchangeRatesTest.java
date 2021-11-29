package com.github.tzzzzzb.check_exchange_rate.model;

import com.github.tzzzzzb.check_exchange_rate.util.SampleUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;

import static com.github.tzzzzzb.check_exchange_rate.util.SampleUtils.assertDeepCopied;

@ActiveProfiles("test")
class ExchangeRatesTest {
    private ExchangeRates oldER;

    @BeforeEach
    public void setupOldER() {
        oldER = SampleUtils.getSample();
    }

    @Test
    void givenAnExchangeRatesObjectWithoutNullField_whenDeepCopy_thenReturnACorrectlyDeepCopiedObject() {
        assertDeepCopyCorrection();
    }

    private void assertDeepCopyCorrection() {
        assertDeepCopied(oldER.deepCopy(), oldER);
    }


    @Test
    void givenAnExchangeRatesObjectWithEmptyRatesMap_whenDeepCopy_thenReturnACorrectlyDeepCopiedObject() {
        oldER.setRates(new HashMap<>());
        assertDeepCopyCorrection();
    }

    @Test
    void givenAnExchangeRatesObjectWithRatesBeingNull_whenDeepCopy_thenReturnACorrectlyDeepCopiedObject() {
        oldER.setRates(null);
        assertDeepCopyCorrection();
    }

    @Test
    void givenAnExchangeRatesObjectWithTimestampBeingNull_whenDeepCopy_thenReturnACorrectlyDeepCopiedObject() {
        oldER.setTimestamp(null);
        assertDeepCopyCorrection();
    }

    @Test
    void givenAnExchangeRatesObjectWithBaseBeingNull_whenDeepCopy_thenReturnACorrectlyDeepCopiedObject() {
        oldER.setBase(null);
        assertDeepCopyCorrection();
    }
}