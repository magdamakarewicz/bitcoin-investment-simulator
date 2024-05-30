package com.enjoythecode.bitcoininvestmentsimulator.service;

import org.springframework.stereotype.Service;

import static com.enjoythecode.bitcoininvestmentsimulator.config.AppConfig.BITCOIN_API_PAGE;
import static com.enjoythecode.bitcoininvestmentsimulator.config.AppConfig.BITCOIN_ENDPOINT;

/**
 * Implementation of the {@link IBtcUrlStringBuilder} interface that builds the CoinDesk API URL
 * for getting the Bitcoin rate for a specific date and currency.
 */
@Service
public class CoinDeskUrlStringBuilder implements IBtcUrlStringBuilder {

    /**
     * Builds the CoinDesk API URL for getting the Bitcoin rate for a specific date and currency.
     *
     * @param investmentDate the date for which to get the Bitcoin price.
     * @param currency the currency in which to get the Bitcoin price.
     * @return the CoinDesk API URL for getting the Bitcoin price for the specified date and currency.
     */
    @Override
    public String buildBtcUrl(String investmentDate, String currency) {
        return BITCOIN_API_PAGE + BITCOIN_ENDPOINT + "?start=" + investmentDate + "&end=" + investmentDate +
                "&currency=" + currency;
    }

}
