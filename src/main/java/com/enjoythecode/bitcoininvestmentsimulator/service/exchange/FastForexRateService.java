package com.enjoythecode.bitcoininvestmentsimulator.service.exchange;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Optional;

@AllArgsConstructor
@Service
public class FastForexRateService implements IExchangeRateService {

    private IExchangeUrlStringBuilder urlStringBuilder;
    private ObjectMapper objectMapper;

    @Override
    public BigDecimal getRate(String fromCurrency, String toCurrency) throws InvalidInputDataException {
        String preparedUrl = urlStringBuilder.buildExchangeUrl(fromCurrency);
        JsonNode mainNode = null;
        try {
            mainNode = objectMapper.readTree(new URL(preparedUrl));
        } catch (IOException e) {
            throw new InvalidInputDataException("Incorrect input currency", e);
        }
        JsonNode specificRateNode = mainNode.get("results").get(toCurrency);
        return new BigDecimal(
                Optional.ofNullable(specificRateNode)
                        .orElseThrow(() -> new InvalidInputDataException("Incorrect target currency"))
                        .asText()
        );
    }

}
