package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BtcServiceTest {

    @Mock
    IBtcPriceService btcPriceServiceMock;

    @InjectMocks
    BtcService btcService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturn299757coma96BtcCurrentValuePerBtcBought31082015For1000Usd() throws InvalidInputDataException {
        //given
        Mockito.when(btcPriceServiceMock.getPastPrice("31-08-2015", "USD"))
                .thenReturn(BigDecimal.valueOf(227.1808));

        Mockito.when(btcPriceServiceMock.getCurrentPrice("USD"))
                .thenReturn(BigDecimal.valueOf(68098.9496));

        //when
        BigDecimal currentValue = btcService.btcValueToday("31-08-2015", "USD",
                BigDecimal.valueOf(1000));

        //then
        assertEquals(BigDecimal.valueOf(299757.96), currentValue);

    }

}