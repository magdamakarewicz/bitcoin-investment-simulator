package com.enjoythecode.bitcoininvestmentsimulator.service;

import com.enjoythecode.bitcoininvestmentsimulator.exception.InvalidInputDataException;

import java.math.BigDecimal;

/**
 * This interface provides methods to get the historical and current exchange rates for Bitcoin.
 */
public interface IBtcPriceService {

    /**
     * Returns the historical price of Bitcoin for the specified date and currency.
     *
     * @param date     the date for which the Bitcoin price is to be retrieved, in the format yyyy-MM-dd.
     * @param currency the currency for which the price is to be retrieved, as a three-letter currency code.
     * @return the historical price of Bitcoin for the specified date and currency.
     * @throws InvalidInputDataException if the input data is invalid.
     */
    BigDecimal getPastPrice(String date, String currency) throws InvalidInputDataException;

    /**
     * Returns the current exchange rate for Bitcoin for the specified currency.
     *
     * @param currency the currency for which the Bitcoin price is to be retrieved, as a three-letter currency code.
     * @return the current price of Bitcoin for the specified currency.
     * @throws InvalidInputDataException if the input data is invalid.
     */
    BigDecimal getCurrentPrice(String currency) throws InvalidInputDataException;

}