package com.enjoythecode.bitcoininvestmentsimulator.service.exchange;

import org.springframework.stereotype.Service;

import static com.enjoythecode.bitcoininvestmentsimulator.config.AppConfig.*;

/**
 * Implementation of {@link IExchangeUrlStringBuilder} that builds a FastForex API URL for a given currency.
 * The API URL is built by concatenating the API page, API endpoint, the currency mark, and a private API key.
 */
@Service
public class FastForexUrlStringBuilder implements IExchangeUrlStringBuilder {

    /**
     * Builds a FastForex API URL for the given currency mark.
     *
     * @param fromCurrency the currency mark to build the API URL for
     * @return a String representing the FastForex API URL for the given currency
     */
    @Override
    public String buildExchangeUrl(String fromCurrency) {
        return EXCHANGE_API_PAGE + EXCHANGE_ENDPOINT + fromCurrency + EXCHANGE_PRIVATE_API_KEY;
    }

}
