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

/**
 * A service class that implements the {@link IExchangeRateService} interface for getting exchange rates from FastForex API.
 * It uses the {@link IExchangeUrlStringBuilder} interface to build the exchange URL and the Jackson {@link ObjectMapper}
 * to parse the JSON response.
 */
@AllArgsConstructor
@Service
public class FastForexRateService implements IExchangeRateService {

    private IExchangeUrlStringBuilder urlStringBuilder;
    private ObjectMapper objectMapper;

    /**
     * Returns the exchange rate from the specified from currency mark to the specified to currency mark, obtained
     * from the FastForex API.
     *
     * @param fromCurrency The from currency mark.
     * @param toCurrency   The to currency mark.
     * @return The exchange rate.
     * @throws InvalidInputDataException if the input data is invalid or the API call fails.
     */
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
