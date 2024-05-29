package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CoinDeskPriceServiceTest {

    @Mock
    CoinDeskUrlStringBuilder coinDeskUrlStringBuilderMock;

    @Mock
    ObjectMapper mapperMock;

    @InjectMocks
    CoinDeskPriceService coinDeskPriceService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturn227coma1808RateForBtcOn31082015() throws IOException, InvalidInputDataException {

        //given
        String currency = "USD";
        String date = "31-08-2015";
        String convertedDate = "2015-08-31";

        Mockito.when(coinDeskUrlStringBuilderMock.buildBtcUrl(convertedDate, currency))
                .thenReturn("https://api.coindesk.com/v1/bpi/historical/close.json?start=2015-08-31&end=2015-08-31&currency=usd");

        String preparedUrl = coinDeskUrlStringBuilderMock.buildBtcUrl(convertedDate, currency);

        String jsonString = "{\"bpi\":{\"2015-08-31\":227.1808},\"disclaimer\":\"This data was produced from the " +
                "CoinDesk Bitcoin Price Index. BPI value data returned as USD.\",\"time\":{\"updated\":\"Sep 1, " +
                "2015 00:03:00 UTC\",\"updatedISO\":\"2015-09-01T00:03:00+00:00\"}}";

        JsonNode testNode = new ObjectMapper().readTree(jsonString);

        Mockito.when(mapperMock.readTree(new URL(preparedUrl)))
                .thenReturn(testNode);

        //when
        BigDecimal btcPriceFromUrl = coinDeskPriceService.getPastPrice(date, currency);

        //then
        assertTrue(BigDecimal.valueOf(227.1808).compareTo(btcPriceFromUrl) == 0);

    }

}