package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;
import com.enjoythecode.bitcoininvestmentsimulator.service.exchange.IExchangeRateService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;

/**
 * Implementation of the {@link IBtcPriceService} interface that provides methods to get historical
 * and current Bitcoin prices using the CoinDesk API.
 */
@AllArgsConstructor
@Service
public class CoinDeskPriceService implements IBtcPriceService {

    private IBtcUrlStringBuilder urlStringBuilder;
    private ObjectMapper objectMapper;
    private IExchangeRateService exchangeRateService;

    /**
     * Gets the past Bitcoin price for a specified date and currency using the CoinDesk API.
     *
     * @param date     the date for which to get the exchange rate, in the format "dd-MM-yyyy".
     * @param currency the currency in which to get the exchange rate ("PLN" or "USD").
     * @return the past Bitcoin price for the specified date and currency, as a {@code BigDecimal}.
     * @throws InvalidInputDataException if the input date is in the wrong format or if the CoinDesk API returns
     *                                   an error response.
     */
    @Override
    public BigDecimal getPastPrice(String date, String currency) throws InvalidInputDataException {
        String urlDate = null;
        try {
            urlDate = convertDate(date);
        } catch (DateTimeParseException e) {
            throw new InvalidInputDataException("Invalid date format");
        }
        String preparedPastUrl = urlStringBuilder.buildBtcUrl(urlDate, currency);
        JsonNode mainNode = null;
        try {
            mainNode = objectMapper.readTree(new URL(preparedPastUrl));
        } catch (IOException e) {
            throw new InvalidInputDataException("Invalid input (date too early) or there is an API issue", e);
        }
        try {
            JsonNode specificRateNode = mainNode.get("bpi").get(urlDate);
            BigDecimal pastRateInUsd = new BigDecimal(specificRateNode.asText());
            return currency.equals("PLN") ? pastRateInUsd.multiply(exchangeRateService.getRate("USD", "PLN")) : pastRateInUsd;
        } catch (NullPointerException e) {
            throw new InvalidInputDataException("Invalid input (date too late) or there is an API issue", e);
        }
    }

    /**
     * Gets the current Bitcoin price for a specified currency using the CoinDesk API from provided URL.
     * Depending on the currency provided by the user (USD or PLN), it downloads the value directly from the URL
     * (for USD) or uses the currency exchange service (for PLN), because the API does not provide rate for PLN currency.
     *
     * @param currency the currency in which to get the exchange rate ("PLN" or "USD").
     * @return the current Bitcoin price for the specified currency, as a {@code BigDecimal}.
     * @throws InvalidInputDataException if the input currency is not "PLN" or "USD", or if the CoinDesk API
     *                                   returns an error response.
     */
    @Override
    public BigDecimal getCurrentPrice(String currency) throws InvalidInputDataException {
        if (!currency.equals("PLN") && !currency.equals("USD")) {
            throw new InvalidInputDataException("Unsupported input currency");
        }
        JsonNode mainNode = null;
        try {
            mainNode = objectMapper.readTree(new URL("https://api.coindesk.com/v1/bpi/currentprice.json"));
        } catch (IOException e) {
            throw new InvalidInputDataException("Access error", e);
        }
        JsonNode specificRateNode = mainNode.get("bpi").get("USD").get("rate_float");
        BigDecimal usdRate = new BigDecimal(
                Optional.ofNullable(specificRateNode)
                        .orElseThrow(() -> new InvalidInputDataException("Access error"))
                        .asText()
        );
        return currency.equals("PLN") ? usdRate.multiply(exchangeRateService.getRate("USD", "PLN")) : usdRate;
    }

    private static String convertDate(String inputDate) throws DateTimeParseException {
        LocalDate date = LocalDate.parse(inputDate, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

}
