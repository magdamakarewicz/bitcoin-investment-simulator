package com.enjoythecode.bitcoininvestmentsimulator.service.exchange;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertTrue;

class FastForexRateServiceTest {

    @Test
    public void shouldReturn4coma3RateForUsdPlnPair() throws IOException, InvalidInputDataException {
        //given
        String currency = "USD";

        FastForexUrlStringBuilder fastForexUrlStringBuilderMock = Mockito.mock(FastForexUrlStringBuilder.class);
        Mockito.when(fastForexUrlStringBuilderMock.buildExchangeUrl(currency))
                .thenReturn("https://api.fastforex.io/fetch-all?from=USD&api_key=fb7a0538a7-a093871df4-se6wmx");

        String preparedUrl = fastForexUrlStringBuilderMock.buildExchangeUrl(currency);

        ObjectMapper mapperMock = Mockito.mock(ObjectMapper.class);

        String jsonString = "{\"base\":\"USD\",\"results\":{\"PLN\":3.90277},\"updated\":\"2024-05-28 12:15:39\",\"ms\":5}";

        JsonNode testNode = new ObjectMapper().readTree(jsonString);

        Mockito.when(mapperMock.readTree(new URL(preparedUrl)))
                .thenReturn(testNode);

        FastForexRateService fastForexRateService = new FastForexRateService(fastForexUrlStringBuilderMock, mapperMock);

        //when
        BigDecimal exchangeRateFromUrl = fastForexRateService.getRate("USD", "PLN");

        //then
        assertTrue(BigDecimal.valueOf(3.90277).compareTo(exchangeRateFromUrl) == 0);
    }

}