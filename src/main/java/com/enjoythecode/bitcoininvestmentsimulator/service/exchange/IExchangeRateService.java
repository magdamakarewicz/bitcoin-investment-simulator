package com.enjoythecode.bitcoininvestmentsimulator.service.exchange;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;

import java.math.BigDecimal;

/**
 * This interface represents a service for retrieving the exchange rate between two currencies.
 * <p>
 * The exchange rate is represented as a {@link java.math.BigDecimal}.
 */
public interface IExchangeRateService {

    /**
     * Retrieves the exchange rate between the specified currencies.
     *
     * @param fromCurrency the currency code of the currency to convert from.
     * @param toCurrency   the currency code of the currency to convert to.
     * @return a {@link java.math.BigDecimal} representing the exchange rate.
     * @throws InvalidInputDataException if either the fromCurrencyMark or toCurrencyMark is not a valid currency code.
     */
    BigDecimal getRate(String fromCurrency, String toCurrency) throws InvalidInputDataException;

}
