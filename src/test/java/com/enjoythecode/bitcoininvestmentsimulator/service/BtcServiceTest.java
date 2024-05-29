package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;
import com.enjoythecode.bitcoininvestmentsimulator.model.Investment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class BtcServiceTest {

    @Mock
    IBtcPriceService btcPriceServiceMock;

    @Mock
    InvestmentService investmentServiceMock;

    @InjectMocks
    BtcService btcService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturn299757coma96BtcCurrentValuePerBtcBought31082015For1000Usd() throws InvalidInputDataException {
        //given
        //sample instance Investment to be returned by the mock
        Investment investment = new Investment(
                LocalDate.of(2024, 5, 29),
                LocalDate.of(2015, 8, 31),
                BigDecimal.valueOf(1000),
                "USD",
                BigDecimal.valueOf(4.4018),
                BigDecimal.valueOf(227.1808),
                BigDecimal.valueOf(68098.9496),
                BigDecimal.valueOf(14470.7051),
                1447.07
        );

        Mockito.when(investmentServiceMock.saveInvestmentEvent(any(Investment.class))).thenReturn(investment);

        Mockito.when(btcPriceServiceMock.getPastPrice("31-08-2015", "USD"))
                .thenReturn(BigDecimal.valueOf(227.1808));

        Mockito.when(btcPriceServiceMock.getCurrentPrice("USD"))
                .thenReturn(BigDecimal.valueOf(68098.9496));

        //when
        BigDecimal currentValue = btcService.btcSaleEvent("31-08-2015", "USD",
                BigDecimal.valueOf(1000));

        //then
        assertEquals(BigDecimal.valueOf(299757.96), currentValue);

    }

}