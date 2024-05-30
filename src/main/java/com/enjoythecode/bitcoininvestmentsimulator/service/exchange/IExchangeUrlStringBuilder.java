package com.enjoythecode.bitcoininvestmentsimulator.service.exchange;

/**
 * Interface for building URLs to exchange rates APIs.
 */
public interface IExchangeUrlStringBuilder {

    /**
     * Builds a URL to get exchange rates for the specified currency.
     *
     * @param fromCurrency the currency code to get exchange rates for.
     * @return the URL to get exchange rates for the specified currency.
     */
    String buildExchangeUrl(String fromCurrency);

}
