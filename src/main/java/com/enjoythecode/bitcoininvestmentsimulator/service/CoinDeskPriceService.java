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

@AllArgsConstructor
@Service
public class CoinDeskPriceService implements IBtcPriceService {

    private IBtcUrlStringBuilder urlStringBuilder;
    private ObjectMapper objectMapper;
    private IExchangeRateService exchangeRateService;

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
